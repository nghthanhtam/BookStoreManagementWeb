/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MessagesModel;
import Model.NhaCungCapModel;
import Model.PhanQuyenModel;
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
@WebServlet(name = "NhaCungCapServlet", urlPatterns = {"/admin/nhacungcap"})
public class NhaCungCapServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* Set page */
        System.out.print("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        //req.setAttribute("txtTitle", "Nhà cung cấp");
        boolean isFailedRequest = false; // request thất bại
        String noiDungThongBao = "";
        //String submitValue = req.getParameter("submit");
        String button = req.getParameter("submit");
        
        if (button !=null && button.equals("them"))
        {
            
            System.out.println("1");    
            String tenNhaCungCap = (String) req.getParameter("tennhacungcap");  
            System.out.println("2");              
            String diaChi = (String) req.getParameter("diachi");
            System.out.println("3");    
            String soDienThoai = (String) req.getParameter("sodienthoai"); 
            System.out.println("4");               
            Double soTienNo =200.0;
            
             
            System.out.println("5");
            
            Connection conn = MyUtils.getStoredConnection(req);
            try {
                    boolean isOk = NhaCungCapModel.InsertNewNhaCungCap(conn, new NhaCungCapModel(0, tenNhaCungCap, diaChi, soDienThoai, soTienNo));
                    if (isOk)
                    {
                        isFailedRequest = false;
                        noiDungThongBao = "Đã thêm nhà cung cấp mới!";
                    }
                    else
                        isFailedRequest = true;
                         
            } catch (SQLException ex) { 
                isFailedRequest=true; 
                Logger.getLogger(NhaCungCapServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        }
        else
            isFailedRequest = true;
        
        
        if (isFailedRequest) // nếu có lỗi thì hiện thông báo
        { 
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!","Yêu cầu của bạn không được xử lý!",MessagesModel.ATT_TYPE_ERROR));         
        }
        else
        {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!",noiDungThongBao,MessagesModel.ATT_TYPE_SUCCESS));         
        }
        Connection conn = MyUtils.getStoredConnection(req);
        List<NhaCungCapModel> listAllNhaCungCap= NhaCungCapModel.getAllNhaCungCap(conn);
        
        req.setAttribute("listAllNhaCungCap", listAllNhaCungCap);
        req.getRequestDispatcher("/admin/nhacungcap.jsp").forward(req, resp);
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          System.out.print("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        
        req.setAttribute("txtTitle", "Nhà cung cấp"); 
        
        Connection conn = MyUtils.getStoredConnection(req);
        List<NhaCungCapModel> listAllNhaCungCap= NhaCungCapModel.getAllNhaCungCap(conn);
        
        
        req.setAttribute("listAllNhaCungCap", listAllNhaCungCap);
        
        req.getRequestDispatcher("/admin/nhacungcap.jsp").forward(req, resp);
 }


    
  
}
