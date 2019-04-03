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
                    <h2>Sửa thông tin thành viên</h2>
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
                    <form class="form-horizontal form-label-left " method="POST" action="${pageContext.request.contextPath}/admin/thanhvien/edit">
                        <input type="hidden" name="mathanhvien" value="${thanhVienModel.getMaThanhVien()}">
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" >Tên đăng nhập <span class="required">*</span>
                        </label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                          <input type="text" name="tendangnhap" required="required" value="${thanhVienModel.getTenDangNhap()}" class="form-control col-md-7 col-xs-12">
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" >Mật khẩu<span class="required">*</span>
                        </label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                            <input type="password" name="matkhau" required="required" value="${thanhVienModel.getMatKhau()}" class="form-control col-md-7 col-xs-12">
                        </div>
                      </div>
                        <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" >Lập lại mật khẩu<span class="required">*</span>
                        </label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                            <input type="password" name="laplaimatkhau"  placeholder="Bỏ trống nếu không thay đổi mật khẩu" value="" class="form-control col-md-7 col-xs-12">
                        </div>
                      </div>
                        <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Họ tên</label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                            <input type="text" name="hoten" required="required" value="${thanhVienModel.getHoTen()}"  class="form-control col-md-7 col-xs-12">
                        </div>
                      </div>
                        <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Địa chỉ</label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                            <input type="text" name="diachi" required="required" value="${thanhVienModel.getDiaChi()}"  class="form-control col-md-7 col-xs-12">
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Số điện thoại</label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                            <input type="text" name="sodienthoai" value="${thanhVienModel.getSoDienThoai()}" class="form-control col-md-7 col-xs-12"required="required" onkeypress="return event.charCode >= 48 && event.charCode <= 57" >
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Email<span class="required">*</span>
                        </label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                          <input type="email" name="email" value="${thanhVienModel.getEmail()}" class="form-control col-md-7 col-xs-12" required="required" >
                        </div>
                      </div>
                        <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Phần quyền<span class="required">*</span>
                        </label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                             ${listAllPhanQuyen.size()==0?"Chưa phân quyền nào!":""}
                            
                            <select  id="heard" class="form-control" required="" name="phanquyen">
                                <option value="" selected disabled hidden>Choose here</option>
                            <c:forEach items="${listAllPhanQuyen}" var="obj">
                                          <option  ${obj.getMaPhanQuyen() == thanhVienModel.getMaPhanQuyen()? "selected" : "" }  value=${obj.getMaPhanQuyen()} >
                                              ${obj.getTenQuyen()}</option>
                                            
                            </c:forEach>
                          </select>
                             
                             
                      
                            </div>
                      </div>
                        <div class="ln_solid"></div>
                      <div class="form-group">
                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                          <a  href="${contextPath}/admin/thanhvien" class="btn btn-primary">Cancel</a>
                          <button type="submit" class="btn btn-success" name="submit" value="capnhat">Submit</button>
                        </div>
                      </div>

                    </form>
                  </div>
                </div>
              </div>
            </div>  
                    
                 
                             
         
            </div>
                    
                    
                    
                    

                
                
              
                
                
   
                
                
                
                
                 


<%@include file="footer.jsp" %>