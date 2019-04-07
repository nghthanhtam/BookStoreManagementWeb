
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@include file="header.jsp" %>

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
                            class="control-label col-md-3 col-sm-3 col-xs-12">Mã nhà cung cấp <span class="required"></span>
                        </label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                            <input class="form-control col-md-7 col-xs-12" required="required" name="manhacungcap" type="text" placeholder="Hãy nhập mã nhà cung cấp">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Mã thành viên<span class="required"></span>
                        </label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                            <input class="form-control col-md-7 col-xs-12" required="required" name="mathanhvien" type="text" placeholder="Hãy nhập mã thành viên">
                        </div>
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