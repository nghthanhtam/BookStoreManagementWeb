
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@include file="header.jsp" %>

<div class="page-title">
    <div class="title_left">
        <h3>Đơn đặt hàng</h3>
    </div>

</div>


<div class="clearfix"></div>
<div class="row">

    <div class="col-md-12 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Danh sách các đơn đặt hàng</h2>
                <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li>

                    <li><a class="close-link"><i class="fa fa-close"></i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>
            <div class="x_content" style="display: block;">


                <div class="x_content">


                    <div class="x_content">
							
							<ul class="">
								<li><span class="text-uppercase">Page:</span></li>
                                                                <li class="active"></li>
                                                                
                                                                <%  
                                                        Integer currentPage = Integer.parseInt(request.getAttribute("currentpage").toString());
                                                        Integer numOfPage = Integer.parseInt(request.getAttribute("numofpage").toString());
                                                        
                                                        for(int i=0; i< numOfPage;i++ ){
                                                            out.print("<li><a href=\"donhang?page="+(i+1)+"\">"+(i+1)+"</a></li>");  
                                     
                                                        }
                                                           
                                                        
                                                        
                                     %>
								
								
								</ul>
						</div>
                    <!-- start project list -->
                    <table class="table table-striped projects">
                        <thead>
                            <tr>
                                <th style="width: 10%">Mã đơn hàng</th>
                                <th style="width: 15%">Tên khách hàng</th>
                                <th style="width: 15%">Tên đăng nhặp</th>
                                <th style="width: 15%">Giờ đặt hàng</th>
                                <th style="width: 10%">Số điện thoại</th>
                                <th style="width: 10%">Tổng tiền</th>
                                <th style="width: 15%">Trạng thái</th>
                                <th sytle="width: 100%">Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>

                            ${listDonHang.size()==0?"Chưa sách nào được tạo!":""}

                            <c:forEach items="${listDonHang}" var="obj">
                                <tr>
                                    <td >${obj.getMaDonHang()}</td>
                                    <td >${obj.getTenThanhVien()}</td>
                                    <td >${obj.getTenDangNhap()}</td>
                                    <td >${obj.getNgayLap()}</td>
                                    <td >${obj.getSoDienThoai()}</td>
                                    <td >${obj.getTongTien()}</td>
                                    <td >
                                        <c:choose>
                                            <c:when test="${obj.getTrangThai()==-1}">
                                                <a href="${contextPath}/admin/donhang/updatetrangthai?id=${obj.getMaDonHang()}&trangthai=1&page=<% out.print(currentPage); %>" style="height: 21px;width: 78px"  class="btn btn-success btn-xs"><i class="fa fa-check-circle-o"></i> Tiếp nhận</a>
                                                <a href="${contextPath}/admin/donhang/updatetrangthai?id=${obj.getMaDonHang()}&trangthai=0&page=<% out.print(currentPage); %>" style="height: 21px;width: 78px" class="btn btn-danger btn-xs"><i class="fa fa-close"></i> Hủy </a>
                                                </form>
                                            </c:when> 
                                            <c:when test="${obj.getTrangThai()==1}">
                                                <font color="blue">
                                                Đã tiếp nhận
                                            </c:when> 
                                            <c:when test="${obj.getTrangThai()==2}">
                                                <font color="grey">
                                                Đang giao hàng
                                            </c:when> 
                                            <c:when test="${obj.getTrangThai()==3}">
                                                <font color="green">
                                                Đã hoàn tất
                                            </c:when>
                                            <c:otherwise>
                                                <font color="red">
                                                Bị hủy

                                            </c:otherwise>
                                        </c:choose>


                                    </td>
                                    <td> 
                                        <a href="${contextPath}/admin/donhang/edit?id=${obj.getMaDonHang()}" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> ${obj.getTrangThai()==0? "Xem":"Edit"} </a>
                                    </td>
                                </tr>
                            </c:forEach>




                        </tbody>
                    </table>

                </div>
            </div>
        </div> 
    </div>










        <!-- end project list -->

    </div>





<%@include file="footer.jsp" %>
