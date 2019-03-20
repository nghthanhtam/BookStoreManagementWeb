/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.PhiShipModel;
import Utility.MyUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
@WebServlet(name = "PhiShipServlet", urlPatterns = {"/admin/phiship" })
public class PhiShipServlet extends HttpServlet {

    
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
        
      
            request.getRequestDispatcher("/admin/phiship.jsp").forward(request, response);
       
             
    }
    
     

    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
        String button = req.getParameter("submit");
        if(button != null && button.equals("them"))
        {
            String tenTinh = req.getParameter("tentinh");
        
            Double phiShip = Double.parseDouble(req.getParameter("phiship"));
            
            req.setAttribute("phiShip", phiShip);
            req.setAttribute("txTenTinh", tenTinh);
        }
        
        PhiShipModel phiship = new PhiShipModel(1,30000, "Da Nang");
      
            
         Connection conn = MyUtils.getStoredConnection(req);
            try {
                boolean result = PhiShipModel.InsertNewPhiShip(conn, phiship);
            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                throw new IOException(ex.getMessage());
            }
            req.getRequestDispatcher("/admin/phiship.jsp").forward(req, resp); ;
    }
}
