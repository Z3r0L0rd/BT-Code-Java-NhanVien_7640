package com.example.Test.controller;

import com.example.Test.models.NhanVien;
import com.example.Test.services.NhanVienService;
import com.example.Test.services.PhongBanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Nhan_Vien")
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private PhongBanService phongBanService;
    @GetMapping
    public String showAllNhanVien(Model model) {
        List<NhanVien> nhanVien = nhanVienService.getAllNhanVien();
        model.addAttribute("NhanVien", nhanVien);
        return "NhanVien/list";
    }
    @GetMapping("/add")
    public String addNhanVienForm(Model model) {
        model.addAttribute("NhanVien", new NhanVien());
        model.addAttribute("PhongBanList", phongBanService.getAllPhongBan());
        return "NhanVien/add";
    }
    @PostMapping("/add")
    public String addNhanVien(@Valid NhanVien nhanVien, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("NhanVien",nhanVien);
            model.addAttribute("PhongBanList", phongBanService.getAllPhongBan());
            return "NhanVien/add";
        }
        nhanVienService.addNhanVien(nhanVien);
        return "redirect:/Nhan_Vien";
    }
    @GetMapping("/delete/{id}")
    public String deleteNhanVien(@PathVariable String id) {
        nhanVienService.deleteNhanVienById(id);
        return "redirect:/Nhan_Vien";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id")String id, Model model) {
        NhanVien nhanVien = nhanVienService.getNhanVienById(id);

        if(nhanVien==null) {
            return "redirect:/NhanVien/list";
        }
        model.addAttribute("NhanVien",nhanVien);
        model.addAttribute("PhongBanList", phongBanService.getAllPhongBan());
        return "NhanVien/edit";
    }
    @PostMapping("/edit/{id}")
    public String updateNhanVien(@PathVariable("id") String id, @Valid NhanVien nhanVien,
                                 BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("PhongBanList",phongBanService.getAllPhongBan());
            return "NhanVien/edit";
        }
        nhanVien.setMa_NV(id);
        nhanVienService.updateNhanVien(nhanVien);
        return "redirect:/Nhan_Vien";
    }
}
