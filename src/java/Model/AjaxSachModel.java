/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Dell
 */
public class AjaxSachModel {
    private int maSach;
    private String tenTheLoai;
    private String tenSach;
    private double giaBan;
    private String tenTacGia;

    

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }
    

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setMaTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }
    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public AjaxSachModel(int maSach,String tenTheLoai, String tenSach, double giaBan, String tenTacGia) {
        this.maSach = maSach;
        this.tenTheLoai = tenTheLoai;
        this.tenSach = tenSach;
        this.giaBan = giaBan;
        this.tenTacGia = tenTacGia;
    }
    
}
