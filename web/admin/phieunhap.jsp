<%@page import="java.lang.String"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.SachModel"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>

<head>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> 

    <!--
    
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/ui-lightness/jquery-ui.css" />
    
        <link rel="stylesheet" href="https://code.jquery.com/qunit/qunit-1.18.0.css" />
        <link href="${contextPath}/admin/appendGrid/jquery.appendGrid-development.css" rel="stylesheet" />
        <script type="text/javascript" src="https://code.jquery.com/qunit/qunit-1.18.0.js"></script>

    -->

    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
    <script type="text/javascript" src="${contextPath}/admin/appendGrid/jquery.appendGrid-development.js"></script>


<!--
        .appendGrid {
            width: 100%;
            padding-right: 15px;
            padding-left: 15px;
            margin-right: auto;
            margin-left: auto;
        }-->
    <style>

.appendGrid-tieu-de-muc-first {
            color: #495057;
            background-color: #e9ecef;
            border-color: #dee2e6;
            vertical-align: bottom;
            border-bottom: 2px solid #dee2e6;
            border-top: 1px solid #dee2e6;
            padding: 10px;
            font-weight: bold;
            font-size: 1.3rem;


        } 
        
        
        .appendGrid-tieu-de-muc {
            color: #495057;
            background-color: #e9ecef;
            border-color: #dee2e6;
            vertical-align: bottom;
            border-bottom: 2px solid #dee2e6;
            border-top: 1px solid #dee2e6;
            padding: 10px;
            font-weight: bold;
            font-size: 1.3rem;


        } 


    </style>



    <script type="text/javascript">
        $(document).ready(function () {
            $('#searchNhaCungCap').autocomplete({
                source: '${pageContext.request.contextPath}/admin/phieunhap/ajax/nhacungcap',
                select: function (event, ui) {
                    this.value = ui.item.label;
                    $("#manhacungcap").val(ui.item.value);
                    return false;
                }
            });
        });
    </script>
















</head>











<script>

    $(function () {
        // Initialize appendGrid
        $('#tblAppendGrid').appendGrid('init', {
            //caption: 'My CD Collections',
            //captionTooltip: 'This is my CD collection list!',
            initRows: 1,

            columns: [
                {
                    name: "masach",
                    display: "Mã sách",
                    value: "0"
                },
                {
                    name: "tensach",
                    display: "Tên sách",
                    //       ctrlCss: { width: "200px" }, 
                    type: "ui-autocomplete",
                    uiOption: {
                        source: 'http://localhost/admin/nhacungcap/ajaxtimnhacungcap',
                        //[{"value":28,"label":"28 - Nguyá»…n TĂ¢y Trung"},{"value":32,"label":"32 - NXB Tiáº¿n"}], 
                        select: function (event, ui) {
                            this.value = ui.item.label;
                            //alert($('#tblAppendGrid').appendGrid('getCellCtrl', 'masach', 0));
                            //$('#tblAppendGrid').appendGrid('setCtrlValue', 'masach', 0, "xsadasd");
                            console.log(event.target.id);
                            var str = event.target.id;
                            var res = str.replace("tensach", "masach");
                            console.log(res);

                            $('#' + res).val(ui.item.value);
                            return false;
                        }
                    }




                },
                {
                    name: "soluongnhap",
                    display: "Số lượng nhập",
                    type: "number",
                    ctrlAttr: {
                        min: 1
                    }
                },
                {
                    name: "dongia",
                    display: "Đơn giá",
                    type: "number",
                    ctrlAttr: {
                        min: 1
                    }
                }
            ],

            /*
             columns: [
             {
             name: 'Album', 
             display: 'Album', 
             type: 'text', 
             ctrlAttr: { maxlength: 100 },
             
             // Make the column resizable
             resizable: true, 
             ctrlCss: { width: '100%' }, 
             displayCss: { 'min-width': '160px' },
             
             // Customize UI tooltip
             displayTooltip: { items: 'td', content: 'You can resize this column!' }
             },
             { name: 'Artist', display: 'Artist', type: 'ui-autocomplete', ctrlAttr: { maxlength: 100 }, ctrlCss: { width: '100px' }, uiOption: { source: ['Theresa Fu', 'Arashi', 'Show Luo', 'Wonder Girls', 'Kelly Chen']} },
             { name: 'Year', display: 'Year', type: 'ui-spinner', ctrlAttr: { maxlength: 4 }, ctrlCss: { width: '40px' }, uiOption: { min: 2000, max: new Date().getFullYear()} },
             { name: 'Origin', display: 'Origin', type: 'ui-selectmenu', ctrlOptions: ['Hong Kong', 'Taiwan', 'Japan', 'Korea', 'US', 'Others'] },
             { name: 'StockIn', display: 'Stock In', type: 'ui-datepicker', ctrlAttr: { maxlength: 10 }, ctrlCss: { width: '80px' }, uiOption: { dateFormat: 'yy/mm/dd'} },
             { name: 'Price', display: 'Price', type: 'text', ctrlAttr: { maxlength: 10, title: 'Please fill in the price!' }, ctrlCss: { width: '50px', 'text-align': 'right' }, value: 0, uiTooltip: { show: true } }
             ],
             
             
             */

            initData: []
        });
    });
</script>












<div class="page-title">
    <div class="title_left">
        <h3>Nhập sách</h3>
    </div>

</div>



















<div class="row">   

    <div class="row">
        <div class="col-md-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>${txtTitle}</h2>
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


                    <c:set var="urlEditCurrent" value="edit?id=${phieunhap.getMaPhieuNhap()}" />

                    <form  class="form-horizontal form-label-left input_mask" method="POST" enctype="multipart/form-data" action="${contextPath}/admin/phieunhap/${phieunhap==null ? "add" : urlEditCurrent}">

                        <c:if test = "${phieunhap != null}">

                            <div class="form-group">
                                <label class= " control-label col-md-3 col-sm-3 col-xs-12">Mã phiếu nhập</label>
                                <div class=" col-md-3 col-sm-9 col-xs-12">
                                    <input type="text" name="masach" value="${phieunhap == null ? "":phieunhap.getMaPhieuNhap()}" class="form-control col-md-7 col-xs-12" readonly>
                                </div>
                            </div> 
                        </c:if>


                        <div class="form-group">
                            <label class= " control-label col-md-3 col-sm-3 col-xs-12" >Ngày lập phiếu<span class="required">*</span>
                            </label>
                            <div class="col-md-3 col-sm-9 col-xs-12">


                                <input type="text" class="form-control has-feedback-left" name="ngaylapphieu" id="single_cal2"  aria-describedby="inputSuccess2Status2">
                                <span class="fa fa-calendar-o form-control-feedback left" aria-hidden="true"></span>



                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Nhà cung cấp <span class="required"></span>
                            </label>               
                            <div class="col-md-3 col-sm-9 col-xs-12">
                                <input id="searchNhaCungCap" class="form-control col-md-7 col-xs-12" required="required" name="tennhacungcap" type="text" placeholder="Hãy nhập tên nhà cung cấp">
                            </div>                      
                            <input id="manhacungcap" class="form-control col-md-7 col-xs-12" required="required" name="manhacungcap" type="hidden" placeholder="Hãy nhập mã nhà cung cấp">                     
                        </div>    




                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Nhân viên thực hiện<span class="required"></span>
                            </label>
                            <div class="col-md-3 col-sm-9 col-xs-12">
                                <input class="form-control col-md-7 col-xs-12" value="<% out.print(thanhvien.getHoTen());%>" type="text" disabled="">
                            </div> 
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Ghi chú<span class="required"></span>
                            </label>
                            <div class="col-md-3 col-sm-9 col-xs-12">
                                <textarea id="message"
                                          class="form-control" 
                                          name="ghichu"></textarea>                            
                            </div> 
                        </div>













                        <div class="form-group">
                            <div class="col-md-12 col-sm-3 col-xs-12">


                                <table id="tblAppendGrid"></table>


                                <button id="get" type="button" class="btn btn-success">getAllValue(), JSON</button>
                                <button id="load" type="button" class="btn btn-primary">Load Data</button>

                                <pre class="p-2 bg-light"><code id="output" class="d-none"></code></pre>



                            </div>
                        </div>     
                            
                            
                            
                            
                            
                            
                            
                            


                        <div class="ln_solid"></div>
                        <div class="form-group">
                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                <a class="btn btn-primary" href="${contextPath}/admin/sach">Cancel</a>
                                <button class="btn btn-primary" type="reset">Reset</button>
                                <button type="submit" class="btn btn-success" name="submit" value="${nhapsach==null ? "them":"sua"}" >Submit</button>
                            </div>
                        </div>


                    </form>
                </div>
            </div>
        </div>
    </div>  

</div>


<script>








    document.getElementById("get").addEventListener("click", function () {
        var output = document.getElementById("output");
        output.classList.remove("d-none");
        output.innerText = JSON.stringify($('#tblAppendGrid').appendGrid('getAllValue'), null, "  ");
    });





    $("#load").on("click", function () {
        $('#tblAppendGrid').appendGrid('load', [
            {
                masach: "57644023-cd0c-47ec-a556-fd8d4e21a4e7",
                tensach: "Batholomew Zecchii",
                soluongnhap: "24",
                dongia: "25900"
            },
            {
                masach: "38e08e8a-c7eb-41eb-9191-6bb2df1fd39b",
                tensach: "Paulie Poel",
                soluongnhap: "2",
                dongia: "25000"
            },
            {
                masach: "d7bf56d4-f955-4dca-b3db-b30eab590028",
                tensach: "Jessica Levett",
                soluongnhap: "2",
                dongia: "4000"
            },
            {
                masach: "b9075764-5228-4ca7-9435-7c362ce097e5",
                tensach: "Fonsie Spring",
                soluongnhap: "4",
                dongia: "95000"
            }
        ]);
    });




    /*
     $('tblAppendGrid').appendGrid({
     columns: [
     {
     name: 'tensach',
     
     // name: 'AutoCompleteColumn',
     type: 'ui-autocomplete',
     uiOption: {
     // Define the custom options for initalize jQuery UI Autocomplete here
     source: 'http://localhost/admin/sach/add/ajaxtimnhaxuatbansach'
     }
     }
     ]
     });
     */



</script>

<%@include file="footer.jsp" %>