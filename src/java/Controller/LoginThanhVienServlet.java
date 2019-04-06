/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ThanhVienModel;
import Utility.MyUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MITICC06
 */
@WebServlet(name = "LoginThanhVienServlet", urlPatterns = {"/login"})
public class LoginThanhVienServlet extends HttpServlet {

      
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     }
 
   
@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tendangnhap = req.getParameter("tendangnhap");
        String matkhau = req.getParameter("matkhau");

        ThanhVienModel thanhvien = null;

        if (tendangnhap == null || matkhau == null || tendangnhap.length() == 0 || matkhau.length() == 0) {
 
        } else {
            Connection conn = MyUtils.getStoredConnection(req);
            try {
                thanhvien = ThanhVienModel.FindByTenDangNhap(conn, tendangnhap);
            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (thanhvien != null && matkhau.equals(thanhvien.getMatKhau())) { 
                MyUtils.storeLoginedThanhVien(req.getSession(), thanhvien); // Lưu user vào session
                req.setAttribute("noiDungThongBao", "Bạn đã đăng nhập thành công với tên: " +thanhvien.getTenDangNhap());
                req.setAttribute("urlReturn", req.getHeader("referer"));
                req.getRequestDispatcher("/trangthailogin.jsp").forward(req, resp);
                
            } else { 
                req.setAttribute("noiDungThongBao", "Đăng nhập thất bại!");
                req.setAttribute("urlReturn", req.getHeader("referer"));
                req.getRequestDispatcher("/trangthailogin.jsp").forward(req, resp);
                
                

            }
        }
    }
}
