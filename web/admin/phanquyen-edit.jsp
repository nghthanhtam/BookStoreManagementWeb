<%-- 
    Document   : phanquyen-edit
    Created on : Mar 20, 2019, 9:03:08 PM
    Author     : MITICC06
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 

<%@include file="header.jsp" %>

            <div class="page-title">
              <div class="title_left">
                <h3>Phân quyền</h3>
              </div>
 
            </div>
<div class="clearfix"></div>
 
            <div class="row">
               <div class="col-md-6 col-xs-12 col-md-offset-3">          
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Sửa thông tin phân quyền</h2>
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
                 
                    
                    
                    <form class="form-horizontal form-label-left " method="POST" action="${contextPath}/admin/phanquyen/edit">
                      
                      <input type="hidden" name="maphanquyen" value="${phanQuyenModel.getMaPhanQuyen()}">
                        
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Tên phân quyền <span class="required">*</span>
                        </label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                          <input class="form-control col-md-7 col-xs-12" required="required" name="tenphanquyen" type="text" placeholder="Hãy nhập tên phân quyền" value="${phanQuyenModel.getTenQuyen()}">
                        </div>
                      </div>
                        
                        
                         
                         <div class="form-group">
                        <label class="col-md-3 col-sm-3 col-xs-12 control-label">Các quyền được phép <span class="required">*</span></label>

                        <div class="col-md-9 col-sm-9 col-xs-12">
                            
                          <div class="checkbox">
                            <label>
                              <input name="qlthanhvien" value="1" type="checkbox" class="flat" ${phanQuyenModel.getQlThanhVien() == 1 ? "checked=\"checked\"":""}> Quản lí thành viên
                            </label>
                          </div>
                            
                          <div class="checkbox">
                            <label>
                              <input name="qlsach" value="1" type="checkbox" class="flat" ${phanQuyenModel.getQlSach() == 1 ? "checked=\"checked\"":""}> Quản lí sách
                            </label>
                          </div>
                            
                          <div class="checkbox">
                            <label>
                              <input name="qltheloai" value="1" type="checkbox" class="flat" ${phanQuyenModel.getQlTheLoai() == 1 ? "checked=\"checked\"":""}> Quản lí thể loại	
                            </label>
                          </div>
                            
                          <div class="checkbox">
                            <label>
                              <input name="qlphiship" value="1" type="checkbox" class="flat" ${phanQuyenModel.getQlPhiShip() == 1 ? "checked=\"checked\"":""}> Quản lí phí ship
                            </label>
                          </div>             
                            
                          <div class="checkbox">
                            <label>
                              <input name="qlphanquyen" value="1" type="checkbox" class="flat" ${phanQuyenModel.getQlPhanQuyen() == 1 ? "checked=\"checked\"":""}> Quản lí phân quyền
                            </label>
                          </div>
                            
                          <div class="checkbox">
                            <label>
                              <input name="qlphieunhap" value="1" type="checkbox" class="flat" ${phanQuyenModel.getQlPhieuNhap() == 1 ? "checked=\"checked\"":""}> Quản lí phiếu nhập
                            </label>
                          </div>
                            
                          <div class="checkbox">
                            <label>
                              <input name="qlphieuchi" value="1" type="checkbox" class="flat" ${phanQuyenModel.getQlPhieuChi() == 1 ? "checked=\"checked\"":""}> Quản lí phiếu chi
                            </label>
                          </div>                            
                            
                          <div class="checkbox">
                            <label>
                              <input name="qlnhacungcap" value="1" type="checkbox" class="flat" ${phanQuyenModel.getQlNhaCungCap() == 1 ? "checked=\"checked\"":""}> Quản lí nhà cung cấp
                            </label>
                          </div>                            
                          <div class="checkbox">
                            <label>
                              <input name="qldonhang" value="1" type="checkbox" class="flat" ${phanQuyenModel.getQlDonHang() == 1 ? "checked=\"checked\"":""}> Quản lí đơn hàng
                            </label>
                          </div>         
                            
                            
                        </div>
                      </div>
                         

                      <div class="form-group">
                        <div class="col-md-9 col-xs-12 col-md-offset-4">
                            <a href="${contextPath}/admin/phanquyen" class="btn btn-primary">Hủy bỏ</a>
                            <button type="submit" class="btn btn-success" name="submit" value="capnhat">Cập nhật</button>
                             
                        </div>
                      </div>

                    </form>
                     
                  </div>
                </div>  
                      
                    
                    
              </div>
                
            </div>
                
                
   
                
                
                
                
                 


<%@include file="footer.jsp" %>