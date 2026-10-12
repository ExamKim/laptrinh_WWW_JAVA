package iuh.edu.fit.midterm2.model;

/**
 * @author EXAM
 * @version 1.0
 * @MSSV 23633041
 * @Class DHKTPM19ATT
 * @since 10/12/2026
 */
public class DanhMuc {
    private int madm;
    private String tendanhmuc;
    private String nguoiquanly;
    private String ghichu;

    public DanhMuc() {
    }

    public DanhMuc(int madm, String tendanhmuc, String nguoiquanly, String ghichu) {
        this.madm = madm;
        this.tendanhmuc = tendanhmuc;
        this.nguoiquanly = nguoiquanly;
        this.ghichu = ghichu;
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
}
