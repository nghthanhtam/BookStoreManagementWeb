package Controller;

import Model.SachModel;
import Utility.MyUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MITICC06
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (req.getParameter("masach") == null) {
            resp.sendRedirect("/");
        } else {
            int masach = 0;
            try {
                masach = Integer.parseInt((String) req.getParameter("masach"));
            } catch (Exception ex) {
                //throw new IOException("Mã sách không hợp lệ!");
            }

            Connection conn = MyUtils.getStoredConnection(req);

            try {
                SachModel sach = SachModel.FindByMaSach(conn, masach);
                if (sach != null && sach.getTrangThai() != SachModel.TRANGTHAI_XOA) { // tìm thấy theo mã sách
                    List<SachModel> listSach = SachModel.getSachByMaTheLoaiTop4(conn, sach.getMaTheLoai());

                    Date date = new Date();
                    long time = date.getTime();
                    Timestamp ts = new Timestamp(time);
                    String currentTs = new SimpleDateFormat("yyyy-MM-dd").format(ts);

                    req.setAttribute("curentTimeStamp", currentTs);

                    req.setAttribute("listSach", listSach);
                    req.setAttribute("sach", sach);
                    req.getRequestDispatcher("product-page.jsp").forward(req, resp);

                } else {

                    resp.sendRedirect("/");
                }

            } catch (SQLException ex) {
                Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
