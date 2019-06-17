package com.example.vicuatui;

public class KhoanChi {
    public int ID;
    public double soTien;
    public String hangMuc;
    public String dienGiai;
    public String ngayThang;
    public byte[] anhHangMuc;

    public KhoanChi(){

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }

    public String getHangMuc() {
        return hangMuc;
    }

    public void setHangMuc(String hangMuc) {
        this.hangMuc = hangMuc;
    }

    public String getDienGiai() {
        return dienGiai;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public String getNgayThang() {
        return ngayThang;
    }

    public void setNgayThang(String ngayThang) {
        this.ngayThang = ngayThang;
    }

    public byte[] getAnhHangMuc() {
        return anhHangMuc;
    }

    public void setAnhHangMuc(byte[] anhHangMuc) {
        this.anhHangMuc = anhHangMuc;
    }

    public KhoanChi(int ID, double soTien, String hangMuc, String dienGiai, String ngayThang, byte[] anhHangMuc) {
        this.ID = ID;
        this.soTien = soTien;
        this.hangMuc = hangMuc;
        this.dienGiai = dienGiai;
        this.ngayThang = ngayThang;
        this.anhHangMuc = anhHangMuc;
    }
}
