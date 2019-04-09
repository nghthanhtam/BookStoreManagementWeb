<%-- 
    Document   : nhacungcap-edit
    Created on : Mar 21, 2019, 3:11:31 PM
    Author     : Trung
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@include file="header.jsp" %>

<div class="page-title">
    <div class="title_left">
        <h3>Sách</h3>
    </div>

</div>
<div class="clearfix"></div>

<div class="row">
    <div class="col-md-12 col-xs-12 col-sm-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Sửa thông tin sách</h2>
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

                <form class="form-horizontal form-label-left input_mask" method="POST" action="${pageContext.request.contextPath}/admin/sach/edit">
                    <input type="hidden" name="masach" value="${sachModel.getMaSach()}">
                    <div class="form-group">
                        <label class= " control-label col-md-3 col-sm-3 col-xs-12" >Tên sách<span class="required">*</span>
                        </label>
                        <div class=" control-label  col-md-2 col-sm-9 col-xs-12">
                            <input type="text" name="tensach" value="${sachModel.getTenSach()}" required="required" class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Thể loại<span class="required">*</span>
                        </label>
                        <div class="col-md-2 col-sm-9 col-xs-12">
                            ${listAllTheLoai.size()==0?"Chưa có thể loại nào!":""}

                            <select id="heard" class="form-control" required="" name="theloai">
                                <option value="" selected disabled hidden>Choose here</option>
                                <c:forEach items="${listAllTheLoai}" var="obj">
                                    <option  ${obj.getMaTheLoai() == sachModel.getMaTheLoai()? "selected" : "" }
                                        value=${obj.getMaTheLoai()}>${obj.getTenTheLoai()}</option>
                                </c:forEach>
                            </select>

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" >Nhà xuất bản<span class="required">*</span>
                        </label>
                        <div class="col-md-2 col-sm-9 col-xs-12">
                            <input type="text" value="${sachModel.getNhaXuatBan()}" name="nhaxuatban" required="required" class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" >Năm xuất bản <span class="required">*</span>
                        </label>
                        <div class="col-md-2 col-sm-9 col-xs-12">

                            <select id="heard" class="form-control" required=""  name="namxuatban">
                                <option ${sachModel.getNamXuatBan() == 2019? "selected" : "" } value="2019">2019</option>
                                <option ${sachModel.getNamXuatBan() == 2018? "selected" : "" }  value="2018">2018</option>
                                <option ${sachModel.getNamXuatBan() == 2017? "selected" : "" } value="2017">2017</option>
                                <option ${sachModel.getNamXuatBan() == 2016? "selected" : "" } value="2016">2016</option>
                                <option ${sachModel.getNamXuatBan() == 2015? "selected" : "" } value="2015">2015</option>
                                <option ${sachModel.getNamXuatBan() == 2014? "selected" : "" } value="2014">2014</option>
                                <option ${sachModel.getNamXuatBan() == 2013? "selected" : "" } value="2013">2013</option>
                                <option ${sachModel.getNamXuatBan() == 2012? "selected" : "" } value="2012">2012</option>
                                <option ${sachModel.getNamXuatBan() == 2011? "selected" : "" } value="2011">2011</option>
                                <option ${sachModel.getNamXuatBan() == 2010? "selected" : "" } value="2010">2010</option>
                                <option ${sachModel.getNamXuatBan() == 2009? "selected" : "" } value="2009">2009</option>
                                <option ${sachModel.getNamXuatBan() == 2008? "selected" : "" } value="2008">2008</option>
                                <option ${sachModel.getNamXuatBan() == 2007? "selected" : "" } value="2007">2007</option>
                                <option ${sachModel.getNamXuatBan() == 2006? "selected" : "" } value="2006">2006</option>
                                <option ${sachModel.getNamXuatBan() == 2005? "selected" : "" } value="2005">2005</option>
                                <option ${sachModel.getNamXuatBan() == 2004? "selected" : "" } value="2004">2004</option>
                                <option ${sachModel.getNamXuatBan() == 2003? "selected" : "" } value="2003">2003</option>
                                <option ${sachModel.getNamXuatBan() == 2002? "selected" : "" } value="2002">2002</option>
                                <option ${sachModel.getNamXuatBan() == 2001? "selected" : "" } value="2001">2001</option>
                                <option ${sachModel.getNamXuatBan() == 2000? "selected" : "" } value="2000">2000</option>
                                <option ${sachModel.getNamXuatBan() == 1999? "selected" : "" } value="1999">1999</option>
                                <option ${sachModel.getNamXuatBan() == 1998? "selected" : "" } value="1998">1998</option>
                                <option ${sachModel.getNamXuatBan() == 1997? "selected" : "" } value="1997">1997</option>
                                <option ${sachModel.getNamXuatBan() == 1996? "selected" : "" } value="1996">1996</option>
                                <option ${sachModel.getNamXuatBan() == 1995? "selected" : "" } value="1995">1995</option>
                                <option ${sachModel.getNamXuatBan() == 1994? "selected" : "" } value="1994">1994</option>
                                <option ${sachModel.getNamXuatBan() == 1993? "selected" : "" } value="1993">1993</option>
                                <option ${sachModel.getNamXuatBan() == 1992? "selected" : "" } value="1992">1992</option>
                                <option ${sachModel.getNamXuatBan() == 1991? "selected" : "" } value="1991">1991</option>
                                <option ${sachModel.getNamXuatBan() == 1990? "selected" : "" } value="1990">1990</option>
                                <option ${sachModel.getNamXuatBan() == 1989? "selected" : "" } value="1989">1989</option>
                                <option ${sachModel.getNamXuatBan() == 1988? "selected" : "" } value="1988">1988</option>
                                <option ${sachModel.getNamXuatBan() == 1987? "selected" : "" } value="1987">1987</option>
                                <option ${sachModel.getNamXuatBan() == 1986? "selected" : "" } value="1986">1986</option>
                                <option ${sachModel.getNamXuatBan() == 1985? "selected" : "" } value="1985">1985</option>
                                <option ${sachModel.getNamXuatBan() == 1984? "selected" : "" } value="1984">1984</option>
                                <option ${sachModel.getNamXuatBan() == 1983? "selected" : "" } value="1983">1983</option>
                                <option ${sachModel.getNamXuatBan() == 1982? "selected" : "" } value="1982">1982</option>
                                <option ${sachModel.getNamXuatBan() == 1981? "selected" : "" } value="1981">1981</option>
                                <option ${sachModel.getNamXuatBan() == 1980? "selected" : "" } value="1980">1980</option>
                                <option ${sachModel.getNamXuatBan() == 1979? "selected" : "" } value="1979">1979</option>
                                <option ${sachModel.getNamXuatBan() == 1978? "selected" : "" } value="1978">1978</option>
                                <option ${sachModel.getNamXuatBan() == 1977? "selected" : "" } value="1977">1977</option>
                                <option ${sachModel.getNamXuatBan() == 1976? "selected" : "" } value="1976">1976</option>
                                <option ${sachModel.getNamXuatBan() == 1975? "selected" : "" } value="1975">1975</option>
                                <option ${sachModel.getNamXuatBan() == 1974? "selected" : "" } value="1974">1974</option>
                                <option ${sachModel.getNamXuatBan() == 1973? "selected" : "" } value="1973">1973</option>
                                <option ${sachModel.getNamXuatBan() == 1972? "selected" : "" } value="1972">1972</option>
                                <option ${sachModel.getNamXuatBan() == 1971? "selected" : "" } value="1971">1971</option>
                                <option ${sachModel.getNamXuatBan() == 1970? "selected" : "" } value="1970">1970</option>
                                <option ${sachModel.getNamXuatBan() == 1969? "selected" : "" } value="1969">1969</option>
                                <option ${sachModel.getNamXuatBan() == 1968? "selected" : "" } value="1968">1968</option>
                                <option ${sachModel.getNamXuatBan() == 1967? "selected" : "" } value="1967">1967</option>
                                <option ${sachModel.getNamXuatBan() == 1966? "selected" : "" } value="1966">1966</option>
                                <option ${sachModel.getNamXuatBan() == 1965? "selected" : "" } value="1965">1965</option>
                                <option ${sachModel.getNamXuatBan() == 1964? "selected" : "" } value="1964">1964</option>
                                <option ${sachModel.getNamXuatBan() == 1963? "selected" : "" } value="1963">1963</option>
                                <option ${sachModel.getNamXuatBan() == 1962? "selected" : "" } value="1962">1962</option>
                                <option ${sachModel.getNamXuatBan() == 1961? "selected" : "" } value="1961">1961</option>
                                <option ${sachModel.getNamXuatBan() == 1960? "selected" : "" } value="1960">1960</option>
                                <option ${sachModel.getNamXuatBan() == 1959? "selected" : "" } value="1959">1959</option>
                                <option ${sachModel.getNamXuatBan() == 1958? "selected" : "" } value="1958">1958</option>
                                <option ${sachModel.getNamXuatBan() == 1957? "selected" : "" } value="1957">1957</option>
                                <option ${sachModel.getNamXuatBan() == 1956? "selected" : "" } value="1956">1956</option>
                                <option ${sachModel.getNamXuatBan() == 1955? "selected" : "" } value="1955">1955</option>
                                <option ${sachModel.getNamXuatBan() == 1954? "selected" : "" } value="1954">1954</option>
                                <option ${sachModel.getNamXuatBan() == 1953? "selected" : "" } value="1953">1953</option>
                                <option ${sachModel.getNamXuatBan() == 1952? "selected" : "" } value="1952">1952</option>
                                <option ${sachModel.getNamXuatBan() == 1951? "selected" : "" } value="1951">1951</option>
                                <option ${sachModel.getNamXuatBan() == 1950? "selected" : "" } value="1950">1950</option>
                                <option ${sachModel.getNamXuatBan() == 1949? "selected" : "" } value="1949">1949</option>
                                <option ${sachModel.getNamXuatBan() == 1948? "selected" : "" } value="1948">1948</option>
                                <option ${sachModel.getNamXuatBan() == 1947? "selected" : "" } value="1947">1947</option>
                                <option ${sachModel.getNamXuatBan() == 1946? "selected" : "" } value="1946">1946</option>
                                <option ${sachModel.getNamXuatBan() == 1945? "selected" : "" } value="1945">1945</option>
                                <option ${sachModel.getNamXuatBan() == 1944? "selected" : "" } value="1944">1944</option>
                                <option ${sachModel.getNamXuatBan() == 1943? "selected" : "" } value="1943">1943</option>
                                <option ${sachModel.getNamXuatBan() == 1942? "selected" : "" } value="1942">1942</option>
                                <option ${sachModel.getNamXuatBan() == 1941? "selected" : "" } value="1941">1941</option>
                                <option ${sachModel.getNamXuatBan() == 1940? "selected" : "" } value="1940">1940</option>
                                <option ${sachModel.getNamXuatBan() == 1939? "selected" : "" } value="1939">1939</option>
                                <option ${sachModel.getNamXuatBan() == 1938? "selected" : "" } value="1938">1938</option>
                                <option ${sachModel.getNamXuatBan() == 1937? "selected" : "" } value="1937">1937</option>
                                <option ${sachModel.getNamXuatBan() == 1936? "selected" : "" } value="1936">1936</option>
                                <option ${sachModel.getNamXuatBan() == 1935? "selected" : "" } value="1935">1935</option>
                                <option ${sachModel.getNamXuatBan() == 1934? "selected" : "" } value="1934">1934</option>
                                <option ${sachModel.getNamXuatBan() == 1933? "selected" : "" } value="1933">1933</option>
                                <option ${sachModel.getNamXuatBan() == 1932? "selected" : "" } value="1932">1932</option>
                                <option ${sachModel.getNamXuatBan() == 1931? "selected" : "" } value="1931">1931</option>
                                <option ${sachModel.getNamXuatBan() == 1930? "selected" : "" } value="1930">1930</option>
                                <option ${sachModel.getNamXuatBan() == 1929? "selected" : "" } value="1929">1929</option>
                                <option ${sachModel.getNamXuatBan() == 1928? "selected" : "" } value="1928">1928</option>
                                <option ${sachModel.getNamXuatBan() == 1927? "selected" : "" } value="1927">1927</option>
                                <option ${sachModel.getNamXuatBan() == 1926? "selected" : "" } value="1926">1926</option>
                                <option ${sachModel.getNamXuatBan() == 1925? "selected" : "" } value="1925">1925</option>
                                <option ${sachModel.getNamXuatBan() == 1924? "selected" : "" } value="1924">1924</option>
                                <option ${sachModel.getNamXuatBan() == 1923? "selected" : "" } value="1923">1923</option>
                                <option ${sachModel.getNamXuatBan() == 1922? "selected" : "" } value="1922">1922</option>
                                <option ${sachModel.getNamXuatBan() == 1921? "selected" : "" } value="1921">1921</option>
                                <option ${sachModel.getNamXuatBan() == 1920? "selected" : "" } value="1920">1920</option>
                                <option ${sachModel.getNamXuatBan() == 1919? "selected" : "" } value="1919">1919</option>
                                <option ${sachModel.getNamXuatBan() == 1918? "selected" : "" } value="1918">1918</option>
                                <option ${sachModel.getNamXuatBan() == 1917? "selected" : "" } value="1917">1917</option>
                                <option ${sachModel.getNamXuatBan() == 1916? "selected" : "" } value="1916">1916</option>
                                <option ${sachModel.getNamXuatBan() == 1915? "selected" : "" } value="1915">1915</option>
                                <option ${sachModel.getNamXuatBan() == 1914? "selected" : "" } value="1914">1914</option>
                                <option ${sachModel.getNamXuatBan() == 1913? "selected" : "" } value="1913">1913</option>
                                <option ${sachModel.getNamXuatBan() == 1912? "selected" : "" } value="1912">1912</option>
                                <option ${sachModel.getNamXuatBan() == 1911? "selected" : "" } value="1911">1911</option>
                                <option ${sachModel.getNamXuatBan() == 1910? "selected" : "" } value="1910">1910</option>
                                <option ${sachModel.getNamXuatBan() == 1909? "selected" : "" } value="1909">1909</option>
                                <option ${sachModel.getNamXuatBan() == 1908? "selected" : "" } value="1908">1908</option>
                                <option ${sachModel.getNamXuatBan() == 1907? "selected" : "" } value="1907">1907</option>
                                <option ${sachModel.getNamXuatBan() == 1906? "selected" : "" } value="1906">1906</option>
                                <option ${sachModel.getNamXuatBan() == 1905? "selected" : "" } value="1905">1905</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Giá bán</label>
                        <div class="col-md-2 col-sm-9 col-xs-12">
                            <input type="text" name="giaban" value="${sachModel.getGiaBan()}" required="required"  class="form-control col-md-7 col-xs-12" onkeypress="return event.charCode >= 48 && event.charCode <= 57" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Ảnh địa diện</label>
                        <div class="col-md-2 col-sm-9 col-xs-12">

                            <input type="file" name="anhdaidien" accept="image/*" value="select images..."/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Số lượng tồn<span class="required">*</span>
                        </label>
                        <div class="col-md-2 col-sm-9 col-xs-12">
                            <input type="text" name="soluongton" value="${sachModel.getSoLuongTon()}" readonly="true" class="form-control col-md-7 col-xs-12" required="required"  >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Tên tác giả<span class="required">*</span>
                        </label>
                        <div class="col-md-2 col-sm-9 col-xs-12">
                            <input type="text" name="tentacgia" class="form-control col-md-7 col-xs-12" value="${sachModel.getTenTacGia()}" required="required" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Phần trăm giảm giá<span class="required">*</span>
                        </label>
                        <div class="col-md-2 col-sm-9 col-xs-12">
                            <input type="text" maxlength="2" name="phantramgiamgia" value="${sachModel.getPhanTramGiamGia()}" class="form-control col-md-7 col-xs-12" required="required" onkeypress="return event.charCode >= 48 && event.charCode <= 57" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Khoảng thời gian giảm giá<span class="required">*</span>
                        </label>
                        <div class="col-md-2 col-sm-9 col-xs-12">

                            <fieldset>
                                <div class="control-group">
                                    <div class="controls">
                                        <div class="input-prepend input-group">
                                            <span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span>
                                            <input type="text" style="width: 200px"  name="khoangthoigian" id="reservation" class="form-control" value="${sachModel.getNgayBatDauGiamGia()}- 01/25/2016">
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Trạng thái<span class="required">*</span>
                        </label>
                        <div class="col-md-2 col-sm-9 col-xs-12">
                            <select id="heard" class="form-control" required="" name="trangthai">
                                <option ${sachModel.getTrangThai() == 1? "selected" : "" } value="1" >Đang bán</option>
                                <option ${sachModel.getTrangThai() == 2? "selected" : "" } value="2" >Xóa</option>
                                <option ${sachModel.getTrangThai() == 3? "selected" : "" } value="3" >Không kinh doanh</option>

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







    <%@include file="footer.jsp" %>