/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filter;

import Model.PhanQuyenModel;
import Model.ThanhVienModel;
import Utility.MyUtils;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MITICC06
 */
public class BaoCaoFilter implements Filter {
    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        Connection conn = MyUtils.getStoredConnection(req);
        ThanhVienModel thanhVien = MyUtils.getLoginedThanhVien(session);

        PhanQuyenModel phanQuyen = null;

        try {
            phanQuyen = PhanQuyenModel.FindByMaPhanQuyen(conn, thanhVien.getMaPhanQuyen());
        } catch (SQLException ex) {
 
        }

        if (phanQuyen != null && phanQuyen.getQlBaoCao()!= 0) {
            chain.doFilter(request, response);// vượt qua filter
        } else {
            request.setAttribute("tenChucNang", "Báo cáo");

            request.getRequestDispatcher("/admin/chantruycap.jsp").forward(request, response);
        }

    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {

    }
    
}
