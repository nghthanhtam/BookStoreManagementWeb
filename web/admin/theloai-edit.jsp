
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@include file="header.jsp" %>

            <div class="page-title">
              <div class="title_left">
                <h3>Thể Loại</h3>
              </div>

            </div>
<div class="clearfix"></div>

            <div class="row">
               <div class="col-md-6 col-xs-12 col-md-offset-3">          
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Sửa thông tin thể loại</h2>
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



                    <form class="form-horizontal form-label-left " method="POST" action="${contextPath}/admin/theloai/edit">

                      <input type="hidden" name="matheloai" value="${theLoaiModel.getMaTheLoai()}">

                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Tên thể loại <span class="required"></span>
                        </label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                          <input class="form-control col-md-7 col-xs-12" required="required" name="tentheloai" type="text" placeholder="Hãy nhập tên thể loại" value="${theLoaiModel.getTenTheLoai()}">
                        </div>
                      </div>

                        
                      <div class="form-group">
                        <div class="col-md-9 col-xs-12 col-md-offset-4">
                            <a href="${contextPath}/admin/theloai" class="btn btn-primary">Hủy bỏ</a>
                            <button type="submit" class="btn btn-success" name="submit" value="capnhat">Cập nhật</button>
                        </div>
                      </div>

                    </form>

                  </div>
                </div>  

              </div>

            </div>



<%@include file="footer.jsp" %> 