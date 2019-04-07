package Controller;

import Model.MessagesModel;
import Model.PhieuChiModel;
import Utility.MyUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
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
@WebServlet(name = "EditPhieuChiServlet", urlPatterns = {"/admin/phieuchi/edit"})
public class EditPhieuChiServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("txtTitle", "Phiếu Chi");
        boolean isFailedRequest = false; // request thất bại
        String noiDungThongBao = "";

        Connection conn = MyUtils.getStoredConnection(req);

        String submitValue = req.getParameter("submit");
        if (submitValue != null && submitValue.equals("capnhat")) {
            
            int maPhieuChi = Integer.parseInt(req.getParameter("maphieuchi"));
            int maNhaCungCap = Integer.parseInt(req.getParameter("manhacungcap"));
            int maThanhVien = Integer.parseInt(req.getParameter("mathanhvien"));
            Double tongTien = Double.parseDouble(req.getParameter("tongtien"));
            String ghiChu = (String) req.getParameter("ghichu");

            PhieuChiModel phieuChiModel = new PhieuChiModel(maPhieuChi, maNhaCungCap, maThanhVien, tongTien, null, ghiChu);

            try {
                boolean isOk = PhieuChiModel.UpdatePhieuChi(conn, phieuChiModel);
                if (isOk) {
                    isFailedRequest = false;
                    noiDungThongBao = "Đã cập nhật thành công!";
                } else {
                    isFailedRequest = true;
                }

            } catch (SQLException ex) {
                isFailedRequest = true;
                Logger.getLogger(PhieuChiServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            isFailedRequest = true;
        }

        if (isFailedRequest) // nếu có lỗi thì hiện thông báo
        {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", "Yêu cầu của bạn không được xử lý!", MessagesModel.ATT_TYPE_ERROR));
        } else {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!", noiDungThongBao, MessagesModel.ATT_TYPE_SUCCESS));
        }

        req.setAttribute("txtTitle", "Phiếu Chi");

        List<PhieuChiModel> listAllPhieuChi = PhieuChiModel.getAllPhieuChi(conn);
        req.setAttribute("listAllPhieuChi", listAllPhieuChi);
        req.getRequestDispatcher("/admin/phieuchi.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = MyUtils.getStoredConnection(req);
        PhieuChiModel phieuChiModel = null;
        boolean result = false;

        try {
            int maPhieuChi = Integer.parseInt(req.getParameter("id"));
            phieuChiModel = PhieuChiModel.FindByMaPhieuChi(conn, maPhieuChi);

            if (phieuChiModel != null) {
                result = true;
            }
        } catch (Exception ex) {
            result = false;
        }

        if (result == true) {
            req.setAttribute("txtTitle", "Sửa phiếu chi");
            req.setAttribute("phieuChiModel", phieuChiModel);
            req.getRequestDispatcher("/admin/phieuchi-edit.jsp").forward(req, resp);

        } else { // hiển thị view thông báo thất bại

            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", "Yêu cầu của bạn không được thực hiện!", MessagesModel.ATT_TYPE_ERROR));
            
            /* Hiển thị view */
            req.setAttribute("txtTitle", "Phiếu Chi");

            List<PhieuChiModel> listAllPhieuChi = PhieuChiModel.getAllPhieuChi(conn);
            req.setAttribute("listAllPhieuChi", listAllPhieuChi);
            req.getRequestDispatcher("/admin/phieuchi.jsp").forward(req, resp);
        }

    }

}
