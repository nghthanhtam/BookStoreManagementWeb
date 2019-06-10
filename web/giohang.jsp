<%-- 
    Document   : giohang
    Created on : May 18, 2019, 8:43:09 AM
    Author     : TamTorres
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.google.gson.Gson"%> 


<jsp:include page="theme/header.jsp" />

	<!-- BREADCRUMB -->
	<div id="breadcrumb">
		<div class="container">
			<ul class="breadcrumb">
				<li><a href="#">Home</a></li>
				<li class="active">Giỏ hàng</li>
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
										<th>Thành tiền</th>
                                                                                <th id="total2" colspan="2" class="total"><span>0</span></th>
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
	 