/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MessagesModel;
import Model.SachModel;
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
@WebServlet(name = "CategoriesServlet", urlPatterns = {"/categories" })
public class CategoriesServlet extends HttpServlet {

    
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {                      
        
        Connection conn = MyUtils.getStoredConnection(request);
            
        int maTheLoai = Integer.parseInt((String) request.getParameter("id"));  
        List<SachModel> listSach = SachModel.getAllSachByMaTheLoai(conn,maTheLoai);
        

        request.setAttribute("listSach", listSach);
        
        //System.out.println(listAllPhiShip.get(0).getTenTinh());
        request.getRequestDispatcher("/categories.jsp").forward(request, response);
         
    }
    
 
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
              System.out.print("abcc");

    }
}
