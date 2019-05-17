package Controller;

import Model.AjaxModel;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.NhaCungCapModel;
import Utility.MyUtils;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
 
@WebServlet(name = "AjaxNhaCungCapServlet", urlPatterns = {
    "/admin/nhacungcap/ajax/nhacungcap",
    "/admin/phieunhap/ajax/nhacungcap"})
public class AjaxNhaCungCapServlet extends HttpServlet {
 
    public AjaxNhaCungCapServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson();
        String tenNhaCungCap = request.getParameter("term");

        PrintWriter out = response.getWriter();

        Connection conn = MyUtils.getStoredConnection(request);
        try {
            List<AjaxModel> listAjaxNhaCungCapModel = NhaCungCapModel.FindAllByName(conn, tenNhaCungCap);
            out.print(gson.toJson(listAjaxNhaCungCapModel));
        } catch (SQLException ex) {
            Logger.getLogger(AjaxNhaCungCapServlet.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        out.flush();
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
