<%-- 
    Document   : product-page
    Created on : Mar 11, 2019, 10:46:06 PM
    Author     : MITICC06
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/theme/header.jsp" />

<!-- BREADCRUMB -->
<div id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="${contextPath}/">Trang chủ</a></li> 
            <li><a href="#">Category</a></li>
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
                                <span>SALE</span>
                                <span class="sale">
                                    <fmt:formatNumber var="lamtron"
                                                      value="${sach.getPhanTramGiamGia()}"
                                                      maxFractionDigits="0" />

                                    <fmt:formatNumber var="khonglamtron"
                                                      value="${sach.getPhanTramGiamGia()}"
                                                      maxFractionDigits="10" />

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

                        </div>
                        <h2 class="product-name">${ sach.getTenSach() }</h2>
                        <h3 class="product-price">${ sach.getGiaBan() } </h3>

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
                                    <button class="primary-btn add-to-cart" data-id="${sach.getMaSach()}" data-linkanh="${sach.getAnhDaiDien()}" data-name="${sach.getTenSach()}" data-price="${sach.getGiaBan()}"><i class="fa fa-shopping-cart"></i> Add to Cart</button>
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

            <!-- Product Single -->
            <div class="col-md-3 col-sm-6 col-xs-6">
                <div class="product product-single">
                    <div class="product-thumb">
                        <button class="main-btn quick-view"><i class="fa fa-search-plus"></i> Quick view</button>
                        <img src="./img/product04.jpg" alt="">
                    </div>
                    <div class="product-body">
                        <h3 class="product-price">$32.50</h3>
                        <div class="product-rating">
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star-o empty"></i>
                        </div>
                        <h2 class="product-name"><a href="#">Product Name Goes Here</a></h2>
                        <div class="product-btns">
                            <button class="main-btn icon-btn"><i class="fa fa-heart"></i></button>
                            <button class="main-btn icon-btn"><i class="fa fa-exchange"></i></button>
                            <button class="primary-btn add-to-cart"><i class="fa fa-shopping-cart"></i> Add to Cart</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /Product Single -->

            <!-- Product Single -->
            <div class="col-md-3 col-sm-6 col-xs-6">
                <div class="product product-single">
                    <div class="product-thumb">
                        <div class="product-label">
                            <span>New</span>
                        </div>
                        <button class="main-btn quick-view"><i class="fa fa-search-plus"></i> Quick view</button>
                        <img src="./img/product03.jpg" alt="">
                    </div>
                    <div class="product-body">
                        <h3 class="product-price">$32.50</h3>
                        <div class="product-rating">
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star-o empty"></i>
                        </div>
                        <h2 class="product-name"><a href="#">Product Name Goes Here</a></h2>
                        <div class="product-btns">
                            <button class="main-btn icon-btn"><i class="fa fa-heart"></i></button>
                            <button class="main-btn icon-btn"><i class="fa fa-exchange"></i></button>
                            <button class="primary-btn add-to-cart"><i class="fa fa-shopping-cart"></i> Add to Cart</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /Product Single -->

            <!-- Product Single -->
            <div class="col-md-3 col-sm-6 col-xs-6">
                <div class="product product-single">
                    <div class="product-thumb">
                        <div class="product-label">
                            <span class="sale">-20%</span>
                        </div>
                        <button class="main-btn quick-view"><i class="fa fa-search-plus"></i> Quick view</button>
                        <img src="./img/product02.jpg" alt="">
                    </div>
                    <div class="product-body">
                        <h3 class="product-price">$32.50 <del class="product-old-price">$45.00</del></h3>
                        <div class="product-rating">
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star-o empty"></i>
                        </div>
                        <h2 class="product-name"><a href="#">Product Name Goes Here</a></h2>
                        <div class="product-btns">
                            <button class="main-btn icon-btn"><i class="fa fa-heart"></i></button>
                            <button class="main-btn icon-btn"><i class="fa fa-exchange"></i></button>
                            <button class="primary-btn add-to-cart"><i class="fa fa-shopping-cart"></i> Add to Cart</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /Product Single -->

            <!-- Product Single -->
            <div class="col-md-3 col-sm-6 col-xs-6">
                <div class="product product-single">
                    <div class="product-thumb">
                        <div class="product-label">
                            <span>New</span>
                            <span class="sale">-20%</span>
                        </div>
                        <button class="main-btn quick-view"><i class="fa fa-search-plus"></i> Quick view</button>
                        <img src="./img/product01.jpg" alt="">
                    </div>
                    <div class="product-body">
                        <h3 class="product-price">$32.50 <del class="product-old-price">$45.00</del></h3>
                        <div class="product-rating">
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star-o empty"></i>
                        </div>
                        <h2 class="product-name"><a href="#">Product Name Goes Here</a></h2>
                        <div class="product-btns">
                            <button class="main-btn icon-btn"><i class="fa fa-heart"></i></button>
                            <button class="main-btn icon-btn"><i class="fa fa-exchange"></i></button>
                            <button class="primary-btn add-to-cart"><i class="fa fa-shopping-cart"></i> Add to Cart</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /Product Single -->
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<!-- /section -->


<jsp:include page="/theme/footer.jsp" />
