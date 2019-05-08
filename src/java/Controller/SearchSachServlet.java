/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AjaxSachModel;
import Model.NhaCungCapModel;
import Model.SachModel;
import Model.TheLoaiModel;
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
import org.apache.jasper.tagplugins.jstl.ForEach;

/**
 *
 * @author Dell
 */
@WebServlet(name = "SearchSachServlet", urlPatterns = {"/search"})
public class SearchSachServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("search.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String tuKhoa = req.getParameter("search");
            req.setAttribute("txtTitle", "Tìm kiếm sách");

            Connection conn = MyUtils.getStoredConnection(req);

            List<SachModel> listSach = SachModel.FindAllByTuKhoa(conn, tuKhoa);
            
            
            req.setAttribute("listSach", listSach);
            req.getRequestDispatcher("search.jsp").forward(req, resp);

        } catch (SQLException ex) {
            Logger.getLogger(SearchSachServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
