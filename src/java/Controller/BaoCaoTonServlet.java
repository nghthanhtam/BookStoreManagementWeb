/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.BaoCaoDoanhThuNgayModel;
import Model.MessagesModel;
import Model.SachModel;
import Utility.MyUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MITICC06
 */
@WebServlet(name = "BaoCaoTonServlet", urlPatterns = {"/admin/baocao/ton"})
public class BaoCaoTonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = MyUtils.getStoredConnection(req);
        boolean isFailed = false;
        String noiDungThongBao = "";

        String button = req.getParameter("submit");
        HttpSession session = ((HttpServletRequest) req).getSession();

        try {
            List<SachModel> list = SachModel.getAllSach(conn);
            String tableHTML = "";
            for (int i = 0; i < list.size(); i++) {
                SachModel sach = list.get(i);
                tableHTML += "<tr>\n"
                        + "     <th scope=\"row\">" + (i + 1) + "</th>\n"
                        + "     <td>#" + sach.getMaSach() + "</td>\n"
                        + "     <td>" + sach.getTenSach() + "</td>\n"
                        + "     <td>" + sach.getSoLuongTon() + "</td>\n" 
                        + "</tr> ";
            }

            req.setAttribute("tableHTML", tableHTML);

            isFailed = false;
        } catch (Exception ex) {
            noiDungThongBao = ex.getMessage();
            isFailed = true;
        }

        if (isFailed) {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", noiDungThongBao, MessagesModel.ATT_TYPE_ERROR));
        }

        req.getRequestDispatcher("/admin/baocaoton.jsp").forward(req, resp);

    }
}
