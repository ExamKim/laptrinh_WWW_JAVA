package iuh.edu.fit.midterm2.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author EXAM
 * @version 1.0
 * @MSSV 23633041
 * @Class DHKTPM19ATT
 * @since 10/12/2026
 */

public class DanhMucTinTucDTO {
    private int madm;
    private String tendanhmuc;
    private String nguoiquanly;
    private String ghichu;
    private int matt;
    private String tieude;
    private String noidungtt;
    private String lienket;
    private int thongkebaiviet;
    private Double tyle; // Kiểu Double để chứa phần trăm (ví dụ: 20.00)
    public Double getTyle() { return tyle; }
    public void setTyle(Double tyle) { this.tyle = tyle; }
    public DanhMucTinTucDTO( int matt, String tieude, String tendanhmuc) {
        this.tendanhmuc = tendanhmuc;
        this.matt = matt;
        this.tieude = tieude;
    }

    public int getMadm() {
        return madm;
    }

    public void setMadm(int madm) {
        this.madm = madm;
    }

    public String getTendanhmuc() {
        return tendanhmuc;
    }

    public void setTendanhmuc(String tendanhmuc) {
        this.tendanhmuc = tendanhmuc;
    }

    public String getNguoiquanly() {
        return nguoiquanly;
    }

    public void setNguoiquanly(String nguoiquanly) {
        this.nguoiquanly = nguoiquanly;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public int getMatt() {
        return matt;
    }

    public void setMatt(int matt) {
        this.matt = matt;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getNoidungtt() {
        return noidungtt;
    }

    public void setNoidungtt(String noidungtt) {
        this.noidungtt = noidungtt;
    }

    public String getLienket() {
        return lienket;
    }

    public void setLienket(String lienket) {
        this.lienket = lienket;
    }

    public int getThongkebaiviet() {
        return thongkebaiviet;
    }

    public void setThongkebaiviet(int thongkebaiviet) {
        this.thongkebaiviet = thongkebaiviet;
    }

    public DanhMucTinTucDTO() {
    }

    public DanhMucTinTucDTO(int madm, String tendanhmuc, String nguoiquanly, String ghichu, int matt, String tieude, String noidungtt, String lienket, int thongkebaiviet) {
        this.madm = madm;
        this.tendanhmuc = tendanhmuc;
        this.nguoiquanly = nguoiquanly;
        this.ghichu = ghichu;
        this.matt = matt;
        this.tieude = tieude;
        this.noidungtt = noidungtt;
        this.lienket = lienket;
        this.thongkebaiviet = thongkebaiviet;
    }
}
