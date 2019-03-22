package Controller;

import Model.MessagesModel;
import Model.PhiShipModel;
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
@WebServlet(name = "DeletePhanQuyenServlet", urlPatterns = {"/admin/phiship/delete"})
public class DeletePhiShipServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); 
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(req);
        boolean result=false;
     
        try {
            int maPhiShip = Integer.parseInt((String)req.getParameter("id"));
            System.out.println(maPhiShip);
            result = PhiShipModel.DeleteByMaPhiShip(conn, maPhiShip);

        } catch (Exception ex) {
            result = false;
            
        }

        if (result == true)
        {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!","Đã xóa thành công!",MessagesModel.ATT_TYPE_SUCCESS));         

        } else {

            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!","Yêu cầu của bạn không được thực hiện!",MessagesModel.ATT_TYPE_ERROR));         


        }

        /* Hiển thị view */
        req.setAttribute("txtTitle", "Phí Ship"); 

        List<PhiShipModel> listAllPhiShip= PhiShipModel.getAllPhiShip(conn);
        req.setAttribute("listAllPhiShip", listAllPhiShip);
        req.getRequestDispatcher("/admin/phiship.jsp").forward(req, resp);

    }


}