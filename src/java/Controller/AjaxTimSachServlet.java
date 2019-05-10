/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AjaxModel;
import Model.AjaxSachModel;
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
import Model.SachModel;
/**
 *
 * @author Dell
 */
@WebServlet(name = "AjaxTimSachServlet", urlPatterns = {"/ajax/sach"})
public class AjaxTimSachServlet extends HttpServlet {

        private static final long serialVersionUID = 1L;

    public AjaxTimSachServlet() {
        super();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String tuKhoa = req.getParameter("term");
        //tuKhoa="ach thật vui";
        PrintWriter out = resp.getWriter();
        
        Connection conn = MyUtils.getStoredConnection(req);
        try{
            List<AjaxModel> listSach = SachModel.FindAllByTuKhoaAjax(conn, tuKhoa);
            out.print(gson.toJson(listSach));
        } catch(SQLException ex){
            Logger.getLogger(AjaxTimSachServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.flush();
        out.close();
    }


}
