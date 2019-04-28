<%-- 
    Document   : sach
    Created on : Mar 29, 2019, 2:45:44 PM
    Author     : Trung
--%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 


<%@include file="header.jsp" %>

<head>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">


<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>  
<script src="<%= request.getContextPath()%>/admin/ckeditor/ckeditor.js"></script>     
<script type="text/javascript">
	$(document).ready(function() {
            $('#tentacgia').autocomplete({
		source: '${pageContext.request.contextPath}/admin/sach/add/ajaxtimtacgiasach'   
                    
            });
	});
        $(document).ready(function() {
            $('#nhaxuatban').autocomplete({
		source: '${pageContext.request.contextPath}/admin/sach/add/ajaxtimnhaxuatbansach'   
                    
            });
	});
</script>


</head>




            <div class="page-title">
              <div class="title_left">
                <h3>Sách</h3>
              </div>
 
            </div>
    
 
            <div class="row">   
                
                <div class="row">
              <div class="col-md-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Thêm sách</h2>
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
                   

                      
                     <form  class="form-horizontal form-label-left input_mask" method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/sach/add">

                      <div class="form-group">
                        <label class= " control-label col-md-3 col-sm-3 col-xs-12" >Tên sách<span class="required">*</span>
                        </label>
                        <div class=" control-label  col-md-3 col-sm-9 col-xs-12">
                          <input type="text" name="tensach" required="required" class="form-control col-md-7 col-xs-12">
                        </div>
                      </div>
                         <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Thể loại<span class="required">*</span>
                        </label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                             ${listAllTheLoai.size()==0?"Chưa có thể loại nào!":""}
                           
                            <select id="heard" class="form-control" required="" name="theloai">
                            <option value="" selected disabled hidden>Choose here</option>
                            <c:forEach items="${listAllTheLoai}" var="obj">
                                          <option value=${obj.getMaTheLoai()}>${obj.getTenTheLoai()}</option>
                                            
                            </c:forEach>
                          </select>
                      
                            </div>
                      </div>
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" >Nhà xuất bản<span class="required">*</span>
                        </label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                            <input id="nhaxuatban" type="text" name="nhaxuatban" required="required" class="form-control col-md-7 col-xs-12">
                        </div>
                      </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" >Năm xuất bản <span class="required">*</span>
                        </label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                            
                            <select id="heard" class="form-control" required=""  name="namxuatban">
                               <% 
                                   int x= Integer.parseInt(new java.text.SimpleDateFormat("yyyy").format(new java.util.Date()));
                                    for (int i = x;i >= 1990 ;i--)
                                        out.print("<option value=\""+i+"\">"+i+"</option>");
                                %>
                               
                            </select>
                        </div>
                      </div>
                        <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Giá bán</label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                            <input type="text" name="giaban" required="required"  class="form-control col-md-7 col-xs-12" onkeypress="return event.charCode >= 48 && event.charCode <= 57" >
                        </div>
                      </div>
                             <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Ảnh đại diện</label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                            
                        <input type="file" name="anhdaidien" accept="image/*" value="select images..."/>
                        </div>
                      </div>
                        
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Số lượng tồn<span class="required">*</span>
                        </label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                         <input type="text" name="sotienno" disabled="disabled" class="form-control col-md-7 col-xs-12" required="required" value="0" >
                         </div>
                      </div>
                         <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Tên tác giả<span class="required">*</span>
                        </label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                          <input id="tentacgia" type="text" name="tentacgia" class="form-control col-md-7 col-xs-12" required="required" >
                        </div>
                      </div>
                         <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Phần trăm giảm giá<span class="required">*</span>
                        </label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                          <input type="text" maxlength="2" name="phantramgiamgia" class="form-control col-md-7 col-xs-12" required="required" onkeypress="return event.charCode >= 48 && event.charCode <= 57" >
                        </div>
                      </div>
                         <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Khoảng thời gian giảm giá<span class="required">*</span>
                        </label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                        
                          <fieldset>
                            <div class="control-group">
                              <div class="controls">
                                <div class="input-prepend input-group">
                                  <span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span>
                                  <input type="text" style="width: 200px" name="khoangthoigiangiamgia" id="reservation" class="form-control" value="01/01/2016 - 01/25/2016">
                                </div>
                              </div>
                            </div>
                          </fieldset>
                        </div>
                      </div>
                        
                         <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Trạng thái<span class="required">*</span>
                        </label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                            <select id="heard" class="form-control" required="" name="trangthai">
                            <option value="" selected disabled hidden>Choose here</option>
                            <option value="1" >Đang bán</option>
                            <option value="2" >Xóa</option>
                            <option value="3" >Không kinh doanh</option>
                            
                          </select>
                        </div>
                      </div>
                        
                      
                             <div   class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Mô tả<span class="required">*</span>
                        </label>
                        <div class="col-md-12 col-sm-9 col-xs-12">
                          <textarea id="editor" type="text" name="mota" required="required" class="form-control" rows="3" placeholder="Mô tả"></textarea>
                        </div>
                      </div>
                           
                             <script>
                                 CKEDITOR.replace('editor')
                                 </script>
              
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