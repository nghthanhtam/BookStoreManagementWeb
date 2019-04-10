
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% request.setAttribute("txtTitle", "Phiếu chi"); %>

<%@include file="header.jsp" %>

<head>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">


<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>     
<script type="text/javascript">
	$(document).ready(function() {

            $('#search').autocomplete({
		source: '${pageContext.request.contextPath}/admin/nhacungcap/ajaxtimnhacungcap',
                select: function(event, ui){
                        this.value = ui.item.label;
                        $("#manhacungcap").val(ui.item.value);
                        return false;
                    }
            });

	});
</script>

</head>




<div class="page-title">
    <div class="title_left">
        <h3>Phiếu chi</h3>
    </div>

</div>
<div class="clearfix"></div>

<div class="row">
    <div class="col-md-6 col-xs-12 col-md-offset-3">          
        <div class="x_panel">
            <div class="x_title">
                <h2>Sửa thông tin phiếu chi</h2>
                <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li> 
                    <li><a class="close-link"><i class="fa fa-close"></i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <br>



                <form class="form-horizontal form-label-left " method="POST" action="${contextPath}/admin/phieuchi/edit">
                
                    <input type="hidden" name="maphieuchi" value="${phieuChiModel.getMaPhieuChi()}">
                    
                    <div class="form-group">
                        <label 
                            class="control-label col-md-3 col-sm-3 col-xs-12">Tên nhà cung cấp <span class="required"></span>
                        </label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                            <input id="search" class="form-control col-md-7 col-xs-12" required="required" name="manhacungcap1" value="${nhaCungCapModel.getTenNhaCungCap()}" type="text" disabled>
                        </div>
                            <input id="manhacungcap" class="form-control col-md-7 col-xs-12" required="required" name="manhacungcap" value="${nhaCungCapModel.getMaNhaCungCap()}" type="hidden" placeholder="Hãy nhập mã nhà cung cấp"> 
                    </div>
   

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Tổng tiền<span class="required"></span>
                        </label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                            <input class="form-control col-md-7 col-xs-12" required="required" name="tongtien" value="${phieuChiModel.getTongTien()}" type="text" placeholder="Hãy nhập tổng tiền" disabled>
                        </div>
                           
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Ghi chú<span class="required"></span>
                        </label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                            <input class="form-control col-md-7 col-xs-12" name="ghichu" value="${phieuChiModel.getGhiChu()}" type="text" placeholder="Hãy nhập ghi chú">
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-md-9 col-xs-12 col-md-offset-4">
                            <a href="${contextPath}/admin/phieuchi" class="btn btn-primary">Hủy bỏ</a>
                            <button type="submit" class="btn btn-success" name="submit" value="capnhat">Cập nhật</button>

                        </div>
                    </div>

                </form>

            </div>
        </div>  



    </div>

</div>



<%@include file="footer.jsp" %> 