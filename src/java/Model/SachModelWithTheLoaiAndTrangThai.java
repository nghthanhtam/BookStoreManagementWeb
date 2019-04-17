/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Blob;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class SachModelWithTheLoaiAndTrangThai {
     private int maSach;
    private String tenTheLoai;    
    private String tenSach;
    private String nhaXuatBan;
    private int namXuatBan;
    private double giaBan;
    private String moTa; 
    private Blob hinhAnh;     
    private int soLuongTon;        
    private String tenTacGia; 
    private int phanTramGiamGia;
    private Date ngayBatDauGiamGia;
    private Date ngayKetThucGiamGia;
    private String tenTrangThai;
}
