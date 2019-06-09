<%-- 
    Document   : baocao
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
                <div class="x_title">
                    <h2>Báo cáo</h2>
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




                    <form  class="form-horizontal form-label-left input_mask" method="POST" action="/admin/baocao/doanhthu">


                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Khoảng thời gian<span class="required">*</span>
                            </label>
                            <div class="col-md-3 col-sm-9 col-xs-12">

                                <fieldset>
                                    <div class="control-group">
                                        <div class="controls">
                                            <div class="input-prepend input-group">
                                                <span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span>


                                                <input type="text" 
                                                       name="khoangthoigian" 
                                                       id="khoangthoigian" 
                                                       class="form-control" 
                                                       value="">
                                            </div>
                                        </div>
                                    </div>
                                </fieldset>
                            </div>
                        </div> 




                        <div class="ln_solid"></div>
                        <div class="form-group">
                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3"> 
                                <button class="btn btn-primary" type="reset">Reset</button>
                                <button type="submit" class="btn btn-success" name="submit" value="baocaodoanhthu">Lập báo cáo</button>
                            </div>
                        </div>

                    </form>
                </div>



                <div class="col-md-12 col-sm-12 col-xs-12">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>Thống kê doanh thu theo ngày</h2>
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
                                        <th>Ngày</th>
                                        <th>Đã hoàn tất</th>
                                        <th>Chờ xử lí</th>
                                        <th>Tổng</th>
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

<script>


    $(function () {
        $("#khoangthoigian").daterangepicker({
            locale: {
                format: 'DD/MM/YYYY'
            }
        });
    });

</script>              