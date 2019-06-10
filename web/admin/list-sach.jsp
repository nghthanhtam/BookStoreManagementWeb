
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@include file="header.jsp" %>

<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>     
<script type="text/javascript">
	$(document).ready(function() {

            $('#search').autocomplete({
		source: '/ajax/sach',
                select: function(event, ui){
                        this.value = ui.item.label;
                        window.location.href = '/admin/sach/edit?id='+ui.item.value;
                        return false;
                    }
            });

	});
</script>



<div class="page-title">
    <div class="title_left">
        <h3>Sách</h3>
    </div>

</div>


<div class="clearfix"></div>
<div class="row">

    <div class="col-md-12 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Danh sách các sách</h2>
                <a class="btn btn-primary"  href="${contextPath}/admin/sach/add"  type="cancel">Thêm sách</a>
                <ul class="nav navbar-right panel_toolbox">
                    <input id="search" class="form-control col-md-7 col-xs-12 ui-autocomplete-input" style="width:400px" required="required" name="tukhoa" type="text" placeholder="Search..." autocomplete="off">
                </ul>
                <div class="clearfix"></div>
            </div>
            <div class="x_content" style="display: block;">


                <!-- start project list -->
                <table class="table table-striped projects">
                    <thead>
                        <tr>
                            <th style="width: 10%">STT</th>
                            <th style="width: 20%">Tên sách</th>
                            <th style="width: 15%">Tác giả</th>
                            <th style="width: 10%">Thể loại</th>
                            <th style="width: 10%">Số lượng tồn</th>
                            <th style="width: 20%">Trạng thái</th>
                            <th >Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>

                        ${listAllSach.size()==0?"Chưa sách nào được tạo!":""}


                        <c:forEach items="${listAllSach}" var="obj">
                            <tr>
                                <td >${obj.getMaSach()}</td>
                                <td >${obj.getTenSach()}</td>
                                <td >${obj.getTenTacGia()}</td>
                                <td >${obj.getTenTheLoai()}</td>
                                <td>${obj.getSoLuongTon()}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${obj.getTrangThai()==1}">
                                            Đang bán
                                        </c:when>
                                        <c:when test="${obj.getTrangThai()==3}">
                                            Ngưng kinh doanh
                                        </c:when>
                                        <c:otherwise>
                                            Bị xóa 
                                        </c:otherwise>  
                                    </c:choose>


                                </td>
                                <td> 
                                    <a href="${contextPath}/admin/sach/edit?id=${obj.getMaSach()}" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Edit </a>
                                    <a href="${contextPath}/admin/sach/delete?id=${obj.getMaSach()}" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Delete </a>
                                </td>


                            </tr>
                        </c:forEach>





                    </tbody>
                </table>
                <!-- end project list -->

            </div>
        </div>
    </div>




</div>
<%@include file="footer.jsp" %>
