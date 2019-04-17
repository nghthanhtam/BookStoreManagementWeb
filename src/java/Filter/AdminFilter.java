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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MITICC06
 */
public class AdminFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        if (JDBCFilter.needJDBC((HttpServletRequest) request) == false)
        {
            chain.doFilter(request, response);// vượt qua filter
            return;
        }
        
        String uri = ((HttpServletRequest) request).getRequestURI();
        if (uri.equals("/admin/") || uri.equals("/admin"))
        {
            chain.doFilter(request, response);// vượt qua filter
            return;
        }
 
        
        HttpSession session = ((HttpServletRequest) request).getSession();
        
        java.sql.Connection conn = MyUtils.getStoredConnection(request);
        
        ThanhVienModel thanhVien = MyUtils.getLoginedThanhVien(session);
        if (thanhVien != null) // đã đăng nhập
        {
            /*
            if (MyUtils.getStoredPhanQuyen(request) == null)
            {
                try {
                        PhanQuyenModel phanQuyenModel = PhanQuyenModel.FindByMaPhanQuyen(conn, thanhVien.getMaPhanQuyen());
                        MyUtils.storePhanQuyen(session, phanQuyenModel);
                } catch (SQLException ex) {
                    Logger.getLogger(AdminFilter.class.getName()).log(Level.SEVERE, null, ex);
                }   
            }
             */
            chain.doFilter(request, response);// vượt qua filter
        }
        else
        {
            ((HttpServletResponse) response).sendRedirect("/admin");
        }

    }

    @Override
    public void destroy() {
    }
    
}