
package Controller;

import Model.MessagesModel;
import Model.TheLoaiModel;
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
@WebServlet(name = "EditTheLoaiServlet", urlPatterns = {"/admin/theloai/edit"})
public class EditTheLoaiServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("txtTitle", "Thể Loại");
        boolean isFailedRequest = false; // request thất bại
        String noiDungThongBao = "";
        
        Connection conn = MyUtils.getStoredConnection(req);
        
        String submitValue = req.getParameter("submit");
        if (submitValue !=null && submitValue.equals("capnhat"))
        {
            String tenTheLoai= (String) req.getParameter("tentheloai");      
            int maTheLoai = Integer.parseInt(req.getParameter("matheloai")); 
          
                    
            TheLoaiModel theloaiModel = new TheLoaiModel(maTheLoai, tenTheLoai);
                    
            try {
                    
                    boolean isOk = TheLoaiModel.UpdateTheLoai(conn, theloaiModel);
                    if (isOk)
                    {
                        isFailedRequest = false;
                        noiDungThongBao = "Đã cập nhật thành công!";
                    }
                    else
                        isFailedRequest = true;
                    
            } catch (SQLException ex) { 
                isFailedRequest=true; 
                Logger.getLogger(TheLoaiServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        }
        else
            isFailedRequest = true;
        
        if (isFailedRequest) // nếu có lỗi thì hiện thông báo
        { 
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!","Yêu cầu của bạn không được xử lý!",MessagesModel.ATT_TYPE_ERROR));         
        }
        else
        {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!",noiDungThongBao,MessagesModel.ATT_TYPE_SUCCESS));         
        }
        
        List<TheLoaiModel> listAllTheLoai = TheLoaiModel.getAllTheLoai(conn);
        req.setAttribute("listAllTheLoai", listAllTheLoai);
        req.getRequestDispatcher("/admin/theloai.jsp").forward(req, resp);
        


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        Connection conn = MyUtils.getStoredConnection(req);
        TheLoaiModel theLoaiModel = null;
        boolean result = false;
        
        try {
//            String tenTinh= (String) req.getParameter("tentinh");                  
     
            int maTheLoai = Integer.parseInt((String) req.getParameter("id"));          
            theLoaiModel = TheLoaiModel.FindByMaTheLoai(conn, maTheLoai);
            
            if (theLoaiModel != null)
                result = true;
        } catch (Exception ex) {
            result = false;
        }
        
        if (result == true)
        {
            req.setAttribute("txtTitle", "Sửa thể loại");
            req.setAttribute("theLoaiModel", theLoaiModel);
            req.getRequestDispatcher("/admin/theloai-edit.jsp").forward(req, resp);

        } else { // hiển thị view thông báo thất bại
            
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!","Yêu cầu của bạn không được thực hiện!",MessagesModel.ATT_TYPE_ERROR));         
            
            /* Hiển thị view */
            req.setAttribute("txtTitle", "Thể Loại"); 

            List<TheLoaiModel> listAllTheLoai= TheLoaiModel.getAllTheLoai(conn);
            req.setAttribute("listAllTheLoai", listAllTheLoai);
            req.getRequestDispatcher("/admin/theloai.jsp").forward(req, resp);
        }       
    }
 
}