
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>List sản phẩm</title>
 </head>
 <body>
 
   
    <h3>List sản phẩm</h3>
 
 
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Mã sách</th>
          <th>Tên sách</th>
          <th>Đơn giá</th>
          <th>Chỉnh sửa</th>
          <th>Xóa</th>
       </tr>
       <c:forEach items="${listAllSach}" var="obj" >
          <tr>
             <td>${obj.getMaSach()}</td>
             <td>${obj.getTenSach()}</td>
             <td>${obj.getGiaBan()}</td>
             <td>
                <a href="editProduct?code=${obj.getMaSach()}">Chỉnh sửa</a>
             </td>
             <td>
                <a href="deleteProduct?code=${obj.getMaSach()}">Xóa</a>
             </td>
          </tr>
       </c:forEach>
    </table>
 
 
 </body>
</html>