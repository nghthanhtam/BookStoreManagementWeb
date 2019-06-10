<%-- 
    Document   : header
    Created on : Mar 12, 2019, 11:07:47 AM
    Author     : Ha Phuong
--%> 
<%@page import="java.sql.Connection"%>
<%@page import="Model.PhanQuyenModel"%>
<%@page import="Model.ThanhVienModel"%>
<%@page import="Utility.MyUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@page import="Model.MessagesModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
    Connection conn = MyUtils.getStoredConnection(request);

    ThanhVienModel thanhvien = MyUtils.getLoginedThanhVien(session); 
    PhanQuyenModel phanQuyen = PhanQuyenModel.FindByMaPhanQuyen(conn, thanhvien.getMaPhanQuyen());
    String urlAvatar = "https://www.gravatar.com/avatar/"+MyUtils.MD5(thanhvien.getEmail());             
%>
  
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="/img/icon.png" type="image/ico" />
  
    <title>${txtTitle == null ? "" : txtTitle.concat(" - ")}Admin Panel - Book Store</title>

    <!-- Bootstrap -->
    <link href="${contextPath}/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="${contextPath}/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="${contextPath}/vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="${contextPath}/vendors/iCheck/skins/flat/green.css" rel="stylesheet">
	
    <!-- bootstrap-progressbar -->
    <link href="${contextPath}/vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
    <!-- JQVMap -->
    <link href="${contextPath}/vendors/jqvmap/dist/jqvmap.min.css" rel="stylesheet"/>
    <!-- bootstrap-daterangepicker -->
    <link href="${contextPath}/vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="${contextPath}/build/css/custom.min.css" rel="stylesheet">
    
    <!-- PNotify -->
    <link href="${contextPath}/vendors/pnotify/dist/pnotify.css" rel="stylesheet">

    <link href="${contextPath}/vendors/pnotify/dist/pnotify.buttons.css" rel="stylesheet"> 
    <!-- jQuery -->

    <script src="${contextPath}/vendors/jquery/dist/jquery.min.js"></script>
  </head>
 
   
   
  
  
      
  <body class="nav-md" <% 
    
      MessagesModel messagesModel = (MessagesModel)request.getAttribute(MessagesModel.ATT_STORE);
    if (messagesModel != null)
    {
        out.print("onload=\"new PNotify({"
                + "title: '" + MyUtils.addSlashes(messagesModel.getTitle()) + "',"
                + "text: '" + MyUtils.addSlashes(messagesModel.getText()) + "',"
                + "type: '"+messagesModel.getType()+"',"
                + "styling: 'bootstrap3'"
                + "});\""); 
    }
  
  %>>
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="/" class="site_title"><i class="fa fa-file-text"></i> <span>Book Store</span></a>
            </div>

            <div class="clearfix"></div>

            <!-- menu profile quick info -->
            <div class="profile clearfix">
              <div class="profile_pic">
                <img src="<% out.print(urlAvatar+"?size=200"); %>" alt="..." class="img-circle profile_img">
              </div>
              <div class="profile_info">
                <span>Welcome,</span>
                
                
                
                <h2><% out.print(thanhvien.getHoTen()); %></h2>
              </div>
            </div>
            <!-- /menu profile quick info -->

            <br />

            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
                <h3>General</h3>
                <ul class="nav side-menu">
                  
                    
                  <li><a href="${contextPath}/admin"><i class="fa fa-home"></i> Home</a></li>
                  
                  <% if (phanQuyen.getQlThanhVien() == 1){ %>    
                  <li><a href="${contextPath}/admin/thanhvien"><i class="fa fa-user"></i> Thành viên</a></li>
                  <% } %>
                  
                  <% if (phanQuyen.getQlNhaCungCap()== 1){ %> 
                  <li><a href="${contextPath}/admin/nhacungcap"><i class="fa fa-truck"></i> Nhà cung cấp</a></li>
                  <% } %>
                  
                  
                  <% if (phanQuyen.getQlPhiShip()== 1){ %> 
                  <li><a href="${contextPath}/admin/phiship"><i class="fa fa-money"></i> Phí ship</a></li>
                  <% } %>
                  
                  <% if (phanQuyen.getQlPhanQuyen()== 1){ %> 
                  <li><a href="${contextPath}/admin/phanquyen"><i class="fa fa-flash"></i> Phân quyền</a></li>
                  <% } %>
                  
                  <% if (phanQuyen.getQlPhieuChi()== 1){ %> 
                  <li><a href="${contextPath}/admin/phieuchi"><i class="fa fa-wpforms"></i> Phiếu chi</a></li>
                  <% } %>
                  
                  <% if (phanQuyen.getQlSach()== 1){ %> 
                  <li><a><i class="fa fa-book"></i> Sách <span class="fa fa-chevron-down"></span></a>
                      <ul class="nav child_menu">
                          <li><a href="${contextPath}/admin/sach">Danh sách</a></li>
                          <li><a href="${contextPath}/admin/sach/add">Thêm mới</a></li>
                          <li><a href="${contextPath}/admin/theloai">Thể loại</a></li>
                      </ul>
                  </li> 
                   
                  
                  
                  <% } %>
                  
                  <% if (phanQuyen.getQlPhieuNhap()== 1){ %> 
                  <li><a><i class="fa fa-list"></i> Phiếu nhập sách <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="${contextPath}/admin/phieunhap">Danh sách</a></li>
                      <li><a href="${contextPath}/admin/phieunhap/add">Thêm mới</a></li>
                    </ul>
                  </li> 
                  <% } %>
                  
                  <% if (phanQuyen.getQlDonHang()== 1){ %> 
                   <li><a><i class="fa fa-ticket"></i> Đơn hàng <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="${contextPath}/admin/donhang">Danh sách</a></li>
                      <li><a href="${contextPath}/admin/donhang/add">Thêm mới</a></li>
                    </ul>
                  </li>
                  <% } %>
                  <% if (phanQuyen.getQlBaoCao() == 1) { %> 

                  <li><a><i class="fa fa-pie-chart"></i> Báo cáo <span class="fa fa-chevron-down"></span></a>
                      <ul class="nav child_menu">
                          <li><a href="${contextPath}/admin/baocao/doanhthu">Báo cáo doanh thu</a></li>
                          <li><a href="${contextPath}/admin/baocao/ton">Báo cáo tồn</a></li>
                      </ul>
                  </li>
                  <% } %>
                </ul>
              </div>
 
            </div>
         
          </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
          <div class="nav_menu">
            <nav>
              <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
              </div>

              <ul class="nav navbar-nav navbar-right">
                <li class="">
                  <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                    <img src="<% out.print(urlAvatar); %>" alt=""><% out.print(thanhvien.getTenDangNhap()); %>
                    <span class=" fa fa-angle-down"></span>
                  </a>
                  <ul class="dropdown-menu dropdown-usermenu pull-right">
                    <li><a href="javascript:"> Profile</a></li>
                    <li>
                      <a href="javascript:;">
                        <span class="badge bg-red pull-right">50%</span>
                        <span>Settings</span>
                      </a>
                    </li>
                    <li><a href="javascript:;">Help</a></li>
                    <li><a href="${contextPath}/logout"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                  </ul>
                </li>
 
              </ul>
            </nav>
          </div>
        </div>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
		 
