<%-- 
    Document   : baocaoton
    Created on : Jun 4, 2019, 11:44:08 AM
    Author     : MITICC06
--%> 

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/admin/header.jsp" />



<div class="page-title">
    <div class="title_left">
        <h3>Thống kê</h3>
    </div>

</div>
 
<div class="row">
    <div class="row">
        <div class="col-md-12 col-xs-12">
            <div class="x_panel">
                 
              
                <div class="col-md-12 col-sm-12 col-xs-12">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>Thống kê tồn sách</h2>
                            <ul class="nav navbar-right panel_toolbox">
                                <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                </li>
                                
                                <li><a class="close-link"><i class="fa fa-close"></i></a>
                                </li>
                            </ul>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">


                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Mã sách</th>
                                        <th>Tên sách</th>
                                        <th>Số lượng tồn</th> 
                                    </tr>
                                </thead>
                                <tbody> 
                                    ${tableHTML} 
                                </tbody>
                            </table> 
                                     
                                 
                        </div>
                    </div>
                </div> 
            </div>
        </div> 
    </div>   
</div> 

<jsp:include page="/admin/footer.jsp" />
        