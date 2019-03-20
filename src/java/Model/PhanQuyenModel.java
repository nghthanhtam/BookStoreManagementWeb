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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MITICC06
 */
public class PhanQuyenModel {

    public int getQlSach() {
        return qlSach;
    }

    public void setQlSach(int qlSach) {
        this.qlSach = qlSach;
    }

    public int getMaPhanQuyen() {
        return maPhanQuyen;
    }

    public void setMaPhanQuyen(int maPhanQuyen) {
        this.maPhanQuyen = maPhanQuyen;
    }

    public String getTenQuyen() {
        return tenQuyen;
    }

    public void setTenQuyen(String tenQuyen) {
        this.tenQuyen = tenQuyen;
    }

    public int getQlThanhVien() {
        return qlThanhVien;
    }

    public void setQlThanhVien(int qlThanhVien) {
        this.qlThanhVien = qlThanhVien;
    } 

    public int getQlTheLoai() {
        return qlTheLoai;
    }

    public void setQlTheLoai(int qlTheLoai) {
        this.qlTheLoai = qlTheLoai;
    }

    public int getQlPhiShip() {
        return qlPhiShip;
    }

    public void setQlPhiShip(int qlPhiShip) {
        this.qlPhiShip = qlPhiShip;
    }

    public int getQlPhanQuyen() {
        return qlPhanQuyen;
    }

    public void setQlPhanQuyen(int qlPhanQuyen) {
        this.qlPhanQuyen = qlPhanQuyen;
    }

    public int getQlPhieuNhap() {
        return qlPhieuNhap;
    }

    public void setQlPhieuNhap(int qlPhieuNhap) {
        this.qlPhieuNhap = qlPhieuNhap;
    }

    public int getQlPhieuChi() {
        return qlPhieuChi;
    }

    public void setQlPhieuChi(int qlPhieuChi) {
        this.qlPhieuChi = qlPhieuChi;
    }

    public int getQlNhaCungCap() {
        return qlNhaCungCap;
    }

    public void setQlNhaCungCap(int qlNhaCungCap) {
        this.qlNhaCungCap = qlNhaCungCap;
    }

    public PhanQuyenModel(int maPhanQuyen, String tenQuyen, int qlThanhVien, int qlSach, int qlTheLoai, int qlPhiShip, int qlPhanQuyen, int qlPhieuNhap, int qlPhieuChi, int qlNhaCungCap, int qlHoaDon) {
        this.maPhanQuyen = maPhanQuyen;
        this.tenQuyen = tenQuyen;
        this.qlThanhVien = qlThanhVien;
        this.qlSach = qlSach;
        this.qlTheLoai = qlTheLoai;
        this.qlPhiShip = qlPhiShip;
        this.qlPhanQuyen = qlPhanQuyen;
        this.qlPhieuNhap = qlPhieuNhap;
        this.qlPhieuChi = qlPhieuChi;
        this.qlNhaCungCap = qlNhaCungCap;
        this.qlHoaDon = qlHoaDon;
    }

    public PhanQuyenModel() {
        this.maPhanQuyen = 0;
    }
    
    private int maPhanQuyen;	
    private String tenQuyen;
    private int qlThanhVien;	
    private int qlSach;	
    private int qlTheLoai;	
    private int qlPhiShip;
    private int qlPhanQuyen;
    private int qlPhieuNhap;
    private int qlPhieuChi;	
    private int qlNhaCungCap;
    private int qlHoaDon;

    public int getQlHoaDon() {
        return qlHoaDon;
    }

    public void setQlHoaDon(int qlHoaDon) {
        this.qlHoaDon = qlHoaDon;
    }

    public static boolean InsertNewPhanQuyen(Connection conn, PhanQuyenModel obj) 
            throws SQLException
    { 
        int count = 0;
        try
        {
            String sql="INSERT INTO phanquyen (tenquyen, qlthanhvien, qlsach, qltheloai, qlphiship, qlphanquyen, qlphieunhap, qlphieuchi, qlnhacungcap, qlhoadon) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, obj.getTenQuyen());
            pstm.setInt(2, obj.getQlThanhVien());
            pstm.setInt(3, obj.getQlSach());
            pstm.setInt(4, obj.getQlTheLoai());
            pstm.setInt(5, obj.getQlPhiShip());
            pstm.setInt(6, obj.getQlPhanQuyen()); 
            pstm.setInt(7, obj.getQlPhieuNhap());
            pstm.setInt(8, obj.getQlPhieuChi());
            pstm.setInt(9, obj.getQlNhaCungCap()); 
            pstm.setInt(10, obj.getQlHoaDon());  
            
            count = pstm.executeUpdate(); 
        } catch (SQLException ex) {
            count = 0;
        }
       return count>0;
    }
    
    public static List<PhanQuyenModel> getAllPhanQuyen(Connection conn)
    {
        List<PhanQuyenModel> listPhanQuyen = new ArrayList<PhanQuyenModel>();
        
        String sql = "SELECT * FROM phanquyen";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            
            while(rs.next())
            {
                PhanQuyenModel phanQuyenModel = new PhanQuyenModel(
                        Integer.parseInt(rs.getString("maphanquyen")),
                        rs.getString("tenquyen"),
                        Integer.parseInt(rs.getString("qlthanhvien")),
                        Integer.parseInt(rs.getString("qlsach")),
                        Integer.parseInt(rs.getString("qltheloai")),
                        Integer.parseInt(rs.getString("qlphiship")),
                        Integer.parseInt(rs.getString("qlphanquyen")),
                        Integer.parseInt(rs.getString("qlphieunhap")),
                        Integer.parseInt(rs.getString("qlphieuchi")),
                        Integer.parseInt(rs.getString("qlnhacungcap")),
                        Integer.parseInt(rs.getString("qlhoadon")) );
                
                listPhanQuyen.add(phanQuyenModel);
            }
            
        } catch (SQLException e) {
           
        } 
        
        return listPhanQuyen; 
    }
    
    
    public static boolean DeleteById(Connection conn, int maPhanQuyen) throws SQLException
    {
        String sql = "DELETE FROM phanquyen WHERE maPhanQuyen = ?";
        int count = 0;
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, maPhanQuyen);             
            count = pstm.executeUpdate(); 
            
        } catch (SQLException ex) {
            
        }
        
       
        System.out.println("count = "+count);
        
        return count>0;
    }
    
    
    
}
