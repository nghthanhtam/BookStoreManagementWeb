/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DonHangModel;
import Model.DonHangModelWithTenThanhVienAndTenDangNhap;
import Model.PhiShipModel;
import Model.SachModelWithTheLoaiAndTrangThai;
import Utility.MyUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dell
 */
@WebServlet(name = "DonDatHangServlet", urlPatterns = {"/admin/donhang"})
public class DonHangServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("txtTitle", "Đơn hàng");
        Connection conn = MyUtils.getStoredConnection(req);
        List<DonHangModelWithTenThanhVienAndTenDangNhap> listDonHang = DonHangModelWithTenThanhVienAndTenDangNhap.getAllDonHang(conn);
        
        
        
        
        
        req.setAttribute("listDonHang", listDonHang);

        req.getRequestDispatcher("/admin/list-donhang.jsp").forward(req, resp);;
    }

}
