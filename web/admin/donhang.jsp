<%@page import="java.lang.String"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.SachModel"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>



<%@include file="header.jsp" %>

<head>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">


    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>  
    <script src="${contextPath}/admin/ckeditor/ckeditor.js"></script>     
    <script type="text/javascript">
        $(document).ready(function () {
            $('#tentacgia').autocomplete({
                source: '${contextPath}/admin/sach/add/ajaxtimtacgiasach'

            });
        });
        $(document).ready(function () {
            $('#nhaxuatban').autocomplete({
                source: '${contextPath}/admin/sach/add/ajaxtimnhaxuatbansach'

            });
        });
    </script>


</head>




<div class="page-title">
    <div class="title_left">
        <h3>Đơn hàng</h3>
    </div>

</div>


<div class="row">   

    <div class="row">
        <div class="col-md-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>${txtTitle}</h2>
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


                    <c:set var="urlEditCurrent" value="edit?id=${donHang.getMaDonHang()}" />

                    <form  class="form-horizontal form-label-left input_mask" method="POST"  action="${contextPath}/admin/donhang/${urlEditCurrent}">

                        <c:if test = "${donHang != null}">

                            <div class="form-group">
                                <label class= " control-label col-md-3 col-sm-3 col-xs-12">Mã đơn hàng</label>
                                <div class=" col-md-3 col-sm-9 col-xs-12">
                                    <input type="text" name="madonhang" value="${donHang == null ? "":donHang.getMaDonHang()}" class="form-control col-md-7 col-xs-12" readonly>
                                </div>
                            </div> 
                        </c:if>


                        <div class="form-group">
                            <label class= " control-label col-md-3 col-sm-3 col-xs-12" >Họ tên khách hàng<span class="required">*</span>
                            </label>
                            <div class="col-md-3 col-sm-9 col-xs-12">
                                <input type="text" name="tenkhachhang" readonly value="${donHang == null ? "":donHang.getTenThanhVien()}" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" >Tên đăng nhập<span class="required">*</span>
                            </label>
                            <div class="col-md-3 col-sm-9 col-xs-12">
                                <input  type="text" name="tendangnhap" readonly required="" value="${donHang == null ? "":donHang.getTenDangNhap()}" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                    
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Ngày đặt hàng</label>
                            <div class="col-md-3 col-sm-9 col-xs-12">
                                <input type="text" name="ngaydathang" readonly  class="form-control col-md-7 col-xs-12" value="${donHang == null ? "":donHang.getNgayLap()}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Tổng tiền</label>
                            <div class="col-md-3 col-sm-9 col-xs-12">

                               <input type="text" name="tongtien" readonly  class="form-control col-md-7 col-xs-12" value="${donHang == null ? "":donHang.getTongTien()}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Trạng thái<span class="required">*</span>
                            </label>
                            <div class="col-md-3 col-sm-9 col-xs-12">
                                 <select id="heard" class="form-control" ${donHang.getTrangThai()==0 ? "disabled":""}  name="trangthai">
                                    <option value="" selected disabled hidden>Chọn trạng thái</option>
                                    <option value="-1" ${donHang != null && donHang.getTrangThai() == -1 ? "selected=\"selected\"" : ""}>Đang chờ</option>
                                    <option value="1" ${donHang != null && donHang.getTrangThai() == 1 ? "selected=\"selected\"" : ""}>Đang tiếp nhận</option>
                                    <option value="2" ${donHang != null && donHang.getTrangThai() == 2 ? "selected=\"selected\"" : ""}>Đang giao hàng</option>
                                    <option value="3" ${donHang != null && donHang.getTrangThai() == 3 ? "selected=\"selected\"" : ""}>Đã hoàn tất</option>
                                    <option value="0" ${donHang != null && donHang.getTrangThai() == 0 ? "selected=\"selected\"" : ""}>Bị hủy</option>

                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Địa chỉ giao hàng<span class="required">*</span>
                            </label>
                            <div class="col-md-3 col-sm-9 col-xs-12">
                                <input  type="text" name="diachigiaohang" class="form-control col-md-7 col-xs-12" required="required" ${donHang.getTrangThai()==0 ? "disabled":""} value="${donHang == null ? "":donHang.getDiaChiGiaoHang()}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Tỉnh thành<span class="required">*</span>
                            </label>
                            <div class="col-md-3 col-sm-9 col-xs-12">
                            ${listPhiShip.size()==0?"Chưa có phí ship nào!":""}

                                <select id="heard" class="form-control" ${donHang.getTrangThai()==0 ? "disabled":""} name="maphiship">
                                    <option value="" disabled hidden>Choose here</option>
                                    <c:forEach items="${listPhiShip}" var="obj">
                                        <option value="${obj.getMaPhiShip()}" ${obj.getMaPhiShip()==donHang.getMaPhiShip() ? "selected" : ""}>${obj.getTenTinh()}</option>

                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Số điện thoại<span class="required">*</span>
                            </label>
                            <div class="col-md-3 col-sm-9 col-xs-12">
                                <input  type="text" name="sodienthoai" class="form-control col-md-7 col-xs-12" readonly value="${donHang == null ? "":donHang.getSoDienThoai()}">
                            </div>
                        </div>


                        <div   class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Ghi chú<span class="required">*</span>
                            </label>
                            <div class="col-md-3 col-sm-9 col-xs-12">
                              <input  type="text" name="ghichu" class="form-control col-md-7 col-xs-12" readonly value="${donHang == null ? "":donHang.getGhiChu()}">
                             </div>
                        </div>
                             
                        
                        <!--DANH SÁCH SẢN PHẨN KHÁCH ĐẶT-->
                        <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th style="width: 10%">Mã sản phẩm</th>
                                <th style="width: 15%">Tên sản phẩm</th>
                                <th style="width: 15%">Số lượng</th>
                                <th style="width: 15%">% Giảm giá</th>
                                <th style="width: 10%">Tổng tiền</th>
                            </tr>
                        </thead>
                        <tbody>

                            ${listSanPham.size()==0?"Chưa có sản phẩm nào trong hóa đơn!":""}

                            <c:forEach items="${listSanPham}" var="obj">
                                <tr>
                                    <td >${obj.getMaSach()}</td>
                                    <td >${obj.getTenSach()}</td>
                                    <td >${obj.getSoLuong()}</td>
                                    <td >${obj.getPhanTramGiamGia()}</td>
                                    <td >${obj.getDonGia()*obj.getSoLuong()*(100-obj.getPhanTramGiamGia())*0.01}</td>
                                   
                                </tr>
                            </c:forEach>




                        </tbody>
                    </table>     
                             
                             
                             
                    <!--KẾT THÚC DANH SÁCH SẢN PHẨN KHÁCH ĐẶT-->
                             
                             
                             
                             
                             
                             
                             

                        

                        <div class="ln_solid"></div>
                        <div class="form-group">
                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                <a class="btn btn-primary" href="${contextPath}/admin/donhang">Cancel</a>
                                
                                ${donHang.getTrangThai()==0? "":"<button class=\"btn btn-primary\" type=\"reset\">Reset</button>"}
                                ${donHang.getTrangThai()==0? "":"<button type=\"submit\" class=\"btn btn-success\" name=\"submit\" value=\"${donHang==null ? \"them\":\"sua\"}\" >Submit</button>"}
                                
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>  

</div>


















<%@include file="footer.jsp" %>