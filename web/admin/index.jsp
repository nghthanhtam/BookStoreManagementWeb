<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>

<title>AutoComplete</title>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$('#search').autocomplete({
			source: '${pageContext.request.contextPath}/ajax'
		});

	});
</script>



 <form class="form-horizontal form-label-left " method="POST" action="${pageContext.request.contextPath}/admin/phieuchi">


                    <div class="form-group">

                        <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
                        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
                        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
                        <script type="text/javascript">
                                $(document).ready(function() {
                                        $('#search').autocomplete({
                                                source: '${pageContext.request.contextPath}/ajax'
                                        });
                                });
                        </script>

                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Mã nhà cung cấp <span class="required"></span>
                        </label>  
                    
                        <div class="col-md-9 col-sm-9 col-xs-12">
                            <input class="form-control col-md-7 col-xs-12" required="required" name="manhacungcap" type="text" id="search" placeholder="Hãy nhập mã nhà cung cấp">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Mã thành viên<span class="required"></span>
                        </label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                            <input class="form-control col-md-7 col-xs-12" required="required" name="mathanhvien" type="text" placeholder="Hãy nhập mã thành viên">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Tổng tiền<span class="required"></span>
                        </label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                            <input class="form-control col-md-7 col-xs-12" required="required" name="tongtien" type="text" placeholder="Hãy nhập tổng tiền">
                        </div>
                    </div>   

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Ghi chú<span class="required"></span>
                        </label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                            <input class="form-control col-md-7 col-xs-12" name="ghichu" type="text" placeholder="Hãy nhập ghi chú">
                        </div>
                    </div>
                    


                    <div class="form-group">
                        <div class="col-md-9 col-xs-12 col-md-offset-4">
                            <button class="btn btn-primary" type="reset">Đặt lại</button>
                            <button type="submit" class="btn btn-success" name="submit" value="them">Thêm</button>
                        </div>
                    </div>

                    
                </form>


