/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MessagesModel;
import Model.PhiShipModel;
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



@WebServlet(name = "EditPhiShipServlet", urlPatterns = {"/admin/phiship/edit"})
public class EditPhiShipServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("txtTitle", "Phí Ship");
        boolean isFailedRequest = false; // request thất bại
        String noiDungThongBao = "";
        
        Connection conn = MyUtils.getStoredConnection(req);
        
        String submitValue = req.getParameter("submit");
        if (submitValue !=null && submitValue.equals("capnhat"))
        {
            String tenTinh= (String) req.getParameter("tentinh");      
            int maPhiShip = Integer.parseInt(req.getParameter("maphiship"));
            double phiShip = Double.parseDouble(req.getParameter("phiship"));
             
            PhiShipModel phiShipModel = new PhiShipModel(maPhiShip, tenTinh, phiShip);
                    
            try {                   
                    boolean isOk = PhiShipModel.UpdatePhiShip(conn, phiShipModel);
                    if (isOk)
                    {
                        isFailedRequest = false;
                        noiDungThongBao = "Đã cập nhật thành công!";
                    }
                    else
                        isFailedRequest = true;
                    
            } catch (SQLException ex) { 
                isFailedRequest=true; 
                Logger.getLogger(PhiShipServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        
        List<PhiShipModel> listAllPhiShip = PhiShipModel.getAllPhiShip(conn);
        req.setAttribute("listAllPhiShip", listAllPhiShip);
        req.getRequestDispatcher("/admin/phiship.jsp").forward(req, resp);
        


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        Connection conn = MyUtils.getStoredConnection(req);
        PhiShipModel phiShipModel = null;
        boolean result = false;
        
        try {           
            int maPhiShip = Integer.parseInt((String) req.getParameter("id"));          
            phiShipModel = PhiShipModel.FindByMaPhiShip(conn, maPhiShip);
            
            if (phiShipModel != null)
                result = true;
        } catch (Exception ex) {
            result = false;
        }
        
        if (result == true)
        {
            req.setAttribute("txtTitle", "Sửa phí ship");
            req.setAttribute("phiShipModel", phiShipModel);
            req.getRequestDispatcher("/admin/phiship-edit.jsp").forward(req, resp);

        } else { // hiển thị view thông báo thất bại
            
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!","Yêu cầu của bạn không được thực hiện!",MessagesModel.ATT_TYPE_ERROR));         
            
            /* Hiển thị view */
            req.setAttribute("txtTitle", "Phí Ship"); 

            List<PhiShipModel> listAllPhiShip= PhiShipModel.getAllPhiShip(conn);
            req.setAttribute("listAllPhiShip", listAllPhiShip);
            req.getRequestDispatcher("/admin/phiship.jsp").forward(req, resp);
        }
        
    }
 
}