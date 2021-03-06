/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
 
import Model.DonHangModelWithTenThanhVienAndTenDangNhap;
import Utility.MyUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
 
@WebServlet(name = "DonHangServlet", urlPatterns = {"/admin/donhang"})
 
public class DonHangServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  
        req.setAttribute("txtTitle", "Đơn hàng");

        Integer numOfDonHangInOnePage = 20;

        Integer currentPage;
        int numOfPage = 0;
        String temp = req.getParameter("page");
        if (req.getParameter("page") != null) {
            currentPage = Integer.parseInt(temp);
        } else {
            currentPage = 1;
        }

        Connection conn = MyUtils.getStoredConnection(req);
        List<DonHangModelWithTenThanhVienAndTenDangNhap> listDonHang = DonHangModelWithTenThanhVienAndTenDangNhap.getAllDonHang(conn, currentPage, numOfDonHangInOnePage);

        int numOfBookFound;

        try {
            numOfBookFound = DonHangModelWithTenThanhVienAndTenDangNhap.CountAllDonHang(conn);

            if (numOfBookFound % numOfDonHangInOnePage == 0) {
                numOfPage = numOfBookFound / numOfDonHangInOnePage;
            } else {
                numOfPage = numOfBookFound / numOfDonHangInOnePage + 1;
            }
        } catch (SQLException ex) {
         }

        
        req.setAttribute("numofpage", numOfPage);
        req.setAttribute("currentpage", currentPage);
        req.setAttribute("listDonHang", listDonHang);

        req.getRequestDispatcher("/admin/list-donhang.jsp").forward(req, resp);
       
      
 
    }

}
