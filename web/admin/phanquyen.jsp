<%-- 
    Document   : phanquyen
    Created on : Mar 19, 2019, 4:15:43 PM
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
                
             <div class="col-md-8 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Danh sách loại phân quyền</h2>
                    <ul class="nav navbar-right panel_toolbox">
                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
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
                          <th style="width: 15%">Tên phân quyền</th>
                          <th>QL Thành viên</th>
                          <th>QL sách</th>
                          <th>QL Thể loại</th>
                          <th>QL Phí ship</th>
                          <th>QL Phân quyền</th>
                          <th>QL Phiếu nhập</th>
                          <th>QL Phiếu chi</th>
                          <th>QL Nhà cung cấp</th> 
                          <th>QL Đơn hàng</th> 
                          <th>QL Báo cáo</th> 
                          <th style="width: 20%">Thao tác</th>
                        </tr>
                      </thead>
                      
                      <tbody>
                            
                            
                            <c:forEach items="${listAllPhanQuyen}" var="obj">
                                <tr>
                                    <td style="width: 5%">${obj.getMaPhanQuyen()}</td>
                                    <td style="width: 20%">${obj.getTenQuyen()}</td>
                                    <td>${obj.getQlThanhVien() == 1 ? "<i class=\"fa fa-check\" style=\"color:green; align-content: center;\"></i>":""}</td>
                                    <td>${obj.getQlSach() == 1 ? "<i class=\"fa fa-check\" style=\"color:green; align-content: center;\"></i>":""}</td>
                                    <td>${obj.getQlTheLoai() == 1 ? "<i class=\"fa fa-check\" style=\"color:green; align-content: center;\"></i>":""}</td>
                                    <td>${obj.getQlPhiShip() == 1 ? "<i class=\"fa fa-check\" style=\"color:green; align-content: center;\"></i>":""}</td>
                                    <td>${obj.getQlPhanQuyen() == 1 ? "<i class=\"fa fa-check\" style=\"color:green; align-content: center;\"></i>":""}</td>
                                    <td>${obj.getQlPhieuNhap() == 1 ? "<i class=\"fa fa-check\" style=\"color:green; align-content: center;\"></i>":""}</td>
                                    <td>${obj.getQlPhieuChi() == 1 ? "<i class=\"fa fa-check\" style=\"color:green; align-content: center;\"></i>":""}</td>
                                    <td>${obj.getQlNhaCungCap() == 1 ? "<i class=\"fa fa-check\" style=\"color:green; align-content: center;\"></i>":""}</td>
                                    <td>${obj.getQlDonHang() == 1 ? "<i class=\"fa fa-check\" style=\"color:green; align-content: center;\"></i>":""}</td>
                                    <td>${obj.getQlBaoCao() == 1 ? "<i class=\"fa fa-check\" style=\"color:green; align-content: center;\"></i>":""}</td>
                                    <td> 
                                        <a href="${contextPath}/admin/phanquyen/edit?id=${obj.getMaPhanQuyen()}" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Sửa </a>
                                        <a href="${contextPath}/admin/phanquyen/delete?id=${obj.getMaPhanQuyen()}" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Xóa </a>
                                    </td>
                                   
                                    
                                </tr>
                            </c:forEach>
                           
                       
                          
                     
                        
                      </tbody>
                      
                    </table>
                    
                                        ${listAllPhanQuyen.size()==0?"Chưa có phân quyền nào được tạo!":""}
                    <!-- end project list -->

                  </div>
                </div>
              </div>

                
                
              <div class="col-md-4 col-xs-12">          
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Thêm phân quyền mới</h2>
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
                  
                    <form class="form-horizontal form-label-left " method="POST" action="${contextPath}/admin/phanquyen">
 
                        
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Tên phân quyền <span class="required">*</span>
                        </label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                          <input class="form-control col-md-7 col-xs-12" required="required" name="tenphanquyen" type="text" placeholder="Hãy nhập tên phân quyền">
                        </div>
                      </div>
                        
                        
                         
                         <div class="form-group">
                        <label class="col-md-3 col-sm-3 col-xs-12 control-label">Các quyền được phép <span class="required">*</span></label>

                        <div class="col-md-9 col-sm-9 col-xs-12">
                            
                          <div class="checkbox">
                            <label>
                              <input name="qlthanhvien" value="1" type="checkbox" class="flat"> Quản lí thành viên
                            </label>
                          </div>
                            
                          <div class="checkbox">
                            <label>
                              <input name="qlsach" value="1" type="checkbox" class="flat"> Quản lí sách
                            </label>
                          </div>
                            
                          <div class="checkbox">
                            <label>
                              <input name="qltheloai" value="1" type="checkbox" class="flat"> Quản lí thể loại	
                            </label>
                          </div>
                            
                          <div class="checkbox">
                            <label>
                              <input name="qlphiship" value="1" type="checkbox" class="flat"> Quản lí phí ship
                            </label>
                          </div>             
                            
                          <div class="checkbox">
                            <label>
                              <input name="qlphanquyen" value="1" type="checkbox" class="flat"> Quản lí phân quyền
                            </label>
                          </div>
                            
                          <div class="checkbox">
                            <label>
                              <input name="qlphieunhap" value="1" type="checkbox" class="flat"> Quản lí phiếu nhập
                            </label>
                          </div>
                            
                          <div class="checkbox">
                            <label>
                              <input name="qlphieuchi" value="1" type="checkbox" class="flat"> Quản lí phiếu chi
                            </label>
                          </div>                            
                            
                          <div class="checkbox">
                            <label>
                              <input name="qlnhacungcap" value="1" type="checkbox" class="flat"> Quản lí nhà cung cấp
                            </label>
                          </div>                            
                          <div class="checkbox">
                            <label>
                              <input name="qldonhang" value="1" type="checkbox" class="flat"> Quản lí đơn hàng
                            </label>
                          </div>         
                          <div class="checkbox">
                            <label>
                              <input name="qlbaocao" value="1" type="checkbox" class="flat"> Quản lí báo cáo
                            </label>
                          </div>   
                            
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