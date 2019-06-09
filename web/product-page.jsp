<%-- 
    Document   : product-page
    Created on : Mar 11, 2019, 10:46:06 PM
    Author     : MITICC06
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/theme/header.jsp" />

            <c:set var="currentDate" value="${curentTimeStamp}" />
<!-- BREADCRUMB -->
<div id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="${contextPath}/">Trang chủ</a></li> 
            <li><a href="/categories?id=${sach.getMaTheLoai()}">Category</a></li>
            <li class="active">${sach.getTenSach()}</li>
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
            <!--  Product Details -->
            <div class="product product-details clearfix">
                <div class="col-md-6">
                    <div id="product-main-view">
                        <div class="product-view">
                            <img src="${contextPath}/${sach.getAnhDaiDien()}" alt="">
                        </div>

                    </div>

                </div>
                <div class="col-md-6">
                    <div class="product-body">
                        <div class="product-label">
                            <c:if test="${sach.getPhanTramGiamGia()!=0}">
                                
                                    <fmt:formatNumber var="lamtron"
                                                      value="${sach.getPhanTramGiamGia()}"
                                                      maxFractionDigits="0" />

                                    <fmt:formatNumber var="khonglamtron"
                                                      value="${sach.getPhanTramGiamGia()}"
                                                      maxFractionDigits="10" />
                                <c:choose>
                                        <c:when test="${sach.getNgayBatDauGiamGia() <= currentDate}">
                                            <c:if test="${sach.getNgayKetThucGiamGia() >= currentDate}">

                                                <span class="new">SALE</span>

                                                <span class="sale">
                                                    <c:choose>

                                                        <c:when test="${lamtron != khonglamtron}">

                                                            ${khonglamtron}
                                                        </c:when> 
                                                        <c:otherwise>

                                                            ${lamtron}
                                                        </c:otherwise>
                                                    </c:choose>
                                                    %
                                                </span>    
                                            </c:if>


                                        </c:when>
                                        <c:otherwise>

                                        </c:otherwise>
                                    </c:choose>
                            </c:if>

                        </div>
                        <h2 class="product-name">${ sach.getTenSach() }</h2>

                        <fmt:formatNumber var="giagoc"
                                          value="${sach.getGiaBan()}"
                                          maxFractionDigits="0" 
                                          groupingUsed="true" />
                        <fmt:formatNumber var="giagocsaugiamgia"
                                          value="${(100-sach.getPhanTramGiamGia())*0.01*sach.getGiaBan()}"
                                          maxFractionDigits="0" 
                                          groupingUsed="true"/>

                        <c:choose>
                            <c:when test="${sach.getPhanTramGiamGia()==0}">
                                <h3 class="product-price">
                                                    ${giagoc} </h3>
                               
                            </c:when> 
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${sach.getNgayBatDauGiamGia() <= currentDate}">
                                        <c:choose>
                                            <c:when test="${sach.getNgayKetThucGiamGia() >= currentDate}">
                                                <h3 class="product-price">
                                                    ${giagocsaugiamgia}
                                                    <del class="product-old-price">
                                                                            ${giagoc}
                        
                                                        
                                                    </del>
                                                </h3>

                                            </c:when>
                                            <c:otherwise>
                                                <h3 class="product-price">${giagoc}
                                                    
                                                </h3>

                                            </c:otherwise>
                                        </c:choose>

                                    </c:when>
                                    <c:otherwise>
                                        <h3 class="product-price">${giagoc} </h3>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>

                            
                                   

                        

                        <p> 
                            <c:choose>
                                <c:when test="${sach.getSoLuongTon() > 0}">
                                    <b>Số lượng tồn:</b> ${sach.getSoLuongTon()}
                                </c:when> 
                                <c:otherwise> 
                                    Sản phẩm đã hết hàng.
                                </c:otherwise>
                            </c:choose> 

                        </p>
                        <p><strong>Tác giả: </strong><a href="${contextPath}/tacgia?tentacgia=${sach.getTenTacGia()}">${ sach.getTenTacGia()}</a> </p>
                        <p><strong>Năm xuất bản: </strong>${sach.getNamXuatBan()}</p>
                        <!--<p><strong>Thể loại sách: </strong><a href="${contextPath}/categories?id=${sach.getMaTheLoai()}"></a> </p>-->


                        <c:choose>
                            <c:when test="${sach.getTrangThai() == 1}">
                                <div class="product-btns">
                                    <div class="qty-input">
                                        <span class="text-uppercase">QTY: </span>
                                        <input class="input" type="number" value="1">
                                    </div>
                                    <button class="primary-btn add-to-cart" data-id="${sach.getMaSach()}"><i class="fa fa-shopping-cart"></i> Add to Cart</button>
                                    <div class="pull-right">
                                        <button class="main-btn icon-btn"><i class="fa fa-share-alt"></i></button>
                                    </div>
                                </div>
                            </c:when> 
                            <c:otherwise> 
                                <div class="primary-btn add-to-cart">Sản phẩm đã ngưng kinh doanh</div>

                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>
                <div class="col-md-12">
                    <div class="product-tab">
                        <ul class="tab-nav">
                            <li class="active"><a data-toggle="tab" href="#tab1">Mô tả sản phẩm</a></li>
                            <!--<li><a data-toggle="tab" href="#tab1">Details</a></li>-->
                        </ul>
                        <div class="tab-content">
                            <div id="tab1" class="tab-pane fade in active">
                                <p>${ sach.getMoTa() }</p>
                            </div> 
                        </div>
                    </div>
                </div>

            </div>
            <!-- /Product Details -->
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<!-- /section -->

<!-- section -->
<div class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row">
            <!-- section title -->
            <div class="col-md-12">
                <div class="section-title">
                    <h2 class="title">Picked For You</h2>
                </div>
            </div>
            <!-- section title -->



            <c:forEach items="${listSach}" var="obj">
                <!-- Product Single -->
                <div class="col-md-3 col-sm-6 col-xs-6">
                    <div class="product product-single">
                        <div class="product-thumb">
                            <div class="product-label">


                                <c:if test="${obj.getPhanTramGiamGia()!=0}">

                                    <fmt:formatNumber var="lamtron"
                                                      value="${obj.getPhanTramGiamGia()}"
                                                      maxFractionDigits="0"
                                                      groupingUsed="false"/>

                                    <fmt:formatNumber var="khonglamtron"
                                                      value="${obj.getPhanTramGiamGia()}"
                                                      maxFractionDigits="10"
                                                      groupingUsed="false"/>



                                    <c:choose>
                                        <c:when test="${obj.getNgayBatDauGiamGia() <= currentDate}">
                                            <c:if test="${obj.getNgayKetThucGiamGia() >= currentDate}">


                                                <span class="new">SALE</span>

                                                <span class="sale">
                                                    <c:choose>

                                                        <c:when test="${lamtron != khonglamtron}">

                                                            ${khonglamtron}
                                                        </c:when> 
                                                        <c:otherwise>

                                                            ${lamtron}
                                                        </c:otherwise>
                                                    </c:choose>
                                                    %
                                                </span>    
                                            </c:if>


                                        </c:when>
                                        <c:otherwise>

                                        </c:otherwise>
                                    </c:choose>


                                </c:if>


                            </div>
                            <a href="${contextPath}/product?masach=${obj.getMaSach()}" class="main-btn quick-view"><i  class="fa fa-search-plus"></i> Quick view</a>
                            <img src="${obj.getAnhDaiDien()}" alt="">
                        </div>
                        <div class="product-body">

                            <h3 class="product-price">
                                <fmt:formatNumber var="giagoc"
                                                  value="${obj.getGiaBan()}"
                                                  maxFractionDigits="0" 
                                                  groupingUsed="true" />
                                <fmt:formatNumber var="giagocsaugiamgia"
                                                  value="${(100-obj.getPhanTramGiamGia())*0.01*obj.getGiaBan()}"
                                                  maxFractionDigits="0" 
                                                  groupingUsed="true"/>




                                <c:choose>
                                    <c:when test="${obj.getPhanTramGiamGia()==0}">
                                        ${giagoc}
                                    </c:when> 
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${obj.getNgayBatDauGiamGia() <= currentDate}">
                                                <c:choose>
                                                    <c:when test="${obj.getNgayKetThucGiamGia() >= currentDate}">
                                                        ${giagocsaugiamgia}

                                                    </c:when>
                                                    <c:otherwise>

                                                        ${giagoc}
                                                    </c:otherwise>
                                                </c:choose>

                                            </c:when>
                                            <c:otherwise>
                                                ${giagoc}
                                            </c:otherwise>
                                        </c:choose>
                                    </c:otherwise>
                                </c:choose>

                                <del class="product-old-price">
                                    <c:if test="${obj.getPhanTramGiamGia()!=0}">
                                        <c:choose>
                                            <c:when test="${obj.getNgayBatDauGiamGia() <= currentDate}">
                                                <c:if test="${obj.getNgayKetThucGiamGia() >= currentDate}">

                                                    ${giagoc}
                                                </c:if>
                                            </c:when>
                                        </c:choose>
                                    </c:if>

                                </del>



                            </h3>
                          
                            <h2 class="product-name"><a href="${contextPath}/product?masach=${obj.getMaSach()}">${obj.getTenSach()}</a></h2>
                            <div class="product-btns">
                                <button class="primary-btn add-to-cart" data-id="${obj.getMaSach()}" data-linkanh="${obj.getAnhDaiDien()}" data-name="${obj.getTenSach()}" data-price="${obj.getGiaBan()}"><i class="fa fa-shopping-cart"></i> Add to Cart</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /Product Single -->

            </c:forEach>
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<!-- /section -->


<jsp:include page="/theme/footer.jsp" />
