package Controller;

import Model.CTDonHangModel;
import Model.DonHangModel;
import Model.MessagesModel;
import Model.ThanhVienModel;
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
@WebServlet(name = "XemDonHangServlet", urlPatterns = {"/xemdonhang"})
public class XemDonHangServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        if (MyUtils.getLoginedThanhVien(session) == null) // chưa đăng nhập
        {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Oops!", "Bạn chưa đăng nhập!", MessagesModel.ATT_TYPE_ERROR));
            req.getRequestDispatcher("dangnhap.jsp").forward(req, resp);
            
        } else {
            
            int maDonHang = Integer.parseInt(req.getParameter("id"));

            Connection conn = MyUtils.getStoredConnection(req);
            List<CTDonHangModel> listCTDonHang = CTDonHangModel.getAllCTDonHangByMaDonHang(conn, maDonHang);

            DonHangModel donHangModel = new DonHangModel();
            ThanhVienModel thanhVienModel = new ThanhVienModel();

            try {
                donHangModel = DonHangModel.FindByMaDonHang(conn, maDonHang);

            } catch (Exception ex) {

            }

            try {
                thanhVienModel = ThanhVienModel.FindByMaThanhVien(conn, donHangModel.getMaThanhVien());

            } catch (Exception ex) {

            }

            req.setAttribute("donHang", donHangModel);
            req.setAttribute("listCTDonHang", listCTDonHang);
            req.setAttribute("thanhvien", thanhVienModel);

            req.getRequestDispatcher("ctdonhang.jsp").forward(req, resp);
        }
    }

}
