package Controller;

import Model.MessagesModel;
import Model.NhaCungCapModel;
import Model.PhieuChiModel;
import Model.PhieuChiModelWithTenNhaCungCap;
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

/**
 *
 * @author MITICC06
 */
@WebServlet(name = "DeletePhieuChiServlet", urlPatterns = {"/admin/phieuchi/delete"})
public class DeletePhieuChiServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(req);
        boolean result = false;

        try {
            int maPhieuChi = Integer.parseInt((String) req.getParameter("id"));
            result = PhieuChiModel.DeleteByMaPhieuChi(conn, maPhieuChi);

        } catch (Exception ex) {
            result = false;

        }

        if (result == true) {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!", "Đã xóa thành công!", MessagesModel.ATT_TYPE_SUCCESS));

        } else {

            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", "Yêu cầu của bạn không được thực hiện!", MessagesModel.ATT_TYPE_ERROR));

        }

        /* Hiển thị view */
        req.setAttribute("txtTitle", "Phiếu Chi");

        List<PhieuChiModelWithTenNhaCungCap> listAllPhieuChiWithTenNhaCungCap = PhieuChiModelWithTenNhaCungCap.getAllPhieuChiWithTenNhaCungCap(conn);       
        List<NhaCungCapModel> listAllNhaCungCap = NhaCungCapModel.getAllNhaCungCap(conn);
        
        req.setAttribute("listAllNhaCungCap", listAllNhaCungCap);
        req.setAttribute("listAllPhieuChiWithTenNhaCungCap", listAllPhieuChiWithTenNhaCungCap);
        req.getRequestDispatcher("/admin/phieuchi.jsp").forward(req, resp);

    }

}
