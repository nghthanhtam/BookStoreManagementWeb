<%-- 
    Document   : chantruycap
    Created on : Apr 9, 2019, 11:28:20 AM
    Author     : MITICC06
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% request.setAttribute("txtTitle", "Bạn không có quyền truy cập trang này");     %>
<%@include file="header.jsp" %>


<div class="page-title">
    <div class="title_left">
        <h3>${tenChucNang}</h3>
    </div>

</div>
<div class="clearfix"></div>

<div class="row">
    <div class="col-md-6 col-md-offset-3 col-xs-12 col-sm-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Thông báo</h2>
                
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                 
                Bạn không có quyền truy cập chức năng này!

            </div>
        </div>


</div>
</div>

<%@include file="footer.jsp" %>