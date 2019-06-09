<%-- 
    Document   : footer
    Created on : Mar 11, 2019, 9:42:47 AM
    Author     : MITICC06
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!-- FOOTER -->
<footer id="footer" class="section section-grey">
    <!-- container -->
    <div class="container">
        <!-- row -->
       
  
        
        <!-- row -->
        <div class="row">
            <div class="col-md-8 col-md-offset-2 text-center">
                <!-- footer copyright -->
                <div class="footer-copyright">
                    Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved
                </div>
                <!-- /footer copyright -->
            </div>
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</footer>
<!-- /FOOTER -->

<!-- jQuery Plugins -->
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/slick.min.js"></script>
<script src="js/nouislider.min.js"></script>
<script src="js/jquery.zoom.min.js"></script>
<script src="js/main.js"></script>

<!-- PNotify -->
<script src="/vendors/pnotify/dist/pnotify.js"></script>
<script src="/vendors/pnotify/dist/pnotify.buttons.js"></script>



<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>  


    
        <script type="text/javascript">
            $(document).ready(function () {

                $('#search').autocomplete({
                    source: '${contextPath}/ajax/sach',
                    select: function (event, ui) {
                        this.value = ui.item.label;
                        window.location.href = '${contextPath}/product?masach='+ui.item.value;
                        return false;
                    }
                });

            });
            
        </script>
        
		<script src="js/maintest.js"></script> <!-- Resource jQuery -->
        
                <!--
                
                
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

		<script>
			if( !window.jQuery ) document.write('<script src="js/jquery-1.4.2.min.js"><\/script>');
		</script>
                
                -->
        
</body>

</html>
