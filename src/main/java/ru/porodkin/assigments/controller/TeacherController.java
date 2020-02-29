package ru.porodkin.assigments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.porodkin.assigments.domain.Teacher;
import ru.porodkin.assigments.service.AbstractService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "teacher")
public class TeacherController extends AbstractController<Teacher>{

    @Autowired
    public TeacherController(AbstractService<Teacher> service) {
        super(service);
    }

    @GetMapping
    public String getAllTeachers(Model model){
        List<Teacher> teachers = service.getAll();
        model.addAttribute("teachers", teachers);
        return "teacher";
    }

    @GetMapping(path = "/add")
    public String viewAddTeacher(Model model){
        Teacher teacher = new Teacher();
        model.addAttribute("teacher", teacher);
        return "add_teacher";
    }

    @PostMapping(path = "/add")
    public String addTeacher(@ModelAttribute("teacher") @Valid Teacher teacher){
        service.save(teacher);
        return "redirect:/teacher";
    }

    @GetMapping(path = "/update/{id}")
    public String viewUpdateTeacher(@PathVariable("id") Long id, Model model){
        Teacher teacher = service.getEntity(id);
        model.addAttribute("teacher", teacher);
        return "update_teacher";
    }

    @PostMapping(path = "/update")
    public String update(@ModelAttribute("teacher") @Valid Teacher teacher){
        service.update(teacher);
        return "redirect:/teacher";
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteTeacher(@PathVariable("id")Long id){
        service.delete(id);
        return "redirect:/teacher";
    }
}
