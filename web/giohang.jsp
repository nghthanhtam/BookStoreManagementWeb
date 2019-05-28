<%-- 
    Document   : giohang
    Created on : May 18, 2019, 8:43:09 AM
    Author     : TamTorres
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="theme/header.jsp" />
<%@page import="com.google.gson.Gson"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

	<title>Giỏ hàng</title>

	<!-- Google font -->
	<link href="https://fonts.googleapis.com/css?family=Hind:400,700" rel="stylesheet">

	<!-- Bootstrap -->
	<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />

	<!-- Slick -->
	<link type="text/css" rel="stylesheet" href="css/slick.css" />
	<link type="text/css" rel="stylesheet" href="css/slick-theme.css" />

	<!-- nouislider -->
	<link type="text/css" rel="stylesheet" href="css/nouislider.min.css" />

	<!-- Font Awesome Icon -->
	<link rel="stylesheet" href="css/font-awesome.min.css">

	<!-- Custom stlylesheet -->
	<link type="text/css" rel="stylesheet" href="css/style.css" />

	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

</head>

<body>

	
	<!-- BREADCRUMB -->
	<div id="breadcrumb">
		<div class="container">
			<ul class="breadcrumb">
				<li><a href="#">Home</a></li>
				<li class="active">Checkout</li>
			</ul>
		</div>
	</div>
	<!-- /BREADCRUMB -->

	<!-- section -->
	<div class="section" type="hidden">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<form id="checkout-form" class="clearfix" >
		
					<div class="col-md-12">
						<div class="order-summary clearfix">
							<div class="section-title">
								<h3 class="title">Giỏ hàng của tôi</h3>
							</div>
							<table class="shopping-cart-table table">
								<thead>
									<tr>
										<th>Sản phẩm</th>
										<th></th>
										<th class="text-center">Giá</th>
										<th class="text-center">Số lượng</th>
										<th class="text-center">Tổng tiền</th>
										<th class="text-right"></th>
									</tr>
								</thead>
								
                                                                <tbody class="giohang">
                                                                
								<!--cart details here-->
                                                             
								</tbody>
                                                          
                                                                
								<tfoot>
									<tr>
										<th class="empty" colspan="3"></th>
										<th>PHÍ SHIP</th>
										<td colspan="2">Free Shipping</td>
									</tr>
									<tr>
										<th class="empty" colspan="3"></th>
										<th>TỔNG TIỀN</th>
                                                                                <th colspan="2" class="total"><span>0</span></th>
									</tr>
								</tfoot>
							</table>                                        
                                                 
                                                        <div class="pull-right">  
                                                         
                                                            <button class="primary-btn-dathang"><a href="${contextPath}/checkout">ĐẶT HÀNG</a></button>
                                                        </div>
                                               
						</div>

					</div>
				</form>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /section -->
<jsp:include page="theme/footer.jsp" />
	
	
</body>

</html>
