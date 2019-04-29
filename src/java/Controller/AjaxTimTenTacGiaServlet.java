package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.AjaxModel;
import Model.SachModel;
import Utility.MyUtils;
import com.google.gson.Gson;
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
@WebServlet(urlPatterns = {"/admin/sach/add/ajaxtimtacgiasach"})
public class AjaxTimTenTacGiaServlet extends HttpServlet {

    public AjaxTimTenTacGiaServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson(); 
        String tenTacGia = request.getParameter("term");
        PrintWriter out = response.getWriter(); 
        Connection conn = MyUtils.getStoredConnection(request);
        try {
            List<AjaxModel> listAjaxTenTacGiaModel  = SachModel.FindTenTacGia(conn, tenTacGia);
            for (AjaxModel x : listAjaxTenTacGiaModel) {
            System.out.println(x.getLabel());
            }
            out.print(gson.toJson(listAjaxTenTacGiaModel));
        } catch (SQLException ex) {
            Logger.getLogger(AjaxTimTenTacGiaServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
        
        out.flush();
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}


