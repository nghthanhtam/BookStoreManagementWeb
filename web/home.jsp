<%-- 
    Document   : index
    Created on : Mar 10, 2019, 4:08:36 PM
    Author     : MITICC06
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
    

<jsp:include page="theme/header.jsp" />

	<!-- HOME -->
	<div id="home">
		<!-- container -->
		<div class="container">
			<!-- home wrap -->
			<div class="home-wrap">
				<!-- home slick -->
				<div id="home-slick">
					<!-- banner -->
					<div class="banner banner-1">
						<img src="./img/banner01.jpg" alt="">
						<div class="banner-caption text-center">
							<h1>${txtTitle}</h1>
							<h3 class="white-color font-weak">Up to 50% Discount</h3>
							<button class="primary-btn">Shop Now</button>
						</div>
					</div>
					<!-- /banner -->

					<!-- banner -->
					<div class="banner banner-1">
						<img src="./img/banner02.jpg" alt="">
						<div class="banner-caption">
							<h1 class="primary-color">HOT DEAL<br><span class="white-color font-weak">Up to 50% OFF</span></h1>
							<button class="primary-btn">Shop Now</button>
						</div>
					</div>
					<!-- /banner -->

					<!-- banner -->
					<div class="banner banner-1">
						<img src="./img/banner03.jpg" alt="">
						<div class="banner-caption">
							<h1 class="white-color">New Product <span>Collection</span></h1>
							<button class="primary-btn">Shop Now</button>
						</div>
					</div>
					<!-- /banner -->
				</div>
				<!-- /home slick -->
			</div>
			<!-- /home wrap -->
		</div>
		<!-- /container -->
	</div>
	<!-- /HOME -->

	<!-- section -->
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<!-- banner -->
				<div class="col-md-4 col-sm-6">
					<a class="banner banner-1" href="#">
						<img src="./img/banner10.jpg" alt="">
						<div class="banner-caption text-center">
							<h2 class="white-color">NEW COLLECTION</h2>
						</div>
					</a>
				</div>
				<!-- /banner -->

				<!-- banner -->
				<div class="col-md-4 col-sm-6">
					<a class="banner banner-1" href="#">
						<img src="./img/banner11.jpg" alt="">
						<div class="banner-caption text-center">
							<h2 class="white-color">NEW COLLECTION</h2>
						</div>
					</a>
				</div>
				<!-- /banner -->

				<!-- banner -->
				<div class="col-md-4 col-md-offset-0 col-sm-6 col-sm-offset-3">
					<a class="banner banner-1" href="#">
						<img src="./img/banner12.jpg" alt="">
						<div class="banner-caption text-center">
							<h2 class="white-color">NEW COLLECTION</h2>
						</div>
					</a>
				</div>
				<!-- /banner -->

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
				<!-- section-title -->
				<div class="col-md-12">
					<div class="section-title">
						<h2 class="title">Sách mới nhất</h2>
						<div class="pull-right">
							<div class="product-slick-dots-1 custom-dots"></div>
						</div>
					</div>
				</div>
				<!-- /section-title -->

                                <!-- Product Single -->
				<div class="col-md-3 col-sm-6 col-xs-6">
					<div class="product product-single product-hot">
						<div class="product-thumb">
							<div class="product-label">
                                                            	<span class="new">NEW</span>
                                                                <c:if test="${sachMoiNhat.getPhanTramGiamGia()!=0}">
                                                                                        <span class="sale">
                                                                                            <fmt:formatNumber var="lamtron"
                                                                                            value="${sachMoiNhat.getPhanTramGiamGia()}"
                                                                                            maxFractionDigits="0"
                                                                                            groupingUsed="false"/>
                                                                                            
                                                                                            <fmt:formatNumber var="khonglamtron"
                                                                                            value="${sachMoiNhat.getPhanTramGiamGia()}"
                                                                                            maxFractionDigits="10"
                                                                                            groupingUsed="false"/>
                                                                                            
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
							<ul class="product-countdown">
								<li><span>00 H</span></li>
								<li><span>00 M</span></li>
								<li><span>00 S</span></li>
							</ul>
							<a href="${contextPath}/product?masach=${sachMoiNhat.getMaSach()}" class="main-btn quick-view"><i  class="fa fa-search-plus"></i> Quick view</a>
							<img src="${sachMoiNhat.getAnhDaiDien()}" alt="">
						</div>
						<div class="product-body">
                                                    
							<h3 class="product-price">
                                                            <fmt:formatNumber var="giagoc"
                                                                              value="${sachMoiNhat.getGiaBan()}"
                                                                              maxFractionDigits="0" 
                                                                              groupingUsed="true" />
                                                            <fmt:formatNumber var="giagocsaugiamgia"
                                                                              value="${(100-sachMoiNhat.getPhanTramGiamGia())*0.01*sachMoiNhat.getGiaBan()}"
                                                                              maxFractionDigits="0" 
                                                                              groupingUsed="true"/>
                                                            
                                                            
                                                            
                                                            
                                                            <c:choose>
                                                                                                <c:when test="${sachMoiNhat.getPhanTramGiamGia()==0}">
                                                                                                    ${giagoc}
                                                                                                </c:when> 
                                                                                                <c:otherwise>
                                                                                                    ${giagocsaugiamgia}
                                                                                                </c:otherwise>
                                                                                            </c:choose>

                                                            <del class="product-old-price">
                                                                <c:if test="${sachMoiNhat.getPhanTramGiamGia()!=0}">
                                                                                        ${giagoc}
                                                                                    </c:if>
                                                                
                                                            </del>
                                                        </h3>
							<div class="product-rating">
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star-o empty"></i>
							</div>
							<h2 class="product-name"><a href="${contextPath}/product?masach=${sachMoiNhat.getMaSach()}">${sachMoiNhat.getTenSach()}</a></h2>
							<div class="product-btns">
								<button class="main-btn icon-btn"><i class="fa fa-heart"></i></button>
								<button class="main-btn icon-btn"><i class="fa fa-exchange"></i></button>
								<button class="primary-btn add-to-cart" data-id="${sachMoiNhat.getMaSach()}" data-name="${sachMoiNhat.getTenSach()}" data-price="${sachMoiNhat.getGiaBan()}"><i class="fa fa-shopping-cart"></i> Add to Cart</button>
							</div>
						</div>
					</div>
				</div>
				<!-- /Product Single -->

				<!-- Product Slick -->
				<div class="col-md-9 col-sm-6 col-xs-6">
					<div class="row">
						<div id="product-slick-1" class="product-slick">
                                                    <c:forEach items="${listSachMoiNhat}" var="obj">
                                
							<!-- Product Single -->
							<div class="product product-single">
								<div class="product-thumb">
									<div class="product-label">
										<span class="new">NEW</span>
                                                                <c:if test="${obj.getPhanTramGiamGia()!=0}">
                                                                                        <span class="sale">
                                                                                            <fmt:formatNumber var="lamtron"
                                                                                            value="${obj.getPhanTramGiamGia()}"
                                                                                            maxFractionDigits="0"
                                                                                            groupingUsed="false"/>
                                                                                            
                                                                                            <fmt:formatNumber var="khonglamtron"
                                                                                            value="${obj.getPhanTramGiamGia()}"
                                                                                            maxFractionDigits="10"
                                                                                            groupingUsed="false"/>
                                                                                            
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
									<ul class="product-countdown">
										<li><span>00 H</span></li>
										<li><span>00 M</span></li>
										<li><span>00 S</span></li>
									</ul>
									<a href="${contextPath}/product?masach=${obj.getMaSach()}" class="main-btn quick-view"><i class="fa fa-search-plus"></i> Quick view</a>
									<img src="${obj.getAnhDaiDien()}" alt="">
								</div>
								<div class="product-body">
									<h3 class="product-price">
                                                                            <fmt:formatNumber var="giagoc"
                                                                              value="${obj.getGiaBan()}"
                                                                              maxFractionDigits="0" 
                                                                              groupingUsed="true" />
                                                            <fmt:formatNumber var="giagocsaugiamgia"
                                                                              value="${(100-sachMoiNhat.getPhanTramGiamGia())*0.01*obj.getGiaBan()}"
                                                                              maxFractionDigits="0" 
                                                                              groupingUsed="true"/>
                                                            
                                                            
                                                            
                                                            
                                                            <c:choose>
                                                                                                <c:when test="${obj.getPhanTramGiamGia()==0}">
                                                                                                    ${giagoc}
                                                                                                </c:when> 
                                                                                                <c:otherwise>
                                                                                                    ${giagocsaugiamgia}
                                                                                                </c:otherwise>
                                                                                            </c:choose>

                                                            <del class="product-old-price">
                                                                <c:if test="${obj.getPhanTramGiamGia()!=0}">
                                                                                        ${giagoc}
                                                                                    </c:if>
                                                                
                                                            </del>
                                                                        </h3>
									<div class="product-rating">
										<i class="fa fa-star"></i>
										<i class="fa fa-star"></i>
										<i class="fa fa-star"></i>
										<i class="fa fa-star"></i>
										<i class="fa fa-star-o empty"></i>
									</div>
									<h2 class="product-name"><a href="${contextPath}/product?masach=${obj.getMaSach()}">${obj.getTenSach()}</a></h2>
									<div class="product-btns">
										<button class="main-btn icon-btn"><i class="fa fa-heart"></i></button>
										<button class="main-btn icon-btn"><i class="fa fa-exchange"></i></button>
										<button class="primary-btn add-to-cart" data-name="${obj.getTenSach()}" data-price="${obj.getGiaBan()}"><i class="fa fa-shopping-cart"></i> Add to Cart</button>
									</div>
								</div>
							</div>
							<!-- /Product Single -->
                                                            
                                                    </c:forEach>
						</div>
					</div>
				</div>
				<!-- /Product Slick -->
			</div>
			<!-- /row -->

			<!-- row -->
			<div class="row">
				<!-- section title -->
				<div class="col-md-12">
					<div class="section-title">
						<h2 class="title">Deals Of The Day</h2>
						<div class="pull-right">
							<div class="product-slick-dots-2 custom-dots">
							</div>
						</div>
					</div>
				</div>
				<!-- section title -->

				<!-- Product Single -->
				<div class="col-md-3 col-sm-6 col-xs-6">
					<div class="product product-single product-hot">
						<div class="product-thumb">
							<div class="product-label">
                                                            <c:if test="${sachGiamGiaNhat.getPhanTramGiamGia()!=0}">
                                                                
                                                                                        <span >SALE</span>
                                                                                        <span class="sale">
                                                                                            <fmt:formatNumber var="lamtron"
                                                                                            value="${sachGiamGiaNhat.getPhanTramGiamGia()}"
                                                                                            maxFractionDigits="0"
                                                                                            groupingUsed="false"/>
                                                                                            
                                                                                            <fmt:formatNumber var="khonglamtron"
                                                                                            value="${sachGiamGiaNhat.getPhanTramGiamGia()}"
                                                                                            maxFractionDigits="10"
                                                                                            groupingUsed="false"/>
                                                                                            
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
							<ul class="product-countdown">
								<li><span>00 H</span></li>
								<li><span>00 M</span></li>
								<li><span>00 S</span></li>
							</ul>
							<a href="${contextPath}/product?masach=${sachGiamGiaNhat.getMaSach()}" class="main-btn quick-view"><i class="fa fa-search-plus"></i> Quick view</a>
							<img src="${sachGiamGiaNhat.getAnhDaiDien()}" alt="">
						</div>
						<div class="product-body">
							<h3 class="product-price">
                                                            <fmt:formatNumber var="giagoc"
                                                                              value="${sachGiamGiaNhat.getGiaBan()}"
                                                                              maxFractionDigits="0" 
                                                                              groupingUsed="true" />
                                                            <fmt:formatNumber var="giagocsaugiamgia"
                                                                              value="${(100-sachGiamGiaNhat.getPhanTramGiamGia())*0.01*sachGiamGiaNhat.getGiaBan()}"
                                                                              maxFractionDigits="0" 
                                                                              groupingUsed="true"/>
                                                            
                                                            
                                                            
                                                            
                                                            <c:choose>
                                                                                                <c:when test="${sachGiamGiaNhat.getPhanTramGiamGia()==0}">
                                                                                                    ${giagoc}
                                                                                                </c:when> 
                                                                                                <c:otherwise>
                                                                                                    ${giagocsaugiamgia}
                                                                                                </c:otherwise>
                                                                                            </c:choose>

                                                            <del class="product-old-price">
                                                                <c:if test="${sachGiamGiaNhat.getPhanTramGiamGia()!=0}">
                                                                                        ${giagoc}
                                                                                    </c:if>
                                                                
                                                            </del>
                                                        </h3>
							<div class="product-rating">
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star-o empty"></i>
							</div>
							<h2 class="product-name"><a href="${contextPath}/product?masach=${sachGiamGiaNhat.getMaSach()}">${sachGiamGiaNhat.getTenSach()}</a></h2>
							<div class="product-btns">
								<button class="main-btn icon-btn"><i class="fa fa-heart"></i></button>
								<button class="main-btn icon-btn"><i class="fa fa-exchange"></i></button>
								<button class="primary-btn add-to-cart" data-name="${sachGiamGiaNhat.getTenSach()}" data-price="${sachGiamGiaNhat.getGiaBan()}"><i class="fa fa-shopping-cart"></i> Add to Cart</button>
							</div>
						</div>
					</div>
				</div>
				<!-- /Product Single -->

				<!-- Product Slick -->
				<div class="col-md-9 col-sm-6 col-xs-6">
					<div class="row">
						<div id="product-slick-2" class="product-slick">
							
                                                        <c:forEach items="${listSachGiamGia}" var="obj">
                                
							<!-- Product Single -->
							<div class="product product-single">
								<div class="product-thumb">
									<div class="product-label">
                                                                <c:if test="${obj.getPhanTramGiamGia()!=0}">
										<span class="new">SALE</span>
                                                                                        <span class="sale">
                                                                                            <fmt:formatNumber var="lamtron"
                                                                                            value="${obj.getPhanTramGiamGia()}"
                                                                                            maxFractionDigits="0"
                                                                                            groupingUsed="false"/>
                                                                                            
                                                                                            <fmt:formatNumber var="khonglamtron"
                                                                                            value="${obj.getPhanTramGiamGia()}"
                                                                                            maxFractionDigits="10"
                                                                                            groupingUsed="false"/>
                                                                                            
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
									<ul class="product-countdown">
										<li><span>00 H</span></li>
										<li><span>00 M</span></li>
										<li><span>00 S</span></li>
									</ul>
									<a href="${contextPath}/product?masach=${obj.getMaSach()}" class="main-btn quick-view"><i class="fa fa-search-plus"></i> Quick view</a>
									<img src="${obj.getAnhDaiDien()}" alt="">
								</div>
								<div class="product-body">
									<h3 class="product-price">
                                                                            <fmt:formatNumber var="giagoc"
                                                                              value="${obj.getGiaBan()}"
                                                                              maxFractionDigits="0" 
                                                                              groupingUsed="true" />
                                                                            <fmt:formatNumber var="giagocsaugiamgia"
                                                                              value="${(100-sachMoiNhat.getPhanTramGiamGia())*0.01*obj.getGiaBan()}"
                                                                              maxFractionDigits="0" 
                                                                              groupingUsed="true"/>
                                                            
                                                            
                                                            
                                                            
                                                            <c:choose>
                                                                                                <c:when test="${obj.getPhanTramGiamGia()==0}">
                                                                                                    ${giagoc}
                                                                                                </c:when> 
                                                                                                <c:otherwise>
                                                                                                    ${giagocsaugiamgia}
                                                                                                </c:otherwise>
                                                                                            </c:choose>

                                                            <del class="product-old-price">
                                                                <c:if test="${obj.getPhanTramGiamGia()!=0}">
                                                                                        ${giagoc}
                                                                                    </c:if>
                                                                
                                                            </del>
                                                                        </h3>
									<div class="product-rating">
										<i class="fa fa-star"></i>
										<i class="fa fa-star"></i>
										<i class="fa fa-star"></i>
										<i class="fa fa-star"></i>
										<i class="fa fa-star-o empty"></i>
									</div>
									<h2 class="product-name"><a href="${contextPath}/product?masach=${obj.getMaSach()}">${obj.getTenSach()}</a></h2>
									<div class="product-btns">
										<button class="main-btn icon-btn"><i class="fa fa-heart"></i></button>
										<button class="main-btn icon-btn"><i class="fa fa-exchange"></i></button>
										<button class="primary-btn add-to-cart" data-name="${obj.getTenSach()}" data-price="${obj.getGiaBan()}"><i class="fa fa-shopping-cart"></i> Add to Cart</button>
									</div>
								</div>
							</div>
							<!-- /Product Single -->

                                                    </c:forEach>

						</div>
					</div>
				</div>
				<!-- /Product Slick -->
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /section -->

	<!-- section -->
	<div class="section section-grey">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<!-- banner -->
				<div class="col-md-8">
					<div class="banner banner-1">
						<img src="./img/banner13.jpg" alt="">
						<div class="banner-caption text-center">
							<h1 class="primary-color">HOT DEAL<br><span class="white-color font-weak">Up to 50% OFF</span></h1>
							<button class="primary-btn">Shop Now</button>
						</div>
					</div>
				</div>
				<!-- /banner -->

				<!-- banner -->
				<div class="col-md-4 col-sm-6">
					<a class="banner banner-1" href="#">
						<img src="./img/banner11.jpg" alt="">
						<div class="banner-caption text-center">
							<h2 class="white-color">NEW COLLECTION</h2>
						</div>
					</a>
				</div>
				<!-- /banner -->

				<!-- banner -->
				<div class="col-md-4 col-sm-6">
					<a class="banner banner-1" href="#">
						<img src="./img/banner12.jpg" alt="">
						<div class="banner-caption text-center">
							<h2 class="white-color">NEW COLLECTION</h2>
						</div>
					</a>
				</div>
				<!-- /banner -->
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
						<h2 class="title">Latest Products</h2>
					</div>
				</div>
				<!-- section title -->

				<!-- Product Single -->
				<div class="col-md-3 col-sm-6 col-xs-6">
					<div class="product product-single">
						<div class="product-thumb">
							<button class="main-btn quick-view"><i class="fa fa-search-plus"></i> Quick view</button>
							<img src="./img/product01.jpg" alt="">
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
							<img src="./img/product03.jpg" alt="">
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
							</div>
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
			</div>
			<!-- /row -->

			<!-- row -->
			<div class="row">
				<!-- banner -->
				<div class="col-md-3 col-sm-6 col-xs-6">
					<div class="banner banner-2">
						<img src="./img/banner15.jpg" alt="">
						<div class="banner-caption">
							<h2 class="white-color">NEW<br>COLLECTION</h2>
							<button class="primary-btn">Shop Now</button>
						</div>
					</div>
				</div>
				<!-- /banner -->
                        
				<!-- Product Single -->
				<div class="col-md-3 col-sm-6 col-xs-6">
					<div class="product product-single">
						<div class="product-thumb">
							<div class="product-label">
								<span>New</span>
								<span class="sale">-20%</span>
							</div>
							<button class="main-btn quick-view"><i class="fa fa-search-plus"></i> Quick view</button>
							<img src="./img/product07.jpg" alt="">
						</div>
						<div class="product-body">
							<h3 class="product-price">$30 <del class="product-old-price">$45.00</del></h3>
							<div class="product-rating">
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star-o empty"></i>
							</div>
							<h2 class="product-name"><a href="#">Product name goes here</a></h2>
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
							<img src="./img/product06.jpg" alt="">
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
							<img src="./img/product05.jpg" alt="">
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

	<jsp:include page="theme/footer.jsp" />


</body>
</html>