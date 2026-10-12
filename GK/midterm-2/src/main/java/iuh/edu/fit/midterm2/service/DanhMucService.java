package iuh.edu.fit.midterm2.service;

import iuh.edu.fit.midterm2.dto.DanhMucTinTucDTO;
import iuh.edu.fit.midterm2.model.DanhMuc;
import iuh.edu.fit.midterm2.repository.DanhMucRepository;
import iuh.edu.fit.midterm2.repository.impl.DanhMucRepositoryImpl;

import java.util.List;

/**
 * @author EXAM
 * @version 1.0
 * @MSSV 23633041
 * @Class DHKTPM19ATT
 * @since 10/12/2026
 */
public class DanhMucService {
    private DanhMucRepository danhMucRepository=new DanhMucRepositoryImpl();

    public List<DanhMuc> getAll(){return danhMucRepository.getAllDanhMuc();};
    public DanhMuc getById(int id){return danhMucRepository.findDanhMucById(id);};
    public boolean update(DanhMuc dm){return danhMucRepository.update(dm);};
    public boolean delete(int id){return danhMucRepository.delete(id);};
    public boolean save(DanhMuc dm){return danhMucRepository.save(dm);};
    public List<DanhMucTinTucDTO> getSoLuongTinTuc(){return danhMucRepository.getAllDanhMucKemSLTinTuc();};
    public List<DanhMucTinTucDTO> getListTu3TinTuc(){return danhMucRepository.getDanhMucTu3BaiViet();};
    public DanhMucTinTucDTO getMax(){return danhMucRepository.getMax();};
    public List<DanhMucTinTucDTO> getThongKeTyLeTinTuc(){return danhMucRepository.getThongKeTyLeTinTuc();}
}
