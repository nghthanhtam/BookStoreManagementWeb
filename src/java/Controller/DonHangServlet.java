/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DonHangModel;
import Model.DonHangModelWithTenThanhVienAndTenDangNhap;
import Model.PhiShipModel;
import Model.SachModel;
import Model.SachModelWithTheLoaiAndTrangThai;
import Model.CTDonHangModel;
import Utility.MyUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.DonHangModel;
import Model.MessagesModel;
import Model.PhiShipModel;
import Model.SachModel;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

 /*
@WebServlet(name = "DonDatHangServlet", urlPatterns = {"/admin/donhang"})
 */

@WebServlet(name = "DonHangServlet", urlPatterns = {"/donhang"}) 

public class DonHangServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 
        req.setAttribute("txtTitle", "Đơn hàng của tôi");

        HttpSession session = req.getSession();
        if (MyUtils.getLoginedThanhVien(session) == null) // chưa đăng nhập
        {

            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Oops!", "Đăng nhập để xem đơn hàng!", MessagesModel.ATT_TYPE_ERROR));
            req.getRequestDispatcher("home.jsp").forward(req, resp);
        } else {
            
            Connection conn = MyUtils.getStoredConnection(req);

            //HttpSession session = ((HttpServletRequest) req).getSession();       
            int maThanhVien = MyUtils.getLoginedThanhVien(session).getMaThanhVien();

            List<DonHangModel> listDonHang = DonHangModel.getAllDonHangByMaThanhVien(conn, maThanhVien);
            req.setAttribute("listDonHang", listDonHang);

            List<CTDonHangModel> listCTDonHang = CTDonHangModel.getAllCTDonHang(conn);
            req.setAttribute("listCTDonHang", listCTDonHang);

            List<SachModel> listSach = SachModel.getAllSach(conn);
            req.setAttribute("listSach", listSach);

            req.getRequestDispatcher("list-donhang.jsp").forward(req, resp);

        }
      
      
      /*
      
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
            Logger.getLogger(DonHangServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        req.setAttribute("numofpage", numOfPage);
        req.setAttribute("currentpage", currentPage);
        req.setAttribute("listDonHang", listDonHang);

        req.getRequestDispatcher("/admin/list-donhang.jsp").forward(req, resp);
      
      */
      
 
    }

}
