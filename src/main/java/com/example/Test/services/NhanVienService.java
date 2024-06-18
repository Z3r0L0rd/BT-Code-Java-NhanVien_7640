package com.example.Test.services;

import com.example.Test.models.NhanVien;
import com.example.Test.repository.INhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhanVienService {
    @Autowired
    private INhanVienRepository nhanVienRepository;
    public List<NhanVien> getAllNhanVien() {return nhanVienRepository.findAll();}
    public NhanVien getNhanVienById(String id) {
        return nhanVienRepository.findById(id).orElse(null);
    }
    public void addNhanVien(NhanVien nhanVien) {nhanVienRepository.save(nhanVien);}
    public void deleteNhanVienById(String id) {
        if(!nhanVienRepository.existsById(id))
            throw new IllegalStateException("Không tìm thấy nhân viên cần xóa");
        nhanVienRepository.deleteById(id);}
    public void updateNhanVien(NhanVien nhanVien) {
        var existingNhanVien = nhanVienRepository.findById(nhanVien.getMa_NV())
            .orElseThrow(() -> new IllegalStateException("Không tìm thấy nhân viên"));
        existingNhanVien.setMa_NV(nhanVien.getMa_NV());
        existingNhanVien.setTen_NV(nhanVien.getTen_NV());
        existingNhanVien.setLuong(nhanVien.getLuong());
        existingNhanVien.setPhongBan(nhanVien.getPhongBan());
        existingNhanVien.setPhai(nhanVien.getPhai());
        nhanVienRepository.save(existingNhanVien);
        }
}
