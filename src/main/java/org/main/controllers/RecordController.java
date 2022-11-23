package org.main.controllers;

import lombok.RequiredArgsConstructor;
import org.main.entities.Doctor;
import org.main.entities.Record;
import org.main.entities.User;
import org.main.services.DoctorService;
import org.main.services.RecordService;
import org.main.services.UserSevice;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;
    private final DoctorService doctorService;

    @PostMapping("/record")
    public String addRecord(Model model, Record record) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        record.setUser(user);
        recordService.saveRecord(record);

        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("records", recordService.getRecordsByUserId(user.getId()));
        return "main";
    }
}
