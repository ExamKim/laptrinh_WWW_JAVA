package iuh.edu.fit.midterm2.service;

import iuh.edu.fit.midterm2.dto.DanhMucTinTucDTO;
import iuh.edu.fit.midterm2.model.DanhMuc;
import iuh.edu.fit.midterm2.model.TinTuc;
import iuh.edu.fit.midterm2.repository.DanhMucRepository;
import iuh.edu.fit.midterm2.repository.TinTucRepository;
import iuh.edu.fit.midterm2.repository.impl.DanhMucRepositoryImpl;
import iuh.edu.fit.midterm2.repository.impl.TinTucRepositoryImpl;

import java.util.List;

/**
 * @author EXAM
 * @version 1.0
 * @MSSV 23633041
 * @Class DHKTPM19ATT
 * @since 10/12/2026
 */
public class TinTucService {
    private TinTucRepository tinTucRepository=new TinTucRepositoryImpl();

    public List<TinTuc> getAll(){return tinTucRepository.getAllTT();};
    public TinTuc getById(int id){return tinTucRepository.findTTById(id);};
    public boolean update(TinTuc tt){return tinTucRepository.update(tt);};
    public boolean delete(int id){return tinTucRepository.delete(id);};
    public boolean save(TinTuc tt){return tinTucRepository.save(tt);};
    public List<DanhMucTinTucDTO> getListKemDanhMuc(){return tinTucRepository.getListKemDanhMuc();};
    public List<DanhMucTinTucDTO> getListThuocDanhMuc(String tenDM){return tinTucRepository.getListThuocDanhMuc(tenDM);};
}
