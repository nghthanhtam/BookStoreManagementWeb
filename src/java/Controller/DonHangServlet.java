/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CTDonHangModel;
import Utility.MyUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.DonHangModel;
import Model.PhiShipModel;
import Model.SachModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TamTorres
 */
@WebServlet(name = "DonHangServlet", urlPatterns = {"/donhang"})
public class DonHangServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
      
   
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("txtTitle", "Đơn hàng của tôi");

        Connection conn = MyUtils.getStoredConnection(req);

        List<DonHangModel> listDonHang = DonHangModel.getAllDonHang(conn);
        req.setAttribute("listDonHang", listDonHang);
        
        List<CTDonHangModel> listCTDonHang = CTDonHangModel.getAllCTDonHang(conn);
        req.setAttribute("listCTDonHang", listCTDonHang);

        List<SachModel> listSach = SachModel.getAllSach(conn);
        req.setAttribute("listSach", listSach);
        
        req.getRequestDispatcher("list-donhang.jsp").forward(req, resp);
        
    
    }

}
