
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 

<%@include file="header.jsp" %>

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
                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                      </li>
                      <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                        <ul class="dropdown-menu" role="menu">
                          <li><a href="#">Settings 1</a>
                          </li>
                          <li><a href="#">Settings 2</a>
                          </li>
                        </ul>
                      </li>
                      <li><a class="close-link"><i class="fa fa-close"></i></a>
                      </li>
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
                                    <td >${obj.getMaTheLoai()}</td>
                                    <td>${obj.getSoLuongTon()}</td>
                                    <td>${obj.getTrangThai()}</td>
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
