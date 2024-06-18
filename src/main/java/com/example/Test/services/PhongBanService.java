package com.example.Test.services;

import com.example.Test.models.PhongBan;
import com.example.Test.repository.IPhongBanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhongBanService {
    @Autowired
    private IPhongBanRepository phongBanRepository;
    public List<PhongBan> getAllPhongBan() {return phongBanRepository.findAll();}
    public PhongBan getPhongBanById(String id) {return phongBanRepository.findById(id).orElse(null);}
    public void addPhongBan(PhongBan phongBan) {phongBanRepository.save(phongBan);}
    public void updatePhongBan(PhongBan phongBan){
            var existingPhongBan = phongBanRepository.findById(phongBan.getMa_Phong())
                    .orElseThrow(() -> new IllegalStateException("Không tìm thấy phòng ban chỉ định"));
            existingPhongBan.setMa_Phong(phongBan.getMa_Phong());
            existingPhongBan.setTen_Phong(phongBan.getTen_Phong());
            phongBanRepository.save(existingPhongBan);
    }
    public void deletePhongBanById(String id) {
        if(!phongBanRepository.existsById(id))
            throw new IllegalStateException("Không tìm thấy phòng ban cần xóa!");
        phongBanRepository.deleteById(id);}
}
