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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            String tuKhoa = req.getParameter("tukhoa");
            Integer currentPage;
            Integer maTheLoai;
            int numOfPage;
            String temp = req.getParameter("page");
            
            if (req.getParameter("page") != null) {
                currentPage = Integer.parseInt(temp);
            } else {
                currentPage = 1;
            }
            temp = req.getParameter("matheloai");
            if (req.getParameter("matheloai") != null) {
                maTheLoai = Integer.parseInt(temp);
            } else {
                maTheLoai = 0;
            }
            req.setAttribute("txtTitle", "Tìm kiếm sách");
            System.out.println(currentPage);
            Connection conn = MyUtils.getStoredConnection(req);

            int numOfBookFound = SachModel.CountAllByTuKhoa(conn, tuKhoa, maTheLoai);

            if (numOfBookFound % MyUtils.soSachTrongMotTrang == 0) {
                numOfPage = numOfBookFound / MyUtils.soSachTrongMotTrang;
            } else {
                numOfPage = numOfBookFound / MyUtils.soSachTrongMotTrang + 1;
            }

            List<SachModel> listSach = SachModel.FindAllByTuKhoa(conn, tuKhoa, maTheLoai, currentPage, MyUtils.soSachTrongMotTrang);

            Date date = new Date();
            long time = date.getTime();
            Timestamp ts = new Timestamp(time);
            String currentTs = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(ts);
            
            req.setAttribute("curentTimeStamp", currentTs);
            req.setAttribute("numofpage", numOfPage);
            req.setAttribute("listSach", listSach);
            req.setAttribute("tukhoa", tuKhoa);
            req.setAttribute("matheloai", maTheLoai);
            req.getRequestDispatcher("search.jsp").forward(req, resp);

        } catch (SQLException ex) {
            Logger.getLogger(SearchSachServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
