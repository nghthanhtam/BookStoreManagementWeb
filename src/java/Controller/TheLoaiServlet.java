/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MessagesModel;
import Model.TheLoaiModel;
import Utility.MyUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TamTorres
 */
@WebServlet(name = "TheLoaiServlet", urlPatterns = {"/admin/theloai" })
public class TheLoaiServlet extends HttpServlet {

    
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
               
        
        request.setAttribute("txtTitle", "Thể Loại Sách"); 
        
        Connection conn = MyUtils.getStoredConnection(request);
        List<TheLoaiModel> listAllTheLoai = TheLoaiModel.getAllTheLoai(conn);
       
        request.setAttribute("listAllTheLoai", listAllTheLoai);
        //System.out.println(listAllPhiShip.get(0).getTenTinh());
        request.getRequestDispatcher("/admin/theloai.jsp").forward(request, response);
         
    }
    
 
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  

        boolean isFailedRequest = false; // request thất bại
        String noiDungThongBao = "";
        
        String submitValue = req.getParameter("submit");
        if (submitValue !=null && submitValue.equals("them"))
        {
            
            String tenTheLoai = (String) req.getParameter("tentheloai");                             
         

            Connection conn = MyUtils.getStoredConnection(req);
            try {
                    boolean isOk = TheLoaiModel.InsertNewTheLoai(conn, new TheLoaiModel(0, tenTheLoai));
                    if (isOk)
                    {
                        isFailedRequest = false;
                        noiDungThongBao = "Đã thêm phí ship!";
                   }
                    else
                        isFailedRequest = true;
                         
            } catch (SQLException ex) { 
                isFailedRequest=true; 
                Logger.getLogger(TheLoaiServlet.class.getName()).log(Level.SEVERE, null, ex);
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
              
        req.setAttribute("txtTitle", "Thể Loại Sách"); 
        
        Connection conn = MyUtils.getStoredConnection(req);
        List<TheLoaiModel> listAllTheLoai = TheLoaiModel.getAllTheLoai(conn);
       
        req.setAttribute("listAllTheLoai", listAllTheLoai);
        req.getRequestDispatcher("/admin/theloai.jsp").forward(req, resp);
        
       
    }
}
