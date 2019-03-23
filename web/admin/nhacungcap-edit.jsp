<%-- 
    Document   : nhacungcap-edit
    Created on : Mar 21, 2019, 3:11:31 PM
    Author     : Trung
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 

<%@include file="header.jsp" %>

            <div class="page-title">
              <div class="title_left">
                <h3>Nhà cung cấp</h3>
              </div>
 
            </div>
            <div class="clearfix"></div>
 
          <div class="row">
              <div class="col-md-12 col-xs-12 col-sm-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Sửa thông tin nhà cung cấp</h2>
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
                    <form id="demo-form2" data-parsley-validate="" class="form-horizontal form-label-left" novalidate="" method="POST" action="${pageContext.request.contextPath}/admin/nhacungcap/edit">
                      <input type="hidden" name="manhacungcap" value="${nhaCungCapModel.getMaNhaCungCap()}">
                      
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" >Tên nhà cung cấp</label>
                        <div class="col-md-5 col-sm-9 col-xs-12">
                          <input type="text" name="tennhacungcap" value="${nhaCungCapModel.getTenNhaCungCap()}" pattern="^[a-zA-Z][\sa-zA-Z]*" required="required" class="form-control col-md-7 col-xs-12">
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" >Địa chỉ</label>
                        <div class="col-md-5 col-sm-9 col-xs-12">
                          <input type="text" name="diachi" required="required" value="${nhaCungCapModel.getDiaChi()}" class="form-control col-md-7 col-xs-12">
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Số điện thoại</label>
                        <div class="col-md-5 col-sm-9 col-xs-12">
                           <input type="text"value="${nhaCungCapModel.getSoDienThoai()}" name="sodienthoai" class="form-control col-md-7 col-xs-12" required="required" onkeypress="return event.charCode >= 48 && event.charCode <= 57">
                        </div>
                        </div>
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Số tiền nợ</label>
                        <div class="col-md-5 col-sm-9 col-xs-12">
                               <input type="text" name="sotienno" disabled="disabled" class="form-control col-md-7 col-xs-12" required="required" value="${nhaCungCapModel.getSoTienNo()}" onkeypress="return event.charCode >= 48 && event.charCode <= 57">
                        </div>
                       </div>
                        <div class="ln_solid"></div>
                       <div class="form-group">
                        <div class="col-md-10 col-sm-6 col-xs-12 col-md-offset-3">
                            <a  href="${contextPath}/admin/nhacungcap" class="btn btn-primary">Cancel</a>
                          <button type="submit" class="btn btn-success" name="submit" value="capnhat">Save</button>
                        </div>
                       </div>
                    </form>
                            
                  </div>
                </div
                
                
                
                
                 


<%@include file="footer.jsp" %>