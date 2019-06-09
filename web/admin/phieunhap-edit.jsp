<%-- 
    Document   : phieunhap-edit
    Created on : Jun 9, 2019, 11:53:25 PM
    Author     : Admin
--%>

<%@page import="java.lang.String"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.SachModel"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>



<%@include file="header.jsp" %>


<div class="page-title">
    <div class="title_left">
        <h3>Phi?u nh?p</h3>
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



                    <c:set var="urlEditCurrent" value="edit?id=${phieuNhap.getMaPhieuNhap()}" />

                    <form  class="form-horizontal form-label-left input_mask" method="POST"  action="${contextPath}/admin/phieunhap/${urlEditCurrent}">

                        <c:if test = "${phieuNhap != null}">

                            <div class="form-group">
                                <label class= " control-label col-md-3 col-sm-3 col-xs-12">Mã phi?u nh?p</label>
                                <div class=" col-md-3 col-sm-9 col-xs-12">
                                    <input type="text" name="maphieunhap" value="${phieuNhap == null ? "":phieuNhap.getMaPhieuNhap()}" class="form-control col-md-7 col-xs-12" readonly>
                                </div>
                            </div> 
                        </c:if>


                        <div class="form-group">
                            <label class= " control-label col-md-3 col-sm-3 col-xs-12" >Nhà cung c?p<span class="required">*</span>
                            </label>
                            <div class="col-md-3 col-sm-9 col-xs-12">
                                <input type="text" name="tennhacungcap" readonly value="${phieuNhap == null ? "":phieuNhap.getTenNhaCungCap()}" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" >Tên ngu?i t?o<span class="required">*</span>
                            </label>
                            <div class="col-md-3 col-sm-9 col-xs-12">
                                <input  type="text" name="tenthanhvien" readonly required="" value="${phieuNhap == null ? "":phieuNhap.getTenThanhVien()}" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Ngày nh?p sách</label>
                            <div class="col-md-3 col-sm-9 col-xs-12">
                                <input type="text" name="ngaynhap" readonly  class="form-control col-md-7 col-xs-12" value="${phieuNhap == null ? "":phieuNhap.getNgayNhap()}">
                            </div>
                        </div>


                        <div   class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Ghi chú</label>
                            <div class="col-md-3 col-sm-9 col-xs-12">
                                <input type="text" name="ghichu" class="form-control col-md-7 col-xs-12" value="${phieuNhap == null ? "":phieuNhap.getGhiChu()}">
                            </div>
                        </div>

                      

                        <!--DANH SÁCH S?N PH?N KHÁCH Ð?T-->
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th style="width: 10%">Mã s?n ph?m</th>
                                    <th style="width: 25%">Tên s?n ph?m</th>
                                    <th style="width: 15%">S? lu?ng</th>
                                    <th style="width: 15%">Ðon giá</th>
                                </tr>
                            </thead>
                            <tbody>

                                ${listCTPhieuNhap.size()==0?"<tr>Chua có s?n ph?m nào trong hóa don!</tr>":""}
                                <c:forEach items="${listCTPhieuNhap}" var="obj">
                                    <tr>
                                        <td>${obj.getMaSach()}</td>
                                        <td>${obj.getTenSach()}</td>
                                        <td>${obj.getSoLuongNhap()}</td>
                                        <td>${obj.getDonGia()}</td>
                                    </tr>
                                </c:forEach> 
                            </tbody>
                        </table>     



                        <!--K?T THÚC DANH SÁCH S?N PH?N KHÁCH Ð?T-->





                        <div class="ln_solid"></div>
                        <div class="form-group">
                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">

                                <a class="btn btn-primary" href="/admin/phieunhap">Cancel</a>

                                <button type="submit" class="btn btn-success" name="submit" value="sua">Submit</button>

                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>  

</div>





<%@include file="footer.jsp" %>
