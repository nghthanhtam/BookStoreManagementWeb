/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.SachModel;
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

/**
 *
 * @author Dell
 */
@WebServlet(name = "SearchSachByTacGiaServlet", urlPatterns = {"/tacgia"})
public class SearchSachByTacGiaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            Integer currentPage;
            int numOfPage = 0;
            String temp = req.getParameter("page");
            if (req.getParameter("page") != null) {
                currentPage = Integer.parseInt(temp);
            } else {
                currentPage = 1;
            }

            Connection conn = MyUtils.getStoredConnection(req);
            String tenTacGia = req.getParameter("tentacgia");

            List<SachModel> listSachTheoTacGia = SachModel.GetAllSachByTacGiaPerPage(conn, tenTacGia, currentPage, MyUtils.soSachTrongMotTrang);

            int numOfBookFound = SachModel.CountAllByTacGia(conn, tenTacGia);

            if (numOfBookFound % MyUtils.soSachTrongMotTrang == 0) {
                numOfPage = numOfBookFound / MyUtils.soSachTrongMotTrang;
            } else {
                numOfPage = numOfBookFound / MyUtils.soSachTrongMotTrang + 1;
            }

            Date date = new Date();
            long time = date.getTime();
            Timestamp ts = new Timestamp(time);
            String currentTs = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(ts);

            req.setAttribute("curentTimeStamp", currentTs);

            req.setAttribute("txtTitle", "Tìm kiếm sách theo tác giả" + tenTacGia);

            req.setAttribute("numofpage", numOfPage);
            req.setAttribute("tentacgia", tenTacGia);
            req.setAttribute("listSachTheoTacGia", listSachTheoTacGia);
            req.getRequestDispatcher("search-tacgia.jsp").forward(req, resp);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
}
