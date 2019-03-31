<%-- 
    Document   : sach
    Created on : Mar 29, 2019, 2:45:44 PM
    Author     : Trung
--%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 

<%@include file="header.jsp" %>

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
                   

                      
                     <form class="form-horizontal form-label-left input_mask" method="POST" action="${pageContext.request.contextPath}/admin/sach">

                      <div class="form-group">
                        <label class= " control-label col-md-3 col-sm-3 col-xs-12" >Tên sách<span class="required">*</span>
                        </label>
                        <div class=" control-label  col-md-3 col-sm-9 col-xs-12">
                          <input type="text" name="tensach" required="required" class="form-control col-md-7 col-xs-12">
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" >Nhà xuất bản<span class="required">*</span>
                        </label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                            <input type="text" name="nhaxuatban" required="required" class="form-control col-md-7 col-xs-12">
                        </div>
                      </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" >Năm xuất bản <span class="required">*</span>
                        </label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                            
                            <select id="heard" class="form-control" required=""  name="namxuatban">
                                <option value="2019">2019</option>
                                <option value="2018">2018</option>
                                <option value="2017">2017</option>
                                <option value="2016">2016</option>
                                <option value="2015">2015</option>
                                <option value="2014">2014</option>
                                <option value="2013">2013</option>
                                <option value="2012">2012</option>
                                <option value="2011">2011</option>
                                <option value="2010">2010</option>
                                <option value="2009">2009</option>
                                <option value="2008">2008</option>
                                <option value="2007">2007</option>
                                <option value="2006">2006</option>
                                <option value="2005">2005</option>
                                <option value="2004">2004</option>
                                <option value="2003">2003</option>
                                <option value="2002">2002</option>
                                <option value="2001">2001</option>
                                <option value="2000">2000</option>
                                <option value="1999">1999</option>
                                <option value="1998">1998</option>
                                <option value="1997">1997</option>
                                <option value="1996">1996</option>
                                <option value="1995">1995</option>
                                <option value="1994">1994</option>
                                <option value="1993">1993</option>
                                <option value="1992">1992</option>
                                <option value="1991">1991</option>
                                <option value="1990">1990</option>
                                <option value="1989">1989</option>
                                <option value="1988">1988</option>
                                <option value="1987">1987</option>
                                <option value="1986">1986</option>
                                <option value="1985">1985</option>
                                <option value="1984">1984</option>
                                <option value="1983">1983</option>
                                <option value="1982">1982</option>
                                <option value="1981">1981</option>
                                <option value="1980">1980</option>
                                <option value="1979">1979</option>
                                <option value="1978">1978</option>
                                <option value="1977">1977</option>
                                <option value="1976">1976</option>
                                <option value="1975">1975</option>
                                <option value="1974">1974</option>
                                <option value="1973">1973</option>
                                <option value="1972">1972</option>
                                <option value="1971">1971</option>
                                <option value="1970">1970</option>
                                <option value="1969">1969</option>
                                <option value="1968">1968</option>
                                <option value="1967">1967</option>
                                <option value="1966">1966</option>
                                <option value="1965">1965</option>
                                <option value="1964">1964</option>
                                <option value="1963">1963</option>
                                <option value="1962">1962</option>
                                <option value="1961">1961</option>
                                <option value="1960">1960</option>
                                <option value="1959">1959</option>
                                <option value="1958">1958</option>
                                <option value="1957">1957</option>
                                <option value="1956">1956</option>
                                <option value="1955">1955</option>
                                <option value="1954">1954</option>
                                <option value="1953">1953</option>
                                <option value="1952">1952</option>
                                <option value="1951">1951</option>
                                <option value="1950">1950</option>
                                <option value="1949">1949</option>
                                <option value="1948">1948</option>
                                <option value="1947">1947</option>
                                <option value="1946">1946</option>
                                <option value="1945">1945</option>
                                <option value="1944">1944</option>
                                <option value="1943">1943</option>
                                <option value="1942">1942</option>
                                <option value="1941">1941</option>
                                <option value="1940">1940</option>
                                <option value="1939">1939</option>
                                <option value="1938">1938</option>
                                <option value="1937">1937</option>
                                <option value="1936">1936</option>
                                <option value="1935">1935</option>
                                <option value="1934">1934</option>
                                <option value="1933">1933</option>
                                <option value="1932">1932</option>
                                <option value="1931">1931</option>
                                <option value="1930">1930</option>
                                <option value="1929">1929</option>
                                <option value="1928">1928</option>
                                <option value="1927">1927</option>
                                <option value="1926">1926</option>
                                <option value="1925">1925</option>
                                <option value="1924">1924</option>
                                <option value="1923">1923</option>
                                <option value="1922">1922</option>
                                <option value="1921">1921</option>
                                <option value="1920">1920</option>
                                <option value="1919">1919</option>
                                <option value="1918">1918</option>
                                <option value="1917">1917</option>
                                <option value="1916">1916</option>
                                <option value="1915">1915</option>
                                <option value="1914">1914</option>
                                <option value="1913">1913</option>
                                <option value="1912">1912</option>
                                <option value="1911">1911</option>
                                <option value="1910">1910</option>
                                <option value="1909">1909</option>
                                <option value="1908">1908</option>
                                <option value="1907">1907</option>
                                <option value="1906">1906</option>
                                <option value="1905">1905</option>
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
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Mô tả<span class="required">*</span>
                        </label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                          <textarea type="text" name="mota" required="required" class="form-control" rows="3" placeholder="Mô tả"></textarea>
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Ảnh địa diện</label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                            
                        </div>
                      </div>
                        
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Số lượng tồn<span class="required">*</span>
                        </label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                          <input type="text" name="soluongton" class="form-control col-md-7 col-xs-12" required="required" onkeypress="return event.charCode >= 48 && event.charCode <= 57"   >
                        </div>
                      </div>
                         <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Tên tác giả<span class="required">*</span>
                        </label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                          <input type="text" name="tentacgia" class="form-control col-md-7 col-xs-12" required="required" >
                        </div>
                      </div>
                         <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Phần trăm giảm giá<span class="required">*</span>
                        </label>
                        <div class="col-md-3 col-sm-9 col-xs-12">
                          <input type="text" maxlength="2" name="email" class="form-control col-md-7 col-xs-12" required="required" onkeypress="return event.charCode >= 48 && event.charCode <= 57" >
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
                                  <input type="text" style="width: 200px" name="khoangthoigian" id="reservation" class="form-control" value="01/01/2016 - 01/25/2016">
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