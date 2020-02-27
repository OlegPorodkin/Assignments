package ru.porodkin.assigments.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.porodkin.assigments.domain.SemesterAssignment;
import ru.porodkin.assigments.service.AbstractService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/assignments")
public class AssignmentsController extends AbstractController<SemesterAssignment> {

    public AssignmentsController(AbstractService<SemesterAssignment> service) {
        super(service);
    }

    @GetMapping()
    public String getAll(Model model){
        List<SemesterAssignment> allAssignment = service.getAll();
        model.addAttribute("assignments", allAssignment);
        return "/main/resources/templates/assignment/assignment.html";
    }

    @GetMapping(params = "id")
    public String getEntity(@RequestParam(value = "id", defaultValue = "0") long id, Model model){
        SemesterAssignment assignment = service.getEntity(id);
        model.addAttribute("assignment", assignment);
        return "/main/resources/templates/assignment/assignment.html";
    }

    @GetMapping(path = "/add")
    public String showAddSaveEntity(Model model){
        SemesterAssignment assignment = new SemesterAssignment();
        model.addAttribute("assignment", assignment);
        return "/main/resources/templates/assignment/add_assignment.html";
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
