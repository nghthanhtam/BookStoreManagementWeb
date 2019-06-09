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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        List<SachModel> listFancySach = SachModel.getRichOrPoorSachTop4(conn, true);
        
        
        SachModel objectGiamGiaNhat = listSachGiamGia.get(0);
        listSachGiamGia.remove(0);

        List<SachModel> listTrashSach = SachModel.getRichOrPoorSachTop4(conn,false);
        List<SachModel> listSachSapHet = SachModel.getOutOfStockSachTop3(conn);    
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        String currentTs = new SimpleDateFormat("yyyy-MM-dd").format(ts);

        req.setAttribute("curentTimeStamp", currentTs);
        req.setAttribute("listFancySach", listFancySach);
        req.setAttribute("listTrashSach", listTrashSach);
        req.setAttribute("sachGiamGiaNhat", objectGiamGiaNhat);
        req.setAttribute("listSachGiamGia", listSachGiamGia);
        req.setAttribute("listOutOfStockSach", listSachSapHet);

        req.getRequestDispatcher("home.jsp").forward(req, resp); 
    }

}
