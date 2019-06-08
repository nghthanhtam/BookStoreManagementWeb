<%-- 
    Document   : dangnhap
    Created on : Apr 7, 2019, 9:02:29 AM
    Author     : MITICC06
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="theme/header.jsp" />



<!-- BREADCRUMB -->
<div id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="/">Home</a></li>
            <li class="active">Đăng nhập</li>
        </ul>
    </div>
</div>
<!-- /BREADCRUMB -->

<!-- section -->
<div class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row">
            <c:choose> 
                <c:when test="${status eq true}">
                    <div class="col-md-12 col-md-offset-3"> ${noiDungThongBao}</div>
                    <c:if test = "${urlReturn != null}"> 
                        <script>
                            setTimeout("location.href = '${urlReturn}';", 2000);
                        </script>  
                    </c:if>


                </c:when>
                <c:otherwise>

                    <form class="clearfix" method="POST" action="${contextPath}/login">
                        <div class="col-md-12"> 
                            <div class="col-md-12 col-xs-12">

                                <div class="form-group col-md-12 col-md-offset-3">
                                    <label class="control-label col-md-2">Tên đăng nhập <span class="required">*</span>
                                    </label>
                                    <div class="col-md-3">
                                        <input type="text" name="tendangnhap" required="required" class="form-control col-md-7 col-xs-12">
                                    </div>
                                </div>

                                <div class="form-group col-md-12 col-md-offset-3">
                                    <label class="col-md-2">Mật khẩu <span class="required">*</span>
                                    </label>
                                    <div class="col-md-3">
                                        <input type="password" name="matkhau" required="required" class="form-control col-md-7 col-xs-12">
                                    </div>
                                </div>


                                <div class="form-group col-md-12 col-md-offset-3">
                                    <div class="col-md-12 col-md-offset-3"> 
                                        <button class="main-btn" type="reset">Reset</button>
                                        <button type="submit" class="primary-btn" name="submit" value="login">Đăng nhập</button>
                                    </div>
                                </div>
                            </div> 
                    </form>


                </c:otherwise>
            </c:choose> 

        </div>
    </div>
    <!-- /row -->
</div>
<!-- /container -->
</div>
<!-- /section -->






<jsp:include page="theme/footer.jsp" />
