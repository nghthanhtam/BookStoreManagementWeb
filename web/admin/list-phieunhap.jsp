<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% request.setAttribute("txtTitle", "Phiếu nhập");%>
<%@include file="header.jsp" %>



<head>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">


    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>     


</head>



<div class="page-title">
    <div class="title_left">
        <h3>Phiếu nhập</h3>
    </div>
</div>



<div class="clearfix"></div>


<div class="row">

    <div class="col-md-12 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Danh sách phiếu nhập</h2>

                <div class="clearfix"></div>
            </div>
            <div class="x_content" style="display: block;">


                <!-- start project list -->
                <table class="table table-striped projects">
                    <thead>
                        <tr>
                            <th style="width: 10%">Mã phiếu nhập</th>                     
                            <th style="width: 25%">Ngày nhập</th>                    
                            <th style="width: 25%">Tên nhà cung cấp</th>                     
                            <th style="width: 10%">Người thực hiện</th>
                            <th style="width: 10%">Tổng tiền</th>
                            <th style="width: 10%">Ghi chú</th>
                            <th style="width: 10%">Thao tác</th>
                        </tr>
                    </thead>

                    <tbody>
                        ${listPhieuNhap.size()==0?"Chưa có phiếu nhập nào!":""}

                        <c:forEach items="${listPhieuNhap}" var="obj">
                                <tr>
                                    <td >${obj.getMaPhieuNhap()}</td>
                                    <td >${obj.getNgayNhap()}</td>
                                    <td >${obj.getMaNhaCungCap()}</td> 
                                    <td>${obj.getMaThanhVien()}</td>
                                    <td></td>
                                    <td>${obj.getGhiChu()}</td>
                                    <td> 
                                        <a href="${contextPath}/admin/phieunhap/edit?id=${obj.getMaPhieuNhap()}" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Edit </a>
                                     <!--   <a href="${contextPath}/admin/phieunhap/delete?id=${obj.getMaPhieuNhap()}" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Delete </a>-->
                                    </td>
                                   
                                    
                                </tr>
                            </c:forEach>
                    </tbody>
                </table>
                <!-- end project list -->

            </div>
        </div>
    </div>



</div>





<%@include file="footer.jsp" %>
