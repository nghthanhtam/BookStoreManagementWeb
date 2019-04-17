package Controller;

import Model.MessagesModel;
import Model.NhaCungCapModel;
import Model.PhieuChiModel;
import Model.PhieuChiModelWithTenNhaCungCap;
import Utility.MyUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
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
        boolean isFailed = false;
        boolean isFailedRequest = false;
        PhieuChiModel phieuChiModel = null;
        
        int maPhieuChi = Integer.parseInt(req.getParameter("id"));       
        try {
            phieuChiModel = PhieuChiModel.FindByMaPhieuChi(conn, maPhieuChi);

            if (phieuChiModel != null) {
                isFailed = false;
            } else {
                conn.rollback();
                System.out.println("abccc1");
                isFailed = true;
            }
        } catch (Exception ex) {
            System.out.println("exxxxx");
            ex.toString();
            isFailed = true;
        }
         
        
        try {         
            result = PhieuChiModel.DeleteByMaPhieuChi(conn, maPhieuChi);

        } catch (Exception ex) {
            result = false;           
        }
 
                
        //<editor-fold defaultstate="collapsed" desc="-- Cap nhat lai tien no --">                  
        try {
            NhaCungCapModel nhaCungCap = NhaCungCapModel.FindByMaNhaCungCap(conn, phieuChiModel.getMaNhaCungCap());
            nhaCungCap.setSoTienNo(nhaCungCap.getSoTienNo() + phieuChiModel.getTongTien());
            
            boolean isOk1 = NhaCungCapModel.UpdateSoTienNo(conn, nhaCungCap);
            if (isOk1) {
                isFailedRequest = false;
            } else {
                conn.rollback();
                isFailedRequest = true;
            }

        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (Exception ex1) {
                // This catch block will handle any errors that may have occurred
                // on the server that would cause the rollback to fail, such as
                // a closed connection.
                System.out.println("Rollback Failed");
            }
            
            isFailedRequest = true;
        }
        //</editor-fold>
        
        
        
        
        if (result == false) {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", "Yêu cầu của bạn không được thực hiện!", MessagesModel.ATT_TYPE_ERROR));
        } else {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!", "Đã xóa thành công!", MessagesModel.ATT_TYPE_SUCCESS));
        }
      
        /* Hiển thị view */           
        List<PhieuChiModelWithTenNhaCungCap> listAllPhieuChiWithTenNhaCungCap = PhieuChiModelWithTenNhaCungCap.getAllPhieuChiWithTenNhaCungCap(conn);              
        
        req.setAttribute("txtTitle", "Phiếu Chi");
        req.setAttribute("listAllPhieuChiWithTenNhaCungCap", listAllPhieuChiWithTenNhaCungCap);
        req.getRequestDispatcher("/admin/phieuchi.jsp").forward(req, resp);

    }

}
