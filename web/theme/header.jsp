<%-- 
    Document   : header
    Created on : Mar 11, 2019, 9:42:30 AM
    Author     : MITICC06
--%>

<%@page import="Model.TheLoaiModel"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Connection"%>
<%@page import="Model.MessagesModel"%>
<%@page import="Utility.MyUtils"%>
<%@page import="Model.ThanhVienModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="abc" value="29081998"/>

<%
    ThanhVienModel thanhvien = MyUtils.getLoginedThanhVien(session);
%>

<!DOCTYPE html>
<html lang="vi">

    <head>
    
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>${txtTitle == null ? "Book Store" : txtTitle}</title>

        <!-- Google font -->
        <link href="https://fonts.googleapis.com/css?family=Hind:400,700" rel="stylesheet">

        <!-- Bootstrap -->
        <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />

        <!-- Slick -->
        <link type="text/css" rel="stylesheet" href="css/slick.css" />
        <link type="text/css" rel="stylesheet" href="css/slick-theme.css" />

        <!-- nouislider -->
        <link type="text/css" rel="stylesheet" href="css/nouislider.min.css" />

        <!-- Font Awesome Icon -->
        <link rel="stylesheet" href="css/font-awesome.min.css">

        <!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="css/style.css" />

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
                  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
                  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
                <![endif]-->


        <!-- PNotify -->
        <link href="/vendors/pnotify/dist/pnotify.css" rel="stylesheet">
        <link href="/vendors/pnotify/dist/pnotify.buttons.css" rel="stylesheet"> 
        
        
        
        
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">





       



    </head>
    

    
    
    
    
    

    <body<%
        MessagesModel messagesModel = (MessagesModel) request.getAttribute(MessagesModel.ATT_STORE);
        if (messagesModel != null) {
            out.print(" onload=\"new PNotify({"
                    + "title: '" + messagesModel.getTitle() + "',"
                    + "text: '" + messagesModel.getText() + "',"
                    + "type: '" + messagesModel.getType() + "',"
                    + "styling: 'bootstrap3'"
                    + "});\"");
        }

        %>>




        <!-- HEADER -->
        <header>
            <!-- top Header -->
            <div id="top-header">
                <div class="container">
                    <div class="pull-left">
                        <span>Welcome to Book Store!</span>
                    </div>
                    <div class="pull-right">
                        <ul class="header-top-links">
                            <li><a href="#">Store</a></li>
                            <li><a href="#">Newsletter</a></li>
                            <li><a href="#">FAQ</a></li>
                            <li class="dropdown default-dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">ENG <i class="fa fa-caret-down"></i></a>
                                <ul class="custom-menu">
                                    <li><a href="#">English (ENG)</a></li>
                                    <li><a href="#">Russian (Ru)</a></li>
                                    <li><a href="#">French (FR)</a></li>
                                    <li><a href="#">Spanish (Es)</a></li>
                                </ul>
                            </li>
                            <li class="dropdown default-dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">USD <i class="fa fa-caret-down"></i></a>
                                <ul class="custom-menu">
                                    <li><a href="#">USD ($)</a></li>
                                    <li><a href="#">EUR (€)</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- /top Header -->

            <!-- header -->
            <div id="header">
                <div class="container">
                    <div class="pull-left">
                        <!-- Logo -->
                        <div class="header-logo">
                            <a class="logo" href="/">
                                <img src="./img/logo.png" alt="">
                            </a>
                        </div>
                        <!-- /Logo -->

                        <!-- Search -->
                        <div class="header-search">
                            <form class="form-horizontal form-label-left" method="GET" action="${contextPath}/search">
                                <input class="input search-input" type="text" required="required" placeholder="Enter your keyword" value="${tukhoa == null ?"":tukhoa}" id="search" name="tukhoa">
                                <select  name="matheloai" id="theloai" class="input search-categories">
                                    <option value="" disabled hidden>Chọn thể loại</option>
                                    <option value="0" selected="selected">Tất cả</option>
                                    <%                                        Connection conn = MyUtils.getStoredConnection(request);

                                        List<TheLoaiModel> listAllTheLoai = TheLoaiModel.getAllTheLoai(conn);
                                        String temp= request.getParameter("matheloai");
                                        int maTheLoai;
                                        if(temp!=null)
                                            maTheLoai= Integer.parseInt(temp);
                                        else
                                            maTheLoai=0;
                                                        
                                                        
                                        
                                        
                                        
                                        
                                        for (TheLoaiModel obj : listAllTheLoai) {
                                            if(obj.getMaTheLoai()== maTheLoai)
                                            out.print("<option selected=\"selected\"  value=\"" + obj.getMaTheLoai() + "\">" + obj.getTenTheLoai() + "</option>");
                                            else
                                                
                                            out.print("<option  value=\"" + obj.getMaTheLoai() + "\">" + obj.getTenTheLoai() + "</option>");
                                        }


                                    %> 



                                </select>

                                <button class="search-btn"><i class="fa fa-search"></i></button>
                            </form>
                        </div>
                        <!-- /Search -->
                    </div>
                    <div class="pull-right">
                        <ul class="header-btns">
                            <!-- Account -->


                            <%                                if (thanhvien != null) {
                            %>


                            <li class="header-account dropdown default-dropdown">
                                <div class="dropdown-toggle" role="button" data-toggle="dropdown" aria-expanded="true">

                                    <img class="header-btns-icon" src="<% out.print("https://www.gravatar.com/avatar/" + MyUtils.MD5(thanhvien.getEmail()) + "?size=40"); %>" alt="">


                                    <strong class="text-uppercase">My Account <i class="fa fa-caret-down"></i></strong>
                                </div>
                                <% out.print(thanhvien.getHoTen()); %>



                                <ul class="custom-menu">
                                    <li><a href="#"><i class="fa fa-user-o"></i> My Account</a></li>
                                    <li><a href="#"><i class="fa fa-heart-o"></i> My Wishlist</a></li>
                                    <li><a href="#"><i class="fa fa-exchange"></i> Compare</a></li>
                                    <li><a href="#"><i class="fa fa-check"></i> Checkout</a></li>
                                    <li><a href="${contextPath}/logout"><i class="fa fa-sign-out"></i> Logout</a></li> 
                                </ul>
                            </li>


                            <%
                            } else {
                            %>


                            <li class="header-account dropdown default-dropdown">
                                <div>
                                    <div class="header-btns-icon">
                                        <i class="fa fa-user-o"></i>
                                    </div>
                                    <strong class="text-uppercase">Hi, Guess</strong>
                                </div>

                                <a href="#" class="dropdown-toggle dropdown-toggle-inline" role="button" data-toggle="dropdown" aria-expanded="true">LOGIN</a> / <a href="${contextPath}/dangky" style="display:inline">JOIN</a>   
                                <ul class="custom-menu">
                                    <form action="${contextPath}/login" method="POST">
                                        <input class="input input-form-dangnhap" type="text" name="tendangnhap" placeholder="Username">
                                        <input class="input input-form-dangnhap" type="password" name="matkhau" placeholder="Password">

                                        <button type="submit" value="login" class="primary-btn btn-form-dangnhap">Login</button>    

                                    </form>
                                </ul>
                            </li>

                            <%
                                }
                            %>                                                


                            <!-- /Account -->

                            <!-- Cart -->
                            <li class="header-cart dropdown default-dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                                    <div class="header-btns-icon">
                                        <i class="fa fa-shopping-cart"></i>
                                        <span class="qty">3</span>
                                    </div>
                                    <strong class="text-uppercase">My Cart:</strong>
                                    <br>
                                    <span>35.20$</span>
                                </a>
                                <div class="custom-menu">
                                    <div id="shopping-cart">
                                        <div class="shopping-cart-list">
                                            <div class="product product-widget">
                                                <div class="product-thumb">
                                                    <img src="./img/thumb-product01.jpg" alt="">
                                                </div>
                                                <div class="product-body">
                                                    <h3 class="product-price">$32.50 <span class="qty">x3</span></h3>
                                                    <h2 class="product-name"><a href="#">Product Name Goes Here</a></h2>
                                                </div>
                                                <button class="cancel-btn"><i class="fa fa-trash"></i></button>
                                            </div>
                                            <div class="product product-widget">
                                                <div class="product-thumb">
                                                    <img src="./img/thumb-product01.jpg" alt="">
                                                </div>
                                                <div class="product-body">
                                                    <h3 class="product-price">$32.50 <span class="qty">x3</span></h3>
                                                    <h2 class="product-name"><a href="#">Product Name Goes Here</a></h2>
                                                </div>
                                                <button class="cancel-btn"><i class="fa fa-trash"></i></button>
                                            </div>
                                        </div>
                                        <div class="shopping-cart-btns">
                                            <button class="main-btn">View Cart</button>
                                            <button class="primary-btn">Checkout <i class="fa fa-arrow-circle-right"></i></button>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <!-- /Cart -->

                            <!-- Mobile nav toggle-->
                            <li class="nav-toggle">
                                <button class="nav-toggle-btn main-btn icon-btn"><i class="fa fa-bars"></i></button>
                            </li>
                            <!-- / Mobile nav toggle -->
                        </ul>
                    </div>
                </div>
                <!-- header -->
            </div>
            <!-- container -->
        </header>
        <!-- /HEADER -->

        <!-- NAVIGATION -->
        <div id="navigation">
            <!-- container -->
            <div class="container">
                <div id="responsive-nav">
                    <!-- category nav --> 
                    <div class="category-nav ${requestScope['javax.servlet.forward.request_uri'] == '/' ? "" : "show-on-click"}">
                        <span class="category-header">Categories <i class="fa fa-list"></i></span>
                        <ul class="category-list">
                            <li class="dropdown side-dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">Women’s Clothing <i class="fa fa-angle-right"></i></a>
                                <div class="custom-menu">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <ul class="list-links">
                                                <li>
                                                    <h3 class="list-links-title">Categories</h3></li>
                                                <li><a href="#">Women’s Clothing</a></li>
                                                <li><a href="#">Men’s Clothing</a></li>
                                                <li><a href="#">Phones & Accessories</a></li>
                                                <li><a href="#">Jewelry & Watches</a></li>
                                                <li><a href="#">Bags & Shoes</a></li>
                                            </ul>
                                            <hr class="hidden-md hidden-lg">
                                        </div>
                                        <div class="col-md-4">
                                            <ul class="list-links">
                                                <li>
                                                    <h3 class="list-links-title">Categories</h3></li>
                                                <li><a href="#">Women’s Clothing</a></li>
                                                <li><a href="#">Men’s Clothing</a></li>
                                                <li><a href="#">Phones & Accessories</a></li>
                                                <li><a href="#">Jewelry & Watches</a></li>
                                                <li><a href="#">Bags & Shoes</a></li>
                                            </ul>
                                            <hr class="hidden-md hidden-lg">
                                        </div>
                                        <div class="col-md-4">
                                            <ul class="list-links">
                                                <li>
                                                    <h3 class="list-links-title">Categories</h3></li>
                                                <li><a href="#">Women’s Clothing</a></li>
                                                <li><a href="#">Men’s Clothing</a></li>
                                                <li><a href="#">Phones & Accessories</a></li>
                                                <li><a href="#">Jewelry & Watches</a></li>
                                                <li><a href="#">Bags & Shoes</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="row hidden-sm hidden-xs">
                                        <div class="col-md-12">
                                            <hr>
                                            <a class="banner banner-1" href="#">
                                                <img src="./img/banner05.jpg" alt="">
                                                <div class="banner-caption text-center">
                                                    <h2 class="white-color">NEW COLLECTION</h2>
                                                    <h3 class="white-color font-weak">HOT DEAL</h3>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li><a href="#">Men’s Clothing</a></li>
                            <li class="dropdown side-dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">Phones & Accessories <i class="fa fa-angle-right"></i></a>
                                <div class="custom-menu">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <ul class="list-links">
                                                <li>
                                                    <h3 class="list-links-title">Categories</h3></li>
                                                <li><a href="#">Women’s Clothing</a></li>
                                                <li><a href="#">Men’s Clothing</a></li>
                                                <li><a href="#">Phones & Accessories</a></li>
                                                <li><a href="#">Jewelry & Watches</a></li>
                                                <li><a href="#">Bags & Shoes</a></li>
                                            </ul>
                                            <hr>
                                            <ul class="list-links">
                                                <li>
                                                    <h3 class="list-links-title">Categories</h3></li>
                                                <li><a href="#">Women’s Clothing</a></li>
                                                <li><a href="#">Men’s Clothing</a></li>
                                                <li><a href="#">Phones & Accessories</a></li>
                                                <li><a href="#">Jewelry & Watches</a></li>
                                                <li><a href="#">Bags & Shoes</a></li>
                                            </ul>
                                            <hr class="hidden-md hidden-lg">
                                        </div>
                                        <div class="col-md-4">
                                            <ul class="list-links">
                                                <li>
                                                    <h3 class="list-links-title">Categories</h3></li>
                                                <li><a href="#">Women’s Clothing</a></li>
                                                <li><a href="#">Men’s Clothing</a></li>
                                                <li><a href="#">Phones & Accessories</a></li>
                                                <li><a href="#">Jewelry & Watches</a></li>
                                                <li><a href="#">Bags & Shoes</a></li>
                                            </ul>
                                            <hr>
                                            <ul class="list-links">
                                                <li>
                                                    <h3 class="list-links-title">Categories</h3></li>
                                                <li><a href="#">Women’s Clothing</a></li>
                                                <li><a href="#">Men’s Clothing</a></li>
                                                <li><a href="#">Phones & Accessories</a></li>
                                                <li><a href="#">Jewelry & Watches</a></li>
                                                <li><a href="#">Bags & Shoes</a></li>
                                            </ul>
                                        </div>
                                        <div class="col-md-4 hidden-sm hidden-xs">
                                            <a class="banner banner-2" href="#">
                                                <img src="./img/banner04.jpg" alt="">
                                                <div class="banner-caption">
                                                    <h3 class="white-color">NEW<br>COLLECTION</h3>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li><a href="#">Computer & Office</a></li>
                            <li><a href="#">Consumer Electronics</a></li>
                            <li class="dropdown side-dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">Jewelry & Watches <i class="fa fa-angle-right"></i></a>
                                <div class="custom-menu">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <ul class="list-links">
                                                <li>
                                                    <h3 class="list-links-title">Categories</h3></li>
                                                <li><a href="#">Women’s Clothing</a></li>
                                                <li><a href="#">Men’s Clothing</a></li>
                                                <li><a href="#">Phones & Accessories</a></li>
                                                <li><a href="#">Jewelry & Watches</a></li>
                                                <li><a href="#">Bags & Shoes</a></li>
                                            </ul>
                                            <hr>
                                            <ul class="list-links">
                                                <li>
                                                    <h3 class="list-links-title">Categories</h3></li>
                                                <li><a href="#">Women’s Clothing</a></li>
                                                <li><a href="#">Men’s Clothing</a></li>
                                                <li><a href="#">Phones & Accessories</a></li>
                                                <li><a href="#">Jewelry & Watches</a></li>
                                                <li><a href="#">Bags & Shoes</a></li>
                                            </ul>
                                            <hr class="hidden-md hidden-lg">
                                        </div>
                                        <div class="col-md-4">
                                            <ul class="list-links">
                                                <li>
                                                    <h3 class="list-links-title">Categories</h3></li>
                                                <li><a href="#">Women’s Clothing</a></li>
                                                <li><a href="#">Men’s Clothing</a></li>
                                                <li><a href="#">Phones & Accessories</a></li>
                                                <li><a href="#">Jewelry & Watches</a></li>
                                                <li><a href="#">Bags & Shoes</a></li>
                                            </ul>
                                            <hr>
                                            <ul class="list-links">
                                                <li>
                                                    <h3 class="list-links-title">Categories</h3></li>
                                                <li><a href="#">Women’s Clothing</a></li>
                                                <li><a href="#">Men’s Clothing</a></li>
                                                <li><a href="#">Phones & Accessories</a></li>
                                                <li><a href="#">Jewelry & Watches</a></li>
                                                <li><a href="#">Bags & Shoes</a></li>
                                            </ul>
                                            <hr class="hidden-md hidden-lg">
                                        </div>
                                        <div class="col-md-4">
                                            <ul class="list-links">
                                                <li>
                                                    <h3 class="list-links-title">Categories</h3></li>
                                                <li><a href="#">Women’s Clothing</a></li>
                                                <li><a href="#">Men’s Clothing</a></li>
                                                <li><a href="#">Phones & Accessories</a></li>
                                                <li><a href="#">Jewelry & Watches</a></li>
                                                <li><a href="#">Bags & Shoes</a></li>
                                            </ul>
                                            <hr>
                                            <ul class="list-links">
                                                <li>
                                                    <h3 class="list-links-title">Categories</h3></li>
                                                <li><a href="#">Women’s Clothing</a></li>
                                                <li><a href="#">Men’s Clothing</a></li>
                                                <li><a href="#">Phones & Accessories</a></li>
                                                <li><a href="#">Jewelry & Watches</a></li>
                                                <li><a href="#">Bags & Shoes</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li><a href="#">Bags & Shoes</a></li>
                            <li><a href="#">View All</a></li>
                        </ul>
                    </div>
                    <!-- /category nav -->

                    <!-- menu nav -->
                    <div class="menu-nav">
                        <span class="menu-header">Menu <i class="fa fa-bars"></i></span>
                        <ul class="menu-list">
                            <li><a href="#">Home</a></li>
                            <li><a href="#">Shop</a></li>
                            <li class="dropdown mega-dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">Women <i class="fa fa-caret-down"></i></a>
                                <div class="custom-menu">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <ul class="list-links">
                                                <li>
                                                    <h3 class="list-links-title">Categories</h3></li>
                                                <li><a href="#">Women’s Clothing</a></li>
                                                <li><a href="#">Men’s Clothing</a></li>
                                                <li><a href="#">Phones & Accessories</a></li>
                                                <li><a href="#">Jewelry & Watches</a></li>
                                                <li><a href="#">Bags & Shoes</a></li>
                                            </ul>
                                            <hr class="hidden-md hidden-lg">
                                        </div>
                                        <div class="col-md-4">
                                            <ul class="list-links">
                                                <li>
                                                    <h3 class="list-links-title">Categories</h3></li>
                                                <li><a href="#">Women’s Clothing</a></li>
                                                <li><a href="#">Men’s Clothing</a></li>
                                                <li><a href="#">Phones & Accessories</a></li>
                                                <li><a href="#">Jewelry & Watches</a></li>
                                                <li><a href="#">Bags & Shoes</a></li>
                                            </ul>
                                            <hr class="hidden-md hidden-lg">
                                        </div>
                                        <div class="col-md-4">
                                            <ul class="list-links">
                                                <li>
                                                    <h3 class="list-links-title">Categories</h3></li>
                                                <li><a href="#">Women’s Clothing</a></li>
                                                <li><a href="#">Men’s Clothing</a></li>
                                                <li><a href="#">Phones & Accessories</a></li>
                                                <li><a href="#">Jewelry & Watches</a></li>
                                                <li><a href="#">Bags & Shoes</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="row hidden-sm hidden-xs">
                                        <div class="col-md-12">
                                            <hr>
                                            <a class="banner banner-1" href="#">
                                                <img src="./img/banner05.jpg" alt="">
                                                <div class="banner-caption text-center">
                                                    <h2 class="white-color">NEW COLLECTION</h2>
                                                    <h3 class="white-color font-weak">HOT DEAL</h3>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="dropdown mega-dropdown full-width"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">Men <i class="fa fa-caret-down"></i></a>
                                <div class="custom-menu">
                                    <div class="row">
                                        <div class="col-md-3">
                                            <div class="hidden-sm hidden-xs">
                                                <a class="banner banner-1" href="#">
                                                    <img src="./img/banner06.jpg" alt="">
                                                    <div class="banner-caption text-center">
                                                        <h3 class="white-color text-uppercase">Women’s</h3>
                                                    </div>
                                                </a>
                                                <hr>
                                            </div>
                                            <ul class="list-links">
                                                <li>
                                                    <h3 class="list-links-title">Categories</h3></li>
                                                <li><a href="#">Women’s Clothing</a></li>
                                                <li><a href="#">Men’s Clothing</a></li>
                                                <li><a href="#">Phones & Accessories</a></li>
                                                <li><a href="#">Jewelry & Watches</a></li>
                                                <li><a href="#">Bags & Shoes</a></li>
                                            </ul>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="hidden-sm hidden-xs">
                                                <a class="banner banner-1" href="#">
                                                    <img src="./img/banner07.jpg" alt="">
                                                    <div class="banner-caption text-center">
                                                        <h3 class="white-color text-uppercase">Men’s</h3>
                                                    </div>
                                                </a>
                                            </div>
                                            <hr>
                                            <ul class="list-links">
                                                <li>
                                                    <h3 class="list-links-title">Categories</h3></li>
                                                <li><a href="#">Women’s Clothing</a></li>
                                                <li><a href="#">Men’s Clothing</a></li>
                                                <li><a href="#">Phones & Accessories</a></li>
                                                <li><a href="#">Jewelry & Watches</a></li>
                                                <li><a href="#">Bags & Shoes</a></li>
                                            </ul>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="hidden-sm hidden-xs">
                                                <a class="banner banner-1" href="#">
                                                    <img src="./img/banner08.jpg" alt="">
                                                    <div class="banner-caption text-center">
                                                        <h3 class="white-color text-uppercase">Accessories</h3>
                                                    </div>
                                                </a>
                                            </div>
                                            <hr>
                                            <ul class="list-links">
                                                <li>
                                                    <h3 class="list-links-title">Categories</h3></li>
                                                <li><a href="#">Women’s Clothing</a></li>
                                                <li><a href="#">Men’s Clothing</a></li>
                                                <li><a href="#">Phones & Accessories</a></li>
                                                <li><a href="#">Jewelry & Watches</a></li>
                                                <li><a href="#">Bags & Shoes</a></li>
                                            </ul>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="hidden-sm hidden-xs">
                                                <a class="banner banner-1" href="#">
                                                    <img src="./img/banner09.jpg" alt="">
                                                    <div class="banner-caption text-center">
                                                        <h3 class="white-color text-uppercase">Bags</h3>
                                                    </div>
                                                </a>
                                            </div>
                                            <hr>
                                            <ul class="list-links">
                                                <li>
                                                    <h3 class="list-links-title">Categories</h3></li>
                                                <li><a href="#">Women’s Clothing</a></li>
                                                <li><a href="#">Men’s Clothing</a></li>
                                                <li><a href="#">Phones & Accessories</a></li>
                                                <li><a href="#">Jewelry & Watches</a></li>
                                                <li><a href="#">Bags & Shoes</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li><a href="#">Sales</a></li>
                            <li class="dropdown default-dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">Pages <i class="fa fa-caret-down"></i></a>
                                <ul class="custom-menu">
                                    <li><a href="index.html">Home</a></li>
                                    <li><a href="products.html">Products</a></li>
                                    <li><a href="product-page.html">Product Details</a></li>
                                    <li><a href="checkout.html">Checkout</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <!-- menu nav -->
                </div>
            </div>
            <!-- /container -->
        </div>
        <!-- /NAVIGATION -->