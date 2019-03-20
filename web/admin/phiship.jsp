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
                <h3>Phí ship</h3>
              </div>
 
            </div>
<div class="clearfix"></div>
 
            <div class="row">
                
             <div class="col-md-8 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Danh sách loại phí ship</h2>
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
                          <th style="width: 15%"> Tên tỉnh</th>
                          <th>Phí ship</th>
                         
                          <th style="width: 20%">Thao tác</th>
                        </tr>
                      </thead>
                      
                      <tbody>
                          ${listAllPhiShip.size()==0?"Chưa có phí ship nào được tạo!":""}
                           
                       
                            <c:forEach items="${listAllPhiShip}" var="obj">
                                <tr>
                                    <td style="width: 5%">${obj.getMaPhiShip()}</td>
                                    <td style="width: 15%">${obj.getTenTinh()}</td>
                                    <td style="width: 15%">${obj.getPhiShip()}</td>
                                     
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

                
                
              <div class="col-md-4 col-xs-12">          
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Thêm phí ship mới</h2>
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
                 
                    
                    
                    <form class="form-horizontal form-label-left " method="POST" action="${pageContext.request.contextPath}/admin/phiship">

                        
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Tên tỉnh <span class="required"></span>
                        </label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                          <input class="form-control col-md-7 col-xs-12" required="required" name="tentinh" type="text" placeholder="Hãy nhập tên tỉnh">
                        </div>
                      </div>
                        
                          <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Phí ship <span class="required"></span>
                        </label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                          <input class="form-control col-md-7 col-xs-12" required="required" name="phiship" type="text" placeholder="Hãy nhập phí ship">
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