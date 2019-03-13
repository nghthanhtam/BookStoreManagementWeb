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
 * @author Admin
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/createaccountservlet"})
public class CreateAccountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String button1 = req.getParameter("Create");
        if(button1!=null)
        {
            String tendangnhap = req.getParameter("tenthanhvien");
            String matkhau = req.getParameter("matkhau");
            Integer vaitro=1;
            Connection conn = MyUtils.getStoredConnection(req);
            try {
                ThanhVienModel.InsertNewThanhVien(conn, tendangnhap, matkhau, vaitro);
            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            req.getRequestDispatcher("/admin/create-account.jsp").forward(req, resp); ;
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         req.getRequestDispatcher("/admin/create-account.jsp").forward(req, resp); ;
    }

    
}
