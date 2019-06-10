/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AjaxModel;
import Model.SachModel;
import Model.ThanhVienModel;
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
@WebServlet(name = "AjaxTimThanhVienServlet", urlPatterns = {"/admin/thanhvien/ajax/thanhvien"})
public class AjaxTimThanhVienServlet extends HttpServlet {
 
        private static final long serialVersionUID = 1L;

    public AjaxTimThanhVienServlet() {
        super();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String tuKhoa = req.getParameter("term");
        
        PrintWriter out = resp.getWriter();
        
        Connection conn = MyUtils.getStoredConnection(req);
        try{
            List<AjaxModel> list = ThanhVienModel.FindAllByTuKhoaAjax(conn, tuKhoa);
            out.print(gson.toJson(list));
        } catch(SQLException ex){



        }
        out.flush();
        out.close();
    }

}
