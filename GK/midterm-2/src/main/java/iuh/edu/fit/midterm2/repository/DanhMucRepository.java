package iuh.edu.fit.midterm2.repository;

import iuh.edu.fit.midterm2.dto.DanhMucTinTucDTO;
import iuh.edu.fit.midterm2.model.DanhMuc;

import java.util.List;

/**
 * @author EXAM
 * @version 1.0
 * @MSSV 23633041
 * @Class DHKTPM19ATT
 * @since 10/12/2026
 */
public interface DanhMucRepository {
    List<DanhMuc> getAllDanhMuc();
    DanhMuc findDanhMucById(int id);
    boolean update(DanhMuc dm);
    boolean delete(int id);
    boolean save(DanhMuc dm);
    List<DanhMucTinTucDTO> getAllDanhMucKemSLTinTuc();
    List<DanhMucTinTucDTO> getDanhMucTu3BaiViet();
    DanhMucTinTucDTO getMax();
    List<DanhMucTinTucDTO> getThongKeTyLeTinTuc();
}
