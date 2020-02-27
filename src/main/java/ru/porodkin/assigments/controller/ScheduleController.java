package ru.porodkin.assigments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.porodkin.assigments.domain.Schedule;
import ru.porodkin.assigments.service.AbstractService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/schedule")
public class ScheduleController extends AbstractController<Schedule> {

    @Autowired
    public ScheduleController(AbstractService<Schedule> service) {
        super(service);
    }

    @GetMapping
    public String getAllSchedule(Model model){
        List<Schedule> schedules = service.getAll();
        model.addAttribute("schedules", schedules);
        return "";
    }

    @GetMapping(path = "/add")
    public String viewAddSchedule(Model model){
        Schedule schedule = new Schedule();
        model.addAttribute("schedule", schedule);
        return "";
    }

    @PostMapping(path = "/add")
    public String addSchedule(@ModelAttribute("schedule") @Valid Schedule schedule){
        service.save(schedule);
        return "redirect:/schedule";
    }

    @GetMapping(path = "/update/{id}")
    public String viewUpdateSchedule(@PathVariable("id") Long id, Model model){
        Schedule schedule = service.getEntity(id);
        model.addAttribute("schedule", schedule);
        return "";
    }

    @PostMapping(path = "/update")
    public String updateSchedule(@ModelAttribute("schedule") @Valid Schedule schedule){
        service.update(schedule);
        return "redirect:/schedule";
    }

    @DeleteMapping(path = "/delete/{id}")
    public String deleteSchedule(@PathVariable("id")Long id){
        service.delete(id);
        return "redirect:/schedule";
    }
}
