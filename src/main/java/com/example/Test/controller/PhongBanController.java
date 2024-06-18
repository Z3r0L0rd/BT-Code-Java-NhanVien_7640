package com.example.Test.controller;

import com.example.Test.models.PhongBan;
import com.example.Test.services.PhongBanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/phong_bans")
public class PhongBanController {
    @Autowired
    private PhongBanService phongBanService;

    @GetMapping
    public String phongBan(Model model){
        List<PhongBan> phongBans = phongBanService.getAllPhongBan();
        model.addAttribute("PhongBan", phongBans);
        return "phongBan/phong_bans";
    }

    @GetMapping("/add")
    public String addPhongBan(Model model){
        model.addAttribute("PhongBan", new PhongBan());
        return "phongBan/add";
    }

    @PostMapping("/add")
    public String add(@Valid PhongBan phongBan, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("PhongBan", new PhongBan());
            return "phongBan/add";
        }
        phongBanService.addPhongBan(phongBan);
        return "redirect:/phong_bans";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") String id, Model model) {
        PhongBan phongBan = phongBanService.getPhongBanById(id);
        model.addAttribute("PhongBan", phongBan);
        return "phongBan/edit";
    }

    @PostMapping("/edit/{id}")
    public String editPhongBan(@PathVariable("id") String id, @Valid PhongBan phongBan,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "phongBan/edit";
        }
        phongBan.setMa_Phong(id); // Ensure the ID is set correctly
        phongBanService.updatePhongBan(phongBan);
        return "redirect:/phong_bans"; // Redirect to the list page after update
    }

    @GetMapping("/delete/{id}")
    public String deletePhongBan(@PathVariable String id){
        phongBanService.deletePhongBanById(id);
        return "redirect:/phong_bans";
    }
}