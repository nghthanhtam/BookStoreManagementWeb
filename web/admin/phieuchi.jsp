<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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

    <div class="col-md-8 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Danh sách loại phiếu chi</h2>
                <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="#">Settings 1</a>
                            </li>
                            <li><a href="#">Settings 2</a>
                            </li>
                        </ul>
                    </li>
                    <li><a class="close-link"><i class="fa fa-close"></i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>
            <div class="x_content" style="display: block;">


                <!-- start project list -->
                <table class="table table-striped projects">
                    <thead>
                        <tr>
                            <th style="width: 2%">STT</th>                     
                            <th style="width: 25%">Tên nhà cung cấp</th>                    
                            <th style="width: 24%">Tên thành viên</th>                     
                            <th style="width: 5%">Tổng tiền</th>
                            <th style="width: 21%">Ngày lập phiếu</th>
                            <th style="width: 20%">Ghi chú</th>


                            <th style="width: 20%">Thao tác</th>
                        </tr>
                    </thead>

                    <tbody>
                        ${listAllPhieuChi.size()==0?"Chưa có phiếu chi nào được tạo!":""}                         

                        <c:forEach items="${listAllPhieuChiWithTenNhaCungCap}" var="obj">
                            <tr>
                                <td style="width: 5%" align="center">${obj.getMaPhieuChi()}</td>                         
                                <td style="width: 15%" align="left">${obj.getTenNhaCungCap()}</td>
                                <td style="width: 15%" align="left">${obj.getHoTen()}</td>
                                <td style="width: 15%" align="left">${obj.getTongTien()}</td>
                                <td style="width: 15%" align="left">${obj.getNgayLapPhieu()}</td>
                                <td style="width: 15%" align="left">${obj.getGhiChu()}</td>

                                <td> 
                                    <a href="${contextPath}/admin/phieuchi/edit?id=${obj.getMaPhieuChi()}" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Sửa </a>
                                    <a href="${contextPath}/admin/phieuchi/delete?id=${obj.getMaPhieuChi()}" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Xóa </a>
                                </td>
                            </tr>
                        </c:forEach>  

                    </tbody>
                </table>
                <!-- end project list -->

            </div>
        </div>
    </div>



    <div class="col-md-4 col-xs-12">          
        <div class="x_panel">
            <div class="x_title">
                <h2>Thêm phiếu chi mới</h2>
                <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li> 
                    <li><a class="close-link"><i class="fa fa-close"></i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                
                
                
             

                <form class="form-horizontal form-label-left " method="POST" action="${pageContext.request.contextPath}/admin/phieuchi">

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Tên nhà cung cấp <span class="required"></span>
                        </label>               
                        <div class="col-md-9 col-sm-9 col-xs-12">
                            <input id="search" class="form-control col-md-7 col-xs-12" required="required" name="tennhacungcap" type="text" placeholder="Hãy nhập mã nhà cung cấp">
                        </div>                      
                            <input id="manhacungcap" class="form-control col-md-7 col-xs-12" required="required" name="manhacungcap" type="hidden" placeholder="Hãy nhập mã nhà cung cấp">                     
                    </div>                    

                     
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Tổng tiền<span class="required"></span>
                        </label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                            <input class="form-control col-md-7 col-xs-12" required="required" name="tongtien" type="text" placeholder="Hãy nhập tổng tiền">
                        </div>
                    </div>   

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Ghi chú<span class="required"></span>
                        </label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                            <input class="form-control col-md-7 col-xs-12" name="ghichu" type="text" placeholder="Hãy nhập ghi chú">
                        </div>
                    </div>
                    


                    <div class="form-group">
                        <div class="col-md-9 col-xs-12 col-md-offset-4">
                            <button class="btn btn-primary" type="reset">Đặt lại</button>
                            <button type="submit" class="btn btn-success" name="submit" value="them">Thêm</button>
                        </div>
                    </div>

                    
                </form>

            </div>
                
        </div>  

    </div>

</div>

                                                

 
                                                
<%@include file="footer.jsp" %>
 