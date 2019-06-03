/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.SachModel;
import Utility.MyUtils;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TamTorres
 */
@WebServlet(name = "ProductListServlet", urlPatterns = {"/productlist"})
public class ProductListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   
        Connection conn = MyUtils.getStoredConnection(req);
         
        List<SachModel> listAllSach = SachModel.getAllSach(conn);
       
        req.setAttribute("listAllSach", listAllSach);

        req.getRequestDispatcher("demo.jsp").forward(req, resp);;
    }
}
