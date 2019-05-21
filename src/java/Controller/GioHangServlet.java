/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MessagesModel;
import Model.PhiShipModel;
import Utility.MyUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
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
@WebServlet(name = "GioHangServlet", urlPatterns = {"/giohang"})
public class GioHangServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        
        Connection conn = MyUtils.getStoredConnection(req);
        List<PhiShipModel> listPhiShip = PhiShipModel.getAllPhiShip(conn);
        req.setAttribute("listPhiShip", listPhiShip);
        //System.out.print(listPhiShip.get(0).getMaPhiShip());
        
        if (MyUtils.getLoginedThanhVien(session) == null) // chưa đăng nhập
        {

            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Oops!", "Đăng nhập để tiếp tục mua hàng!", MessagesModel.ATT_TYPE_ERROR));
            req.getRequestDispatcher("giohang.jsp").forward(req, resp);
        } else {

            req.getRequestDispatcher("checkout.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("txtTitle", "Giỏ hàng");

        req.getRequestDispatcher("giohang.jsp").forward(req, resp);

    }

}
