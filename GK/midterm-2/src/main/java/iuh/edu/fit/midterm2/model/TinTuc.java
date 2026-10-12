package iuh.edu.fit.midterm2.model;

/**
 * @author EXAM
 * @version 1.0
 * @MSSV 23633041
 * @Class DHKTPM19ATT
 * @since 10/12/2026
 */
public class TinTuc {
    private int matt;
    private String tieude;
    private String noidungtt;
    private String lienket;
    private int madm;

    public TinTuc() {
    }

    public TinTuc(int matt, String tieude, String noidungtt, String lienket, int madm) {
        this.matt = matt;
        this.tieude = tieude;
        this.noidungtt = noidungtt;
        this.lienket = lienket;
        this.madm = madm;
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

    public int getMadm() {
        return madm;
    }

    public void setMadm(int madm) {
        this.madm = madm;
    }
}
