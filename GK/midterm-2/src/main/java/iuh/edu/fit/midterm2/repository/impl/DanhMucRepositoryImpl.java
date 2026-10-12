package iuh.edu.fit.midterm2.repository.impl;

import iuh.edu.fit.midterm2.dto.DanhMucTinTucDTO;
import iuh.edu.fit.midterm2.model.DanhMuc;
import iuh.edu.fit.midterm2.repository.DanhMucRepository;
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
public class DanhMucRepositoryImpl implements DanhMucRepository {
    @Override
    public List<DanhMuc> getAllDanhMuc() {
        List<DanhMuc> list= new ArrayList<>();
        String  sql="SELECT * FROM danhmuc";
        try(Connection conn= DBConnection.getConnection();
            PreparedStatement ps= conn.prepareStatement(sql);
            ResultSet rs= ps.executeQuery()) {
        while(rs.next()){
            list.add(new DanhMuc(
                    rs.getInt("madm"),
                    rs.getString("tendanhmuc"),
                    rs.getString("nguoiquanly"),
                    rs.getString("ghichu")
            ));
        }
        return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public DanhMuc findDanhMucById(int id) {
        String sql= "SELECT * FROM danhmuc WHERE MADM=? ";
        try(Connection conn= DBConnection.getConnection();
            PreparedStatement ps= conn.prepareStatement(sql)) {
            ps.setInt(1,id);
            try(ResultSet rs= ps.executeQuery()) {
                if(rs.next()){
                    return new DanhMuc( rs.getInt("madm"),
                            rs.getString("tendanhmuc"),
                            rs.getString("nguoiquanly"),
                            rs.getString("ghichu")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean update(DanhMuc dm) {
        String sql= "UPDATE danhmuc SET TENDANHMUC=?, NGUOIQUANLY=?, GHICHU=? WHERE MADM=? ";
        try(Connection conn= DBConnection.getConnection();
            PreparedStatement ps= conn.prepareStatement(sql)) {
            ps.setString(1,dm.getTendanhmuc());
            ps.setString(2,dm.getNguoiquanly());
            ps.setString(3,dm.getGhichu());
            ps.setInt(4,dm.getMadm());
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql= "DELETE FROM danhmuc WHERE MADM=? ";
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
    public boolean save(DanhMuc dm) {
        String sql= "INSERT INTO danhmuc(TENDANHMUC, NGUOIQUANLY, GHICHU) VALUE( ?, ?, ?)";
        try(Connection conn= DBConnection.getConnection();
            PreparedStatement ps= conn.prepareStatement(sql)) {
            ps.setString(1,dm.getTendanhmuc());
            ps.setString(2,dm.getNguoiquanly());
            ps.setString(3,dm.getGhichu());
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<DanhMucTinTucDTO> getAllDanhMucKemSLTinTuc(){
        List<DanhMucTinTucDTO> list= new ArrayList<>();
        String  sql="SELECT dm.MADM, dm.TENDANHMUC, COUNT(tt.MATT) AS SOLUONGTINTUC " +
                "FROM danhmuc dm" +
                " LEFT JOIN tintuc tt ON dm.MADM=tt.MADM" +
                " GROUP BY dm.MADM";
        try(Connection conn= DBConnection.getConnection();
            PreparedStatement ps= conn.prepareStatement(sql);
            ResultSet rs= ps.executeQuery()) {
            while(rs.next()){
                DanhMucTinTucDTO dto= new DanhMucTinTucDTO();
                dto.setMadm(rs.getInt("MADM"));
                dto.setTendanhmuc(rs.getString("TENDANHMUC"));
                dto.setThongkebaiviet(rs.getInt("SOLUONGTINTUC"));
                list.add(dto);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<DanhMucTinTucDTO> getDanhMucTu3BaiViet(){
        List<DanhMucTinTucDTO> list= new ArrayList<>();
        String  sql="SELECT dm.MADM, dm.TENDANHMUC, COUNT(tt.MATT) AS SOLUONGTINTUC " +
                "FROM danhmuc dm" +
                " LEFT JOIN tintuc tt ON dm.MADM=tt.MADM" +
                " GROUP BY dm.MADM" +
                " HAVING SOLUONGTINTUC>2";
        try(Connection conn= DBConnection.getConnection();
            PreparedStatement ps= conn.prepareStatement(sql);
            ResultSet rs= ps.executeQuery()) {
            while(rs.next()){
                DanhMucTinTucDTO dto= new DanhMucTinTucDTO();
                dto.setMadm(rs.getInt("MADM"));
                dto.setTendanhmuc(rs.getString("TENDANHMUC"));
                dto.setThongkebaiviet(rs.getInt("SOLUONGTINTUC"));
                list.add(dto);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public DanhMucTinTucDTO getMax() {
        String sql = "SELECT dm.MADM, dm.TENDANHMUC, COUNT(tt.MATT) AS SOLUONGTINTUC" +
                " FROM danhmuc dm" +
                " LEFT JOIN tintuc tt ON dm.MADM=tt.MADM" +
                " GROUP BY dm.MADM" +
                " HAVING COUNT(tt.MATT)=(SELECT MAX(SOLUONG)" +
                " FROM ( " +
                "SELECT COUNT(tt2.MATT) AS SOLUONG FROM danhmuc dm2" +
                " LEFT JOIN tintuc tt2 ON dm2.MADM=tt2.MADM" +
                " GROUP BY dm2.MADM) ASTHONGKEMAX " +
                ")";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                DanhMucTinTucDTO dto = new DanhMucTinTucDTO();
                dto.setMadm(rs.getInt("MADM"));
                dto.setTendanhmuc(rs.getString("TENDANHMUC"));
                dto.setThongkebaiviet(rs.getInt("SOLUONGTINTUC"));
                return dto;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
    @Override
    public List<DanhMucTinTucDTO> getThongKeTyLeTinTuc(){
        List<DanhMucTinTucDTO> list= new ArrayList<>();
        String  sql="SELECT dm.MADM, dm.TENDANHMUC, COUNT(tt.MATT) AS SOLUONGTINTUC,  " +
                " ROUND(COUNT(tt.MATT) * 100.0 / NULLIF((SELECT COUNT(*) FROM tintuc), 0), 2) AS TYLE " +
                " FROM danhmuc dm " +
                " LEFT JOIN tintuc tt ON dm.MADM=tt.MADM" +
                " GROUP BY dm.MADM, dm.TENDANHMUC" ;
        try(Connection conn= DBConnection.getConnection();
            PreparedStatement ps= conn.prepareStatement(sql);
            ResultSet rs= ps.executeQuery()) {
            while(rs.next()){
                DanhMucTinTucDTO dto= new DanhMucTinTucDTO();
                dto.setMadm(rs.getInt("MADM"));
                dto.setTendanhmuc(rs.getString("TENDANHMUC"));
                dto.setThongkebaiviet(rs.getInt("SOLUONGTINTUC"));
                dto.setTyle(rs.getDouble("TYLE"));
                list.add(dto);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
