package Controller;

import Model.CTDonHangModel;
import Model.CTDonHangModelWithTenSachAnhDaiDien;
import Model.DonHangModel;
import Model.MessagesModel;
import Model.PhiShipModel;
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

            int maDonHang = 0;
            try{
                maDonHang = Integer.parseInt(req.getParameter("id"));
            } catch (Exception ex)
            {
                
            } 
            
            Connection conn = MyUtils.getStoredConnection(req);

            DonHangModel donHang = null;
            
            ThanhVienModel thanhVien = MyUtils.getLoginedThanhVien(session);
            int maThanhVien = MyUtils.getLoginedThanhVien(session).getMaThanhVien();

            List<CTDonHangModelWithTenSachAnhDaiDien> listCTDonHang = null;
             
            boolean isOk = true;
            String noiDungThongBao = "";
            try {
                donHang = DonHangModel.FindByMaDonHang(conn, maDonHang);
                if (donHang == null) {
                    throw new Exception("Không tìm thấy đơn hàng!");
                }

                if (donHang.getMaThanhVien() != maThanhVien) {
                    throw new Exception("Bạn không có quyền xem đơn hàng này!");
                }

                 
                listCTDonHang = CTDonHangModelWithTenSachAnhDaiDien.FindAllByMaDonHang(conn, maDonHang);

                if (listCTDonHang == null) {
                    throw new Exception("Không tìm thấy chi tiết đơn hàng!");
                }
                
                

            } catch (Exception ex) {
                isOk = false;
                noiDungThongBao = ex.getMessage();
            }

            if (isOk == false) {
                req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", noiDungThongBao, MessagesModel.ATT_TYPE_ERROR));

                List<DonHangModel> listDonHang = DonHangModel.getAllDonHangByMaThanhVien(conn, maThanhVien);
                req.setAttribute("listDonHang", listDonHang);

                req.getRequestDispatcher("list-donhang.jsp").forward(req, resp);
                return;
            } else {
                req.setAttribute("donHang", donHang);

                req.setAttribute("listCTDonHang", listCTDonHang);

                req.setAttribute("thanhVien", thanhVien);
 
                
                
                req.getRequestDispatcher("ctdonhang.jsp").forward(req, resp);
            }

        }
    }

}
