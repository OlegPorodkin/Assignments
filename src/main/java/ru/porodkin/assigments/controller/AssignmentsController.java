package ru.porodkin.assigments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.porodkin.assigments.domain.Schedule;
import ru.porodkin.assigments.domain.SemesterAssignment;
import ru.porodkin.assigments.domain.Teacher;
import ru.porodkin.assigments.service.AssignmentsService;
import ru.porodkin.assigments.service.ScheduleService;
import ru.porodkin.assigments.service.TeacherService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/assignments")
public class AssignmentsController extends AbstractController<SemesterAssignment> {

    private TeacherService teacherService;
    private ScheduleService scheduleService;

    @Autowired
    public AssignmentsController(AssignmentsService service, TeacherService teacherService, ScheduleService scheduleService) {
        super(service);
        this.teacherService = teacherService;
        this.scheduleService = scheduleService;
    }

    @GetMapping()
    public String getAll(Model model){
        List<SemesterAssignment> allAssignment = service.getAll();
        model.addAttribute("assignments", allAssignment);
        return "assignment";
    }

    @GetMapping(params = "id")
    public String getEntity(@RequestParam(value = "id", defaultValue = "0") long id, Model model){
        SemesterAssignment assignment = service.getEntity(id);
        model.addAttribute("assignment", assignment);
        return "assignment";
    }

    @GetMapping(path = "/add")
    public String showAddSaveEntity(Model model){
        SemesterAssignment assignment = new SemesterAssignment();
        List<Teacher> teachers = teacherService.getAll();
        List<Schedule> schedules = scheduleService.getAll();
        model.addAttribute("assignment", assignment);
        model.addAttribute("teachers", teachers);
        model.addAttribute("schedules", schedules);
        return "add_assignment";
    }

    @PostMapping(path = "/add")
    public String saveEntity(@ModelAttribute("assignment") @Valid SemesterAssignment assignment){
        service.save(assignment);
        return "redirect:/assignments";
    }

    @PutMapping(path = "/update")
    public String update(@ModelAttribute("assignment") SemesterAssignment assignment){
        service.update(assignment);
        return "redirect:/assignments";
    }

    @DeleteMapping(path = "/delete")
    public String delete(@ModelAttribute("assignment") SemesterAssignment assignment){
        service.delete(assignment.getId());
        return "redirect:/assignments";
    }
}
