package Controller;

import Model.MessagesModel;
import Model.TheLoaiModel;
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
@WebServlet(name = "DeleteTheLoaiServlet", urlPatterns = {"/admin/theloai/delete"})
public class DeleteTheLoaiServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); 
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(req);
        boolean result=false;
     
        try {
            int maTheLoai= Integer.parseInt((String)req.getParameter("id"));          
            result = TheLoaiModel.DeleteByMaTheLoai(conn, maTheLoai);

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
        List<TheLoaiModel> listAllTheLoai= TheLoaiModel.getAllTheLoai(conn);
         
        req.setAttribute("txtTitle", "Thê Loại"); 
        req.setAttribute("listAllTheLoai", listAllTheLoai);
        req.getRequestDispatcher("/admin/theloai.jsp").forward(req, resp);

    }

}