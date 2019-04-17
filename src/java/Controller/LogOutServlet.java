/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Utility.MyUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ha Phuong
 */
@WebServlet(name = "LogOutServlet", urlPatterns = {"/logout"})
public class LogOutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        MyUtils.LogOutThanhVien(session);
        
        String urlReferer = req.getHeader("referer");
        if (urlReferer == null)
        {
            resp.sendRedirect("/");
        }
        else
        {
            resp.sendRedirect(urlReferer);
        }
        //resp.sendRedirect("/admin/");
        // req.getRequestDispatcher("/admin/admin-login.jsp").forward(req, resp);
    }

}
