/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author MITICC06
 */
public class ThanhVienModel {
    
    private int MaThanhVien;
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
    
    
}
