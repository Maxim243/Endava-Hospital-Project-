package org.main.controllers;

import lombok.RequiredArgsConstructor;
import org.main.entities.Record;
import org.main.entities.User;
import org.main.services.DoctorService;
import org.main.services.RecordService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final DoctorService doctorService;
    private final RecordService recordService;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String recordList(Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("records", recordService.getRecordsByUserId(user.getId()));
        return "main";
    }

    @GetMapping("/record/{id}")
    public String delete(@AuthenticationPrincipal User user, @PathVariable Long id,
                         Map<String, Object> model) {
        recordService.deleteById(id);
        model.put("records", recordService.getRecordsByUserId(user.getId()));
        model.put("doctors", doctorService.getAllDoctors());
        return "redirect:/main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String date,
            @RequestParam String time,
            @RequestParam String problem,
            @RequestParam Long doctorId,
            Map<String, Object> model
    ) {
        LocalDate localDate = LocalDate.of(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)), Integer.parseInt(date.substring(8, 10)));
        LocalTime localTime = LocalTime.of(Integer.parseInt(time.substring(0, 2)), Integer.parseInt(time.substring(3, 5)));

        List<Record> records = recordService.getRecordsByDoctorId(doctorId);
        for (Record doctorsRecord : records
        ) {
            LocalTime doctorAfter = doctorsRecord.getTime().plusMinutes(15);
            LocalTime doctorBefore = doctorsRecord.getTime().minusMinutes(15);
            if (doctorsRecord.getDate().equals(localDate)
                    && localTime.isBefore(doctorAfter) && localTime.isAfter(doctorBefore)) {
                model.put("records", recordService.getRecordsByUserId(user.getId()));
                model.put("doctors", doctorService.getAllDoctors());
                return "mainRecover";
            }
        }

        Record record = new Record();
        record.setUser(user);
        record.setDoctor(doctorService.getDoctorById(doctorId));
        record.setTime(localTime);
        record.setDate(localDate);
        record.setProblem(problem);

        recordService.saveRecord(record);

        model.put("records", recordService.getRecordsByUserId(user.getId()));
        model.put("doctors", doctorService.getAllDoctors());
        return "main";
    }

}
