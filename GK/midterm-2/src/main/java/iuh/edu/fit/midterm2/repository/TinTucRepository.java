package iuh.edu.fit.midterm2.repository;

import iuh.edu.fit.midterm2.dto.DanhMucTinTucDTO;
import iuh.edu.fit.midterm2.model.DanhMuc;
import iuh.edu.fit.midterm2.model.TinTuc;

import java.util.List;

/**
 * @author EXAM
 * @version 1.0
 * @MSSV 23633041
 * @Class DHKTPM19ATT
 * @since 10/12/2026
 */
public interface TinTucRepository {
    List<TinTuc> getAllTT();
    TinTuc findTTById(int id);
    boolean update(TinTuc tt);
    boolean delete(int id);
    boolean save(TinTuc tt);
    List<DanhMucTinTucDTO> getListKemDanhMuc();
    List<DanhMucTinTucDTO> getListThuocDanhMuc(String tenDM);
}
