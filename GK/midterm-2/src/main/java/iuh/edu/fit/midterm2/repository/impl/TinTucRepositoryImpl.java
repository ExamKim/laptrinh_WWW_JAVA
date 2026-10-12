package iuh.edu.fit.midterm2.repository.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import iuh.edu.fit.midterm2.dto.DanhMucTinTucDTO;
import iuh.edu.fit.midterm2.model.DanhMuc;
import iuh.edu.fit.midterm2.model.TinTuc;
import iuh.edu.fit.midterm2.repository.TinTucRepository;
import iuh.edu.fit.midterm2.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author EXAM
 * @version 1.0
 * @MSSV 23633041
 * @Class DHKTPM19ATT
 * @since 10/12/2026
 */

public class TinTucRepositoryImpl implements TinTucRepository {
    @Override
    public List<TinTuc> getAllTT() {
        List<TinTuc> list= new ArrayList<>();
        String  sql="SELECT * FROM tintuc";
        try(Connection conn= DBConnection.getConnection();
            PreparedStatement ps= conn.prepareStatement(sql);
            ResultSet rs= ps.executeQuery()) {
            while(rs.next()){
                list.add(new TinTuc(
                        rs.getInt("matt"),
                        rs.getString("tieude"),
                        rs.getString("noidungtt"),
                        rs.getString("lienket"),
                        rs.getInt("madm")
                ));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TinTuc findTTById(int id) {
        String sql= "SELECT * FROM tintuc WHERE MATT=? ";
        try(Connection conn= DBConnection.getConnection();
            PreparedStatement ps= conn.prepareStatement(sql)) {
            ps.setInt(1,id);
            try(ResultSet rs= ps.executeQuery()) {
                if(rs.next()){
                    return new TinTuc( rs.getInt("matt"),
                            rs.getString("tieude"),
                            rs.getString("noidungtt"),
                            rs.getString("lienket"),
                            rs.getInt("madm")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean update(TinTuc tt) {
        String sql= "UPDATE tintuc SET TIEUDE=?, NOIDUNGTT=?, LIENKET=?, MADM=? WHERE MATT=? ";
        try(Connection conn= DBConnection.getConnection();
            PreparedStatement ps= conn.prepareStatement(sql)) {
            ps.setString(1,tt.getTieude());
            ps.setString(2,tt.getNoidungtt());
            ps.setString(3,tt.getLienket());
            ps.setInt(4,tt.getMadm());
            ps.setInt(5,tt.getMatt());
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql= "DELETE FROM tintuc WHERE MATT=? ";
        try(Connection conn= DBConnection.getConnection();
            PreparedStatement ps= conn.prepareStatement(sql)) {
            ps.setInt(1,id);
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean save(TinTuc tt) {
        String sql= "INSERT INTO tintuc(TIEUDE, NOIDUNGTT, LIENKET, MADM) VALUE( ?, ?, ?, ?)";
        try(Connection conn= DBConnection.getConnection();
            PreparedStatement ps= conn.prepareStatement(sql)) {
            ps.setString(1,tt.getTieude());
            ps.setString(2,tt.getNoidungtt());
            ps.setString(3,tt.getLienket());
            ps.setInt(4,tt.getMadm());
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<DanhMucTinTucDTO> getListKemDanhMuc() {
        List<DanhMucTinTucDTO> list= new ArrayList<>();
        String  sql="SELECT tt.MATT, tt.TIEUDE, dm.TENDANHMUC FROM tintuc tt" +
                " INNER JOIN danhmuc dm ON tt.MADM=dm.MADM";
        try(Connection conn= DBConnection.getConnection();
            PreparedStatement ps= conn.prepareStatement(sql);
            ResultSet rs= ps.executeQuery()) {
            while(rs.next()){
                list.add(new DanhMucTinTucDTO(
                        rs.getInt("matt"),
                        rs.getString("tieude"),
                        rs.getString("tendanhmuc")
                ));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DanhMucTinTucDTO> getListThuocDanhMuc(String tenDM) {
        List<DanhMucTinTucDTO> list= new ArrayList<>();
        String  sql="SELECT tt.MATT, tt.TIEUDE, dm.TENDANHMUC FROM tintuc tt" +
                " INNER JOIN danhmuc dm ON tt.MADM = dm.MADM" +
                " WHERE dm.TENDANHMUC=? ";
        try(Connection conn= DBConnection.getConnection();
            PreparedStatement ps= conn.prepareStatement(sql);
            ) {
            ps.setString(1,tenDM);
            try(ResultSet rs= ps.executeQuery()){
                while(rs.next()){
                    list.add(new DanhMucTinTucDTO(
                            rs.getInt("matt"),
                            rs.getString("tieude"),
                            rs.getString("tendanhmuc")
                    ));
                }
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
