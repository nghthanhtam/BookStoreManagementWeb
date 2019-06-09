<%-- 
    Document   : dangky
    Created on : Apr 7, 2019, 9:02:29 AM
    Author     : MITICC06
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="theme/header.jsp" />



<!-- BREADCRUMB -->
	<div id="breadcrumb">
		<div class="container">
			<ul class="breadcrumb">
				<li><a href="/">Home</a></li>
				<li class="active">Đăng ký thành viên</li>
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
                            <form class="clearfix" method="POST" action="${contextPath}/dangky">
                                
                                        <div class="col-md-12">
                                        </div>
                                
					<div class="col-md-6">
						<div class="billing-details">
 							<div class="section-title">
								<h3 class="title">Thông tin đăng ký thành viên</h3>
							</div>
							<div class="form-group">
								<input class="input" type="text" name="tendangnhap" placeholder="Tên đăng nhập" value="${tendangnhap == null ? "" : tendangnhap}">
							</div>
							<div class="form-group">
								<input class="input" autocomplete="new-password" type="password" name="matkhau" placeholder="Mật khẩu">
							</div>
                                                        <div class="form-group">
								<input class="input" autocomplete="new-password" type="password" name="laplaimatkhau" placeholder="Lặp lại mật khẩu">
							</div>
							<div class="form-group">
								<input class="input" type="text" name="hoten" placeholder="Họ tên" value="${hoten == null ? "" : hoten}">
							</div>
							<div class="form-group">
								<input class="input" type="text" name="diachi" placeholder="Địa chỉ" value="${diachi == null ? "" : diachi}">
							</div>
							<div class="form-group">
								<input class="input" type="tel" name="sodienthoai" placeholder="Số điện thoại" value="${sodienthoai == null ? "" : sodienthoai}">
							</div>
							<div class="form-group">
								<input class="input" type="email" name="email" placeholder="Email" value="${email == null ? "" : email}">
							</div> 
							
						</div>
					</div>

					<div class="col-md-6">
						<div>
							<div class="section-title">
								<h4 class="title">Điều khoản sử dụng</h4>
							</div>
                                                    
							<div> 
								<div class="caption">
                                                                    <p>Khi quý khách hàng truy cập vào trang website của chúng tôi có nghĩa là quý khách đồng ý với các điều khoản này. Trang web có quyền thay đổi, chỉnh sửa, thêm hoặc lược bỏ bất kỳ phần nào trong Điều khoản mua bán hàng hóa này, vào bất cứ lúc nào. Các thay đổi có hiệu lực ngay khi được đăng trên trang web mà không cần thông báo trước. Và khi quý khách tiếp tục sử dụng trang web, sau khi các thay đổi về Điều khoản này được đăng tải, có nghĩa là quý khách chấp nhận với những thay đổi đó.<p>
                                                                    <p>Tất cả nội dung trang web và ý kiến phê bình của quý khách đều là tài sản của chúng tôi. Nếu chúng tôi phát hiện bất kỳ thông tin giả mạo nào, chúng tôi sẽ khóa tài khoản của quý khách ngay lập tức hoặc áp dụng các biện pháp khác theo quy định của pháp luật Việt Nam.</p>
                                                                    <p>Chúng tôi có quyền từ chối hoặc hủy đơn hàng của quý khách vì bất kỳ lý do gì liên quan đến lỗi kỹ thuật, hệ thống một cách khách quan vào bất kỳ lúc nào.</p>
                                                                    <p>Mọi quyền sở hữu trí tuệ (đã đăng ký hoặc chưa đăng ký), nội dung thông tin và tất cả các thiết kế, văn bản, đồ họa, phần mềm, hình ảnh, video, âm nhạc, âm thanh, biên dịch phần mềm, mã nguồn và phần mềm cơ bản đều là tài sản của chúng tôi. Toàn bộ nội dung của trang web được bảo vệ bởi luật bản quyền của Việt Nam và các công ước quốc tế. Bản quyền đã được bảo lưu.</p>
								</div>
							</div>
                                                    
                                                        <div class="form-group">
								<div class="input-checkbox">
									<input name="xacnhandieukhoan" value="true" type="checkbox" id="xacnhandieukhoan" value="${xacnhandieukhoan == null ? "" : "checked"}">
									<label class="font-weak" for="xacnhandieukhoan">Tôi đã đọc và đồng ý với điều khoản sử dụng.</label>
									<div class="caption"> 
                                                                            <p class="xacnhandieukhoan-caption">
                                                                                <i class="fa fa-check"></i>
                                                                                Bạn đã xác nhận đồng ý với điều khoản.</p>
                                                                            
									</div>
								</div>
							</div> 
							 
						</div>

						 
					</div>

					    <div class="col-md-12">
						<div class="order-summary clearfix">
 							 
                                                    
                                                    
                                                    
							<div class="pull-right">
                                                            <button class="primary-btn" type="submit" name="submit" value="dangky">Đăng ký</button>
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
