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
 * @author MITICC06
 */
@WebServlet(name = "HomeServlet", urlPatterns = {""})
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("txtTitle", "Trang chủ - Book Store");
             
        Connection conn = MyUtils.getStoredConnection(req);
            
        List<SachModel> listSachMoiNhat = SachModel.getListSachMoiNhatTop7(conn);
        SachModel objectOne = listSachMoiNhat.get(0);
        listSachMoiNhat.remove(0);
        req.setAttribute("listSachMoiNhat", listSachMoiNhat);
        req.setAttribute("sachMoiNhat", objectOne);
        List<SachModel> listSachGiamGia = SachModel.getListSachGiamGiaTop7(conn);
//        for(int i=0;i<listSachGiamGia.size();i++){
//            System.out.println(listSachGiamGia.get(i).getTenSach());
//        }
        SachModel objectGiamGiaNhat = listSachGiamGia.get(0);
        listSachGiamGia.remove(0);
        req.setAttribute("sachGiamGiaNhat", objectGiamGiaNhat);
        req.setAttribute("listSachGiamGia", listSachGiamGia);


        
        req.getRequestDispatcher("home.jsp").forward(req, resp);
        //req.getRequestDispatcher("htmltest.html").forward(req, resp);
    }

}
