/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AjaxModel;
import Model.NhaCungCapModel;
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
 * @author MITICC06
 */
@WebServlet(name = "AjaxSachServlet", urlPatterns = {"/admin/phieunhap/ajax/sach"})
public class AjaxSachServlet extends HttpServlet {
 
    public AjaxSachServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson();
        String tenSach = request.getParameter("term");

        PrintWriter out = response.getWriter();

        Connection conn = MyUtils.getStoredConnection(request);
        try {
            List<AjaxModel> listAjaxSachModel = SachModel.FindAllByName(conn, tenSach);
            out.print(gson.toJson(listAjaxSachModel));
        } catch (SQLException ex) {
            
            Logger.getLogger(AjaxSachServlet.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        out.flush();
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
