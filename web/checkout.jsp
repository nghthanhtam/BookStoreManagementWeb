<%-- 
    Document   : checkout
    Created on : May 19, 2019, 7:23:50 AM
    Author     : TamTorres
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="theme/header.jsp" />
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
        
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

	<title>E-SHOP HTML Template</title>

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

<script type="text/javascript">
      var listCTDonHang = (localStorage.getItem("obj"));
      
</script>


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
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<form id="checkout-form" class="clearfix" method="POST" action="/checkout">
					<div class="col-md-6">
						<div class="billing-details">
							<p>Already a customer ? <a href="#">Login</a></p>
							<div class="section-title">
								<h3 class="title">Địa chỉ giao hàng</h3>
							</div>
							<div class="form-group">
								<input class="input" type="text" name="first-name" placeholder="Họ tên">
							</div>
							<div class="form-group">
								<input class="input" type="email" name="email" placeholder="Email">
							</div>
							<div class="form-group">
								<input class="input" type="text" name="address" placeholder="Địa chỉ">
							</div>
                                                        
                                                        <div class="form-group">
                                                            <select id="selectphiship" class="form-control" required="" name="maphiship">
                                                                
                                                                <c:forEach items="${listPhiShip}" var="obj">                                                                  
                                                                    <option value="${obj.getMaPhiShip()}" selected>${obj.getTenTinh()} - ${obj.getPhiShip()}</option>                      
                                                                </c:forEach>
                                                                    
                                                                <option value="">Tỉnh/Thành phố</option>
                                                            </select>
							
                                                        </div>

							<div class="form-group">
								<input class="input" type="tel" name="tel" placeholder="Điện thoại">
							</div>
                                                        <div class="form-group">
                                                            <input class="input" type="text" name="comment" placeholder="Ghi chú">
							</div>
                                                         <div class="form-group">
                                                            <input class="input" type="hidden" name="listctdonhang" id="listctdonhang">
							</div>
							<div class="form-group">
								<div class="input-checkbox">
									<input type="checkbox" id="register">
									<label class="font-weak" for="register">Create Account?</label>
									<div class="caption">
										<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt.
											<p>
												<input class="input" type="password" name="password" placeholder="Enter Your Password">
									</div>
								</div>
							</div>
						</div>
					</div>
<script type="text/javascript">
      document.getElementById("listctdonhang").value = listCTDonHang;
</script>
					<div class="col-md-6">
						<div class="shiping-methods">
							<div class="section-title">
								<h4 class="title">Phương thức ship hàng</h4>
							</div>
							<div class="input-checkbox">
								<input type="radio" name="shipping" id="shipping-1">
								<label for="shipping-1">Free Shiping</label>
								<div class="caption">
									<p>Bạn được free ship vào các ngày đặc biệt của E-shop!<p>						
								</div>
							</div>
							<div class="input-checkbox">
								<input type="radio" name="shipping" id="shipping-2" checked>
								<label for="shipping-2">Standard</label>
								<div class="caption">
									<p>Phí ship tùy thuộc vào thành phố/tỉnh bạn đang sống.<p>					
								</div>
							</div>
						</div>

						<div class="payments-methods">
							<div class="section-title">
								<h4 class="title">Phương thức thanh toán</h4>
							</div>
							<div class="input-checkbox">
								<input type="radio" name="payments" id="payments-1" checked>
								<label for="payments-1">Thanh Toán tại nhà</label>
								<div class="caption">
									<p>Thanh toán và nhận hàng tại địa chỉ bạn đã cung cấp cho chúng tôi.<p>
										
								</div>
							</div>
							<!--<div class="input-checkbox">
								<input type="radio" name="payments" id="payments-2">
								<label for="payments-2">Cheque Payment</label>
								<div class="caption">
									<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
										<p>
								</div>
							</div>
							<div class="input-checkbox">
								<input type="radio" name="payments" id="payments-3">
								<label for="payments-3">Paypal System</label>
								<div class="caption">
									<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
										<p>
								</div>
							</div> -->
						</div>
					</div>

					<div class="col-md-12">
						<div class="order-summary clearfix">
							<div class="section-title">
								<h3 class="title">Đơn đặt hàng</h3>
							</div>
							<table class="shopping-cart-table table">
								<thead>
									<tr>
										<th>Sản phẩm</th>
										<th></th>
										<th class="text-center">Đơn giá</th>
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
										<td id="phiship" colspan="2">Free Shipping</td>
									</tr>
									<tr>
										<th class="empty" colspan="3"></th>
										<th>TỔNG TIỀN</th>
										<th colspan="2" class="total"><span>0</span></th> 
									</tr>
								</tfoot>
                                                              
                                                                <c:if test="${listSachHetHang.size() gt 0}">
                                                                     
                                                                    <c:forEach items="${listSachHetHang}" var="obj">
                                                                        <tr>                               
                                                                            <th> <font color="red">Sản phẩm "${obj.getTenSach()}" chỉ còn ${obj.getSoLuongTon()} cuốn</font></th>

                                                                        </tr>
                                                                    </c:forEach>
                                                                </c:if>   
							</table>
                                
							<div class="pull-right">
                                                            <button type="submit" class="primary-btn-dathang" name="submit" value="them">ĐẶT MUA</button>
                                                           
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


        <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
        <script>
                if( !window.jQuery ) document.write('<script src="js/jquery-1.4.2.min.js"><\/script>');
        </script>
        <script src="js/maintest.js"></script> <!-- Resource jQuery -->
        
 <jsp:include page="theme/footer.jsp" />
</body>

</html>
