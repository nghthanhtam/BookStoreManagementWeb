/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MessagesModel;
import Model.PhanQuyenModel;
import Model.ThanhVienModel;
import Utility.MyUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MITICC06
 */
@WebServlet(name = "DangKyThanhVienServlet", urlPatterns = {"/dangky"})
public class DangKyThanhVienServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  
        boolean isFailedRequest = false; // request thất bại 
        
        String noiDungThongBao = "";
        String button = req.getParameter("submit");
        Connection conn = MyUtils.getStoredConnection(req); 
        
        if (button != null && button.equals("dangky"))
        {
            String tenDangNhap = (String) req.getParameter("tendangnhap");
            String matKhau = (String) req.getParameter("matkhau");
            String lapLaiMatKhau = (String) req.getParameter("laplaimatkhau");
            String hoTen = (String) req.getParameter("hoten");
            String diaChi = (String) req.getParameter("diachi");
            String soDienThoai = (String) req.getParameter("sodienthoai");
            String email = (String) req.getParameter("email"); 
            String xacNhanDieuKhoan = (String) req.getParameter("xacnhandieukhoan"); 

            req.setAttribute("tendangnhap", tenDangNhap);
            req.setAttribute("hoten", hoTen);
            req.setAttribute("diachi", diaChi);
            req.setAttribute("sodienthoai", soDienThoai);
            req.setAttribute("email", email);
            req.setAttribute("xacnhandieukhoan", xacNhanDieuKhoan);
            
            try {
                
                if (Pattern.compile("^[a-zA-Z0-9_\\-]{5,50}$").matcher(tenDangNhap).matches() == false) // username k hợp lệ //Check username gồm 6-14 kí tự từ a-z 0-9 và "_" "-"
                    throw new Exception("Tên đăng nhập không hợp lệ! Chỉ bao gồm a-z, A-Z, 0-9 và _"); 
                
                if (matKhau.equals(lapLaiMatKhau)!= true)
                    throw new Exception("Mật khẩu lặp lại của bạn không trùng khớp!");
                
                if (matKhau.equals("") == true)
                    throw new Exception("Bạn chưa nhập mật khẩu!");
                
                if (Pattern.compile("^(0|\\+84)[0-9]{3,10}$").matcher(soDienThoai).matches() == false)
                    throw new Exception("Số điện thoại không hợp lệ!"); 
                
                if (Pattern.compile("^[a-zA-Z0-9._]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$").matcher(email).matches() == false)  
                    throw new Exception("Địa chỉ email không hợp lệ!");
                
                if (xacNhanDieuKhoan==null || !xacNhanDieuKhoan.equals("true"))
                    throw new Exception("Bạn chưa đồng ý điều khoản sử dụng!");
                
                
                List<ThanhVienModel> listAllThanhVien = ThanhVienModel.getAllThanhVien(conn);
                    for (int i = 0; i < listAllThanhVien.size(); i++) {
                        if (Objects.equals(tenDangNhap, listAllThanhVien.get(i).getTenDangNhap())) {
                             throw new Exception("Tên đăng nhập đã được sử dụng!");
                        }
                        if (Objects.equals(email, listAllThanhVien.get(i).getEmail())) {
                            throw new Exception("Địa chỉ email của bạn đã được sử dụng!");
                        }
                    }
                
                
                
                boolean isOk = ThanhVienModel.InsertNewThanhVien(conn, new ThanhVienModel(0, tenDangNhap, matKhau, hoTen, diaChi, soDienThoai, email, PhanQuyenModel.ATT_MAPHANQUYEN_THANHVIEN));
                if (isOk)
                {
                    noiDungThongBao = "Đăng ký thành viên mới hoàn tất!";
                    isFailedRequest = false;
                } 
                else
                {
                    throw new Exception("Yêu cầu của bạn không thể xử lý!");
                }
                     
            } catch (Exception ex) {
                isFailedRequest = true;
                noiDungThongBao = ex.getMessage();
            }
        }
        else
        {
            isFailedRequest = true; // thất bại!
            noiDungThongBao = "Yêu cầu của bạn không thể xử lý!";
        }
        
      
        if (isFailedRequest == true)
        {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", noiDungThongBao, MessagesModel.ATT_TYPE_ERROR));
        }
        else
        {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!", noiDungThongBao, MessagesModel.ATT_TYPE_SUCCESS));
        }
        
         
        req.setAttribute("txtTitle", "Đăng ký thành viên");
          
        req.getRequestDispatcher("dangky.jsp").forward(req, resp);;
         

        
            /*
            
            
                              
            
            try {

                if (usernameMatch.matches() == true && passwordMatch.matches() == true
                        && soDienThoaiMatch.matches() == true && emailMatch.matches() == true) {
                    isInvalidEmail = false;
                    isInvalidPassword = false;
                    isInvalidNumber = false;
                    isInvalidUsername = false;
                    List<ThanhVienModel> listAllThanhVien = ThanhVienModel.getAllThanhVien(conn);
                    for (int i = 0; i < listAllThanhVien.size(); i++) {
                        if (Objects.equals(tenDangNhap, listAllThanhVien.get(i).getTenDangNhap())) {
                            haveUsedName = true;
                            System.out.println("Trùng tên");
                            break;
                        }
                        if (Objects.equals(email, listAllThanhVien.get(i).getEmail())) {
                            haveUsedEmail = true;
                            System.out.println("Trùng email");
                            break;
                        }

                    }
                    System.out.println(haveUsedName);
                    if (!haveUsedName && !haveUsedEmail) {
                        if (Objects.equals(matKhau, lapLaiMatKhau))//check mat khau co khop voi nhau khong
                        {
                            System.out.println("Khớp mật khẩu");
                            isWrongPassword = false;
                        } else {
                            isWrongPassword = true;
                            System.out.println("Không khớp mật khẩu");
                        }
                        if (isWrongPassword == false) {
                            System.out.println(isOk);
                            if (isOk) {
                                isFailedRequest = false;
                                noiDungThongBao = "Đã thêm thành viên mới!";
                            } else {
                                System.out.println("stage cuoi");
                                isFailedRequest = true;
                            }
                        }
                    }

                } else {
                    if (usernameMatch.matches() == false) {
                        isInvalidUsername = true;
                    } else {
                        if (passwordMatch.matches() == false) {
                            isInvalidPassword = true;
                        } else {
                            if (soDienThoaiMatch.matches() == false) {
                                isInvalidNumber = true;
                            } else {
                                if (emailMatch.matches() == false) {
                                    isInvalidEmail = true;
                                }
                            }
                        }
                    }

                }

            } catch (SQLException ex) {
                isFailedRequest = true;
                Logger.getLogger(ThanhVienServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            isFailedRequest = true;
        }

        if (isFailedRequest || isWrongPassword || haveUsedName || haveUsedEmail
                || isInvalidUsername || isInvalidPassword || isInvalidNumber || isInvalidEmail) // nếu có lỗi thì hiện thông báo
        {
            if (isFailedRequest) {
                req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", "Yêu cầu của bạn không được xử lý!", MessagesModel.ATT_TYPE_ERROR));
            }
            if (haveUsedName) {
                req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", "Tên đăng đã có người dùng!", MessagesModel.ATT_TYPE_ERROR));
            }
            
            if (haveUsedEmail) {
                req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", "Email đã có người dùng!", MessagesModel.ATT_TYPE_ERROR));
            }
            if (isInvalidUsername) {
                req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", "Tên đăng nhập không hợp lệ", MessagesModel.ATT_TYPE_ERROR));
            }
            if (isInvalidPassword) {
                req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", "Mật khẩu không hợp lệ!", MessagesModel.ATT_TYPE_ERROR));
            }
            if (isInvalidNumber) {
                req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", "Số điện thoại không hợp lệ!", MessagesModel.ATT_TYPE_ERROR));
            }
            if (isInvalidEmail) {
            }

        } else {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!", noiDungThongBao, MessagesModel.ATT_TYPE_SUCCESS));
        }

        Connection conn = MyUtils.getStoredConnection(req);
        req.setAttribute("txtTitle", "Thành viên");

        List<PhanQuyenModel> listAllPhanQuyen = PhanQuyenModel.getAllPhanQuyen(conn);
        List<ThanhVienModel> listAllThanhVien = ThanhVienModel.getAllThanhVien(conn);

        
        req.setAttribute("listAllThanhVien", listAllThanhVien);
        req.setAttribute("listAllPhanQuyen", listAllPhanQuyen);
        req.getRequestDispatcher("/admin/thanhvien.jsp").forward(req, resp);;

    
    */
            
            
            
    
    
    
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("dangky.jsp").forward(req, resp);    
    }

     
}
