/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MessagesModel;
import Model.NhaCungCapModel;
import Model.PhanQuyenModel;
import Model.ThanhVienModel;
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
 * @author Admin
 */
@WebServlet(name = "DeleteThanhVienServlet", urlPatterns = {"/admin/thanhvien/delete"})
public class DeleteThanhVienServlet extends HttpServlet {

 
    
   @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(req);
            System.out.println("XA^XAXAXAXAXAX");
        boolean result = false;
        try {
            int maThanhVien = Integer.parseInt((String) req.getParameter("id"));
            System.out.println("XA^XAXAXAXAXAX");
            result = ThanhVienModel.DeleteThanhVienById(conn, maThanhVien);
            
        } catch (Exception ex) {
            result = false;
        }
        
        if (result == true)
        {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!","Đã xóa thành công!",MessagesModel.ATT_TYPE_SUCCESS));         
        } else {
            
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!","Yêu cầu của bạn không được thực hiện!",MessagesModel.ATT_TYPE_ERROR));         
 
        }
        
        /* Hiển thị view */
        req.setAttribute("txtTitle", "Thành viên"); 
        System.out.println("XA^xázd");
        List<PhanQuyenModel> listAllPhanQuyen= PhanQuyenModel.getAllPhanQuyen(conn);
        List<ThanhVienModel> listAllThanhVien= ThanhVienModel.getAllThanhVien(conn);
        
        req.setAttribute("listAllThanhVien", listAllThanhVien);
        req.setAttribute("listAllPhanQuyen", listAllPhanQuyen);
        req.getRequestDispatcher("/admin/thanhvien.jsp").forward(req, resp);

        

    }
  
}
