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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
@WebServlet(name = "CategoriesServlet", urlPatterns = {"/categories"})
public class CategoriesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        

        try {
        
        Connection conn = MyUtils.getStoredConnection(request);

       Integer  maTheLoai= Integer.parseInt((String) request.getParameter("id"));

        Integer currentPage;
        int numOfPage = 0;
        String temp = request.getParameter("page");
        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(temp);
        } else {
            currentPage = 1;
        }

      List<SachModel>  listSach = SachModel.getAllSachByTheLoaiPerPage(conn, maTheLoai ,currentPage, MyUtils.soSachTrongMotTrang);

        int numOfBookFound = SachModel.CountAllByTheLoai(conn, maTheLoai);
       

        if (numOfBookFound % MyUtils.soSachTrongMotTrang == 0) {
            numOfPage = numOfBookFound / MyUtils.soSachTrongMotTrang;
        } else {
            numOfPage = numOfBookFound / MyUtils.soSachTrongMotTrang + 1;
        }

        
        
        
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        String currentTs = new SimpleDateFormat("yyyy-MM-dd").format(ts);

        request.setAttribute("listSach", listSach);
        request.setAttribute("curentTimeStamp", currentTs);
        request.setAttribute("numofpage", numOfPage);
        request.setAttribute("listSach", listSach);
        request.setAttribute("matheloai", maTheLoai);
        request.getRequestDispatcher("search-theloai.jsp").forward(request, response);

        
        
         } catch (SQLException ex) {
           ex.printStackTrace();
        }
        
        

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.print("abcc");

    }
}
