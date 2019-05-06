package com.example.vicuatui;

public class KhoanChi {
    public int ID;
    public double soTien;
    public String hangMuc;
    public String dienGiai;
    public String ngayThang;
    public byte[] anhHangMuc;

    public KhoanChi(int ID, double soTien, String hangMuc, String dienGiai, String ngayThang, byte[] anhHangMuc) {
        this.ID = ID;
        this.soTien = soTien;
        this.hangMuc = hangMuc;
        this.dienGiai = dienGiai;
        this.ngayThang = ngayThang;
        this.anhHangMuc = anhHangMuc;
    }
}
