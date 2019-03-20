/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.NhaCungCapModel;
import Model.ThanhVienModel;
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

/**
 *
 * @author Admin
 */
@WebServlet(name = "ThanhVienServlet", urlPatterns = {"/admin/thanhvien"})
public class ThanhVienServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String button = req.getParameter("Create");
        if(button!=null)
        { 
            String tendangnhap = req.getParameter("tendangnhap");
            String matkhau = req.getParameter("matkhau");
            String matkhauRepeat = req.getParameter("matkhaurepeat");

            Integer vaitro=1;
            
            
          //  ThanhVienModel thanhvien = new ThanhVienModel(0, tendangnhap, matkhau,vaitro );
            
            
            Connection conn = MyUtils.getStoredConnection(req);
            //try {
            //    boolean result = ThanhVienModel.InsertNewThanhVien(conn, thanhvien);
            //} catch (SQLException ex) {
             //   Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            //    throw new IOException(ex.getMessage());
            //}
        }
            req.getRequestDispatcher("/admin/thanhvien.jsp").forward(req, resp); ;
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("txtTitle", "Thành viên"); 
        
        Connection conn = MyUtils.getStoredConnection(req);
        List<ThanhVienModel> listAllThanhVien= ThanhVienModel.getAllThanhVien(conn);
        System.out.println("NÔNNONOO");
        
        req.setAttribute("listAllThanhVien", listAllThanhVien);
        
 
        req.getRequestDispatcher("/admin/thanhvien.jsp").forward(req, resp); ;
    }

    
}
