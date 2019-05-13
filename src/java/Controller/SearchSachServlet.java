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
            
        Integer numberOfBookInOnePage =9;
            
        try {
            String tuKhoa = req.getParameter("tukhoa");
            Integer currentPage;
            Integer maTheLoai;
            int numOfPage;
            String temp =req.getParameter("page");
            if(req.getParameter("page")!=null){
                currentPage = Integer.parseInt(temp);
            }
            else
                currentPage=1;
            temp=req.getParameter("matheloai");
            if(req.getParameter("matheloai")!=null){
                maTheLoai = Integer.parseInt(temp);
            }
            else
                maTheLoai = 0;
            req.setAttribute("txtTitle", "Tìm kiếm sách");
            System.out.println(currentPage);
            Connection conn = MyUtils.getStoredConnection(req);
            
            int numOfBookFound = SachModel.CountAllByTuKhoa(conn, tuKhoa, maTheLoai);
            
            if(numOfBookFound%numberOfBookInOnePage==0)
                numOfPage = numOfBookFound/numberOfBookInOnePage;
            else
                numOfPage = numOfBookFound/numberOfBookInOnePage+1;
            
            List<SachModel> listSach = SachModel.FindAllByTuKhoa(conn, tuKhoa, maTheLoai,currentPage,numberOfBookInOnePage);
            
            for(int i=0;i<listSach.size();i++){
                System.out.println(listSach.get(i).getTenSach());
            }
                System.out.println(numberOfBookInOnePage);//7
                System.out.println(numOfPage);//0
                System.out.println(listSach.size());//0
                System.out.println(numOfBookFound);//0
            
               
            
            req.setAttribute("numofpage", numOfPage);
            req.setAttribute("listSach", listSach);
            req.setAttribute("tukhoa", tuKhoa);
            req.setAttribute("matheloai", maTheLoai);
            req.setAttribute("page", currentPage);
            req.getRequestDispatcher("search.jsp").forward(req, resp);

        } catch (SQLException ex) {
            Logger.getLogger(SearchSachServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
