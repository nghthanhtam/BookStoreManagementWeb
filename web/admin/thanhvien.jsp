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
<div class="clearfix"></div>
 
            <div class="row">
                
             <div class="col-md-8 col-xs-12">
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
                          <th style="width: 5%">STT</th>
                          <th style="width: 15%">Tên đăng nhập</th>
                          <th>Họ và tên</th>
                          <th>Đỉa chỉ</th>
                          <th>Số điện thoại</th>
                          <th>Email</th>
                          <th>Mã phân quyền</th>
                          <th style="width: 20%">Thao tác</th>
                        </tr>
                      </thead>
                      <tbody>
                           
                             ${listAllThanhVien.size()==0?"Chưa có nhà cung cấp nào được tạo!":""}
                           
                            
                            <c:forEach items="${listAllThanhVien}" var="obj">
                                <tr>
                                    <td style="width: 5%">${obj.getMaThanhVien()}</td>
                                    <td style="width: 20%">${obj.getTenDangNhap()}</td>
                                    <td>${obj.getHoTen()}</td>
                                    <td>${obj.getDiaChi()}</td>
                                    <td>${obj.getSoDienThoai()}</td>
                                    <td>${obj.getEmail()}</td>
                                    <td>${obj.getMaPhanQuyen()}</td>
                                    <td> 
                                        <a href="#" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Sửa </a>
                                        <a href="#" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Xóa </a>
                                    </td>
                                   
                                    
                                </tr>
                            </c:forEach>
                           
                       
                          
                     
                        
                      </tbody>
                    </table>
                    <!-- end project list -->

                  </div>
                </div>
              </div>

                
                
              <div class="row">
              <div class="col-md-4 col-xs-12">
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
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" >Tên nhà cung cấp <span class="required">*</span>
                        </label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                          <input type="text" name="tennhacungcap" required="required" class="form-control col-md-7 col-xs-12">
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" >Địa chỉ <span class="required">*</span>
                        </label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                          <input type="text" name="diachi" required="required" class="form-control col-md-7 col-xs-12">
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Số điện thoại</label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                            <input class="form-control col-md-7 col-xs-12"required="required" type="text" name="sodienthoai">
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Số tiền nợ<span class="required">*</span>
                        </label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                          <input name="sotienno" class="form-control col-md-7 col-xs-12" required="required" type="text">
                        </div>
                      </div>
                      <div class="ln_solid"></div>
                      <div class="form-group">
                        <div class="col-md-12 col-sm-6 col-xs-12 col-md-offset-3">
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
            </div>
                
                
   
                
                
                
                
                 


<%@include file="footer.jsp" %>