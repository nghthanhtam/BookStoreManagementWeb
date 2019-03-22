<%-- 
    Document   : phiship
    Created on : Mar 19, 2019, 4:15:43 PM
    Author     : Tam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@include file="header.jsp" %>

            <div class="page-title">
              <div class="title_left">
                <h3>Thể Loại</h3>
              </div>
 
            </div>
<div class="clearfix"></div>
 
            <div class="row">
                
             <div class="col-md-8 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Danh sách các thể loại sách</h2>
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
                          <th style="width: 5%">STT</th>
                          <th style="width: 15%"> Tên thể loại</th>
                        
                         
                          <th style="width: 50%">Thao tác</th>
                        </tr>
                      </thead>
                      
                      <tbody>
                          ${listAllTheLoai.size()==0?"Chưa có thể loại nào được tạo!":""}                         
                       
                            <c:forEach items="${listAllTheLoai}" var="obj">
                                <tr>
                                    <td style="width: 5%">${obj.getMaTheLoai()}</td>
                                    <td style="width: 15%">${obj.getTenTheLoai()}</td>
        
                                    
                                    <td> 
                                        <a href="${contextPath}/admin/theloai/edit?id=${obj.getMaTheLoai()}" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Sửa </a>
                                        <a href="${contextPath}/admin/theloai/delete?id=${obj.getMaTheLoai()}" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Xóa </a>
                                    </td>
                                   
                                    
                                </tr>
                            </c:forEach>  
                                            
                     
                        
                      </tbody>
                    </table>
                    <!-- end project list -->

                  </div>
                </div>
              </div>

                
                
              <div class="col-md-4 col-xs-12">          
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Thêm thể loại mới</h2>
                    <ul class="nav navbar-right panel_toolbox">
                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                      </li> 
                      <li><a class="close-link"><i class="fa fa-close"></i></a>
                      </li>
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <br>
                 
                    
                    
                    <form class="form-horizontal form-label-left " method="POST" action="${pageContext.request.contextPath}/admin/theloai">

                        
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Tên thể loại <span class="required"></span>
                        </label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                          <input class="form-control col-md-7 col-xs-12" required="required" name="tentheloai" type="text" placeholder="Hãy nhập tên thể loại">
                        </div>
                      </div>
                        
      
                      <div class="form-group">
                        <div class="col-md-9 col-xs-12 col-md-offset-4">
                            <button class="btn btn-primary" type="reset">Đặt lại</button>
                            <button type="submit" class="btn btn-success" name="submit" value="them">Thêm</button>                            
                        </div>
                      </div>

                    </form>
                     
                  </div>
                </div>  
                      
                    
                    
              </div>
                
            </div>
                
                


<%@include file="footer.jsp" %>
