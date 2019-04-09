<%-- 
    Document   : admin-logined
    Created on : Mar 11, 2019, 11:55:26 AM
    Author     : Ha Phuong
--%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 

<%@include file="header.jsp" %>

            <div class="page-title">
              <div class="title_left">
                <h3>Thành viên</h3>
              </div>
 
            </div>
    
 
            <div class="row">
                
                <div class="row">
              <div class="col-md-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Thêm thành viên</h2>
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
                  <div class="x_content">
                    <br>
                    <form class="form-horizontal form-label-left " method="POST" action="${pageContext.request.contextPath}/admin/thanhvien">

                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" >Tên đăng nhập <span class="required">*</span>
                        </label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                          <input type="text" name="tendangnhap" required="required" class="form-control col-md-7 col-xs-12">
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" >Mật khẩu <span class="required">*</span>
                        </label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                            <input type="password" name="matkhau" required="required" class="form-control col-md-7 col-xs-12">
                        </div>
                      </div>
                        <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" >Lập lại mật khẩu <span class="required">*</span>
                        </label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                            <input type="password" name="laplaimatkhau" required="required" class="form-control col-md-7 col-xs-12">
                        </div>
                      </div>
                        <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Họ tên</label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                            <input type="text" name="hoten" required="required"  class="form-control col-md-7 col-xs-12">
                        </div>
                      </div>
                        <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Địa chỉ</label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                            <input type="text" name="diachi" required="required"  class="form-control col-md-7 col-xs-12">
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Số điện thoại</label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                            <input type="text" name="sodienthoai" class="form-control col-md-7 col-xs-12"required="required" onkeypress="return event.charCode >= 48 && event.charCode <= 57" >
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Email<span class="required">*</span>
                        </label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                          <input type="email" name="email" class="form-control col-md-7 col-xs-12" required="required" >
                        </div>
                      </div>
                        <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Phần quyền<span class="required">*</span>
                        </label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                             ${listAllPhanQuyen.size()==0?"Chưa phân quyền nào!":""}
                           
                            <select id="heard" class="form-control" required="" name="phanquyen">
                            <option value="" selected disabled hidden>Choose here</option>
                            <c:forEach items="${listAllPhanQuyen}" var="obj">
                                          <option value=${obj.getMaPhanQuyen()}>${obj.getTenQuyen()}</option>
                                            
                            </c:forEach>
                          </select>
                      
                            </div>
                      </div>
                        <div class="ln_solid"></div>
                      <div class="form-group">
                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                          <button class="btn btn-primary" type="cancel">Cancel</button>
                          <button class="btn btn-primary" type="reset">Reset</button>
                          <button type="submit" class="btn btn-success" name="submit" value="them">Submit</button>
                        </div>
                      </div>

                    </form>
                  </div>
                </div>
              </div>
            </div>  
                    
            <div class="clearfix"></div>
 
            <div class="row">
                
             <div class="col-md-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Danh sách thành viên</h2>
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
                          <th>STT</th>
                          <th style="width: 10%">Tên đăng nhập</th>
                          <th style="width: 15%">Họ và tên</th>
                          <th style="width: 45%">Đỉa chỉ</th>
                          <th style="width: 10%">Số điện thoại</th>
                          <th>Email</th>
                          <th style="width: 20%">Tên phân quyền</th>
                          <th style="width: 20%">Thao tác</th>
                        </tr>
                      </thead>
                      <tbody>
                           
                           ${listAllThanhVienWithModel.size()==0?"Chưa có nhà cung cấp nào được tạo!":""}
                           
                            
                            <c:forEach items="${listAllThanhVienWithModel}" var="obj">
                                <tr>
                                    <td >${obj.getMaThanhVien()}</td>
                                    <td >${obj.getTenDangNhap()}</td>
                                    <td >${obj.getHoTen()}</td>
                                    <td >${obj.getDiaChi()}</td>
                                    <td>${obj.getSoDienThoai()}</td>
                                    <td>${obj.getEmail()}</td>
                                    <td>${obj.getTenPhanQuyen()}</td>
                                    <td> 
                                        <a href="${contextPath}/admin/thanhvien/edit?id=${obj.getMaThanhVien()}" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Edit </a>
                                        <a href="${contextPath}/admin/thanhvien/delete?id=${obj.getMaThanhVien()}" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Delete </a>
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
                             
                         
                             
         
            </div>
                    
                    
                    
                    

                
                
              
                
                
   
                
                
                
                
                 


<%@include file="footer.jsp" %>