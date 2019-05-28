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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author TamTorres
 */
@WebServlet(name = "GioHangServlet", urlPatterns = {"/giohang"})
public class GioHangServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

 
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("txtTitle", "Giỏ hàng");

//        String listCT = (String) req.getParameter("listctdonhang");
//        System.out.print(listCT);
//
//        JSONArray jsonListCT = null;
//
//        try {
//            jsonListCT = new JSONArray(listCT);
//
//        } catch (JSONException ex) {
//
//            ex.printStackTrace();
//        };
//
//        
//        if (jsonListCT.length() <= 0) {
//            System.out.print("abcccccccc");
//            req.getRequestDispatcher("giohang-empty.jsp").forward(req, resp);
//        } else {
            req.getRequestDispatcher("giohang.jsp").forward(req, resp);
        //}

        //req.setAttribute("listCT", listCT);
      
       

    }

}
