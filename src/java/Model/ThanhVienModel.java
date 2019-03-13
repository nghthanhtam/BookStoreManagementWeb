/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author MITICC06
 */
public class ThanhVienModel {
    
    private int MaThanhVien;

    public int getMaThanhVien() {
        return MaThanhVien;
    }

    public void setMaThanhVien(int MaThanhVien) {
        this.MaThanhVien = MaThanhVien;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String TenDangNhap) {
        this.TenDangNhap = TenDangNhap;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public int getVaiTro() {
        return VaiTro;
    }

    public void setVaiTro(int VaiTro) {
        this.VaiTro = VaiTro;
    }
    private String TenDangNhap;
    private String MatKhau;
    private int VaiTro;
    
    public ThanhVienModel() {
        this.MaThanhVien = 0;
    }
    
    public ThanhVienModel(int maThanhVien, String tenDangNhap, String matKhau, int vaiTro) {
        this.MaThanhVien = maThanhVien;
        this.TenDangNhap = tenDangNhap;
        this.MatKhau = matKhau;
        this.VaiTro = vaiTro;
    }
    
    
    public static ThanhVienModel FindByTenDangNhap(Connection conn, String tendangnhap) throws SQLException
    {
         String sql = "SELECT * FROM thanhvien WHERE tendangnhap = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, tendangnhap);
         
        ResultSet rs = pstm.executeQuery();
                     

        if (rs.next()) {  
            ThanhVienModel thanhvien = new ThanhVienModel(Integer.parseInt(rs.getString("mathanhvien")),
                    rs.getString("tendangnhap"),
                    rs.getString("matkhau"),
                    Integer.parseInt(rs.getString("vaitro"))
            ); 
             return thanhvien;
        }
        return null;
        
    }
    public static ThanhVienModel InsertNewThanhVien(Connection conn, String tendangnhap, String matkhau, Integer vaitro) 
            throws SQLException
    {
        //INSERT INTO thanhvien (mathanhvien,tendangnhap,matkhau,vaitro)
//VALUES (3,"Trung","1","1");
         String sql="INSERT INTO thanhvien (mathanhvien, tendangnhap, matkhau, vaitro) VALUES (3,?,?,?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, tendangnhap);
        pstm.setString(2, matkhau);
        pstm.setInt(3, vaitro);
        ResultSet rs = pstm.executeQuery();
                     

        if (rs.next()) {  
        //    ThanhVienModel thanhvien = new ThanhVienModel(Integer.parseInt(rs.getString("mathanhvien")),
         //           rs.getString("tendangnhap"),
         //           rs.getString("matkhau"),
         //           Integer.parseInt(rs.getString("vaitro"))
         //   ); 
       //      return thanhvien;
        }
        return null;
        
    
    
}
}

 