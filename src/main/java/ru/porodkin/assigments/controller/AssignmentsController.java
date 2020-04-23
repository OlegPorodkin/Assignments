package ru.porodkin.assigments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.porodkin.assigments.domain.SemesterAssignment;
import ru.porodkin.assigments.repository.TeacherRepository;
import ru.porodkin.assigments.service.AssignmentsService;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@RestController
@RequestMapping(path = "/assignments")
public class AssignmentsController extends AbstractController<SemesterAssignment> {

    private final TeacherRepository teacherRepository;

    @Autowired
    public AssignmentsController(AssignmentsService service, TeacherRepository teacherRepository) {
        super(service);
        this.teacherRepository = teacherRepository;
    }

    @GetMapping()
    public ResponseEntity<List<SemesterAssignment>> getAll() {
        List<SemesterAssignment> allAssignment = service.getAll();

        return ResponseEntity.ok(allAssignment);
    }

    @GetMapping("{id}")
    public ResponseEntity<SemesterAssignment> getOne(@PathVariable("id") Long id) {
        SemesterAssignment assignment;

        try {
            assignment = service.getEntity(id).orElseThrow(() -> new EntityNotFoundException("this id: " + id + " not found!"));
        } catch (Exception e) {
            return new ResponseEntity(new HashMap<String, String>() {{
                put("id:" + id, "Assignment not found!");
            }}, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(assignment);
    }

    @PostMapping("/add")
    public ResponseEntity<SemesterAssignment> saveAssignment(@RequestBody SemesterAssignment assignment) {
        System.out.println(assignment);
//        assignment.setTeacher(teacherRepository.findById(1L).get());
//        Optional<SemesterAssignment> save = service.save(assignment);

//        List<SemesterAssignment> all = service.getAll();
        return ResponseEntity.ok(/*save.get()*/).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<SemesterAssignment> updateAssignment(@PathVariable("id") Long id, @RequestBody SemesterAssignment assignment) {
        SemesterAssignment updateAssignment;

        try {
            updateAssignment = service.update(id, assignment).orElseThrow(() -> new EntityNotFoundException("this id: " + id + " not found!"));
        } catch (Exception e) {
            return new ResponseEntity(new HashMap<String, String>() {{
                put("id:" + id, "Assignment not found!");
            }}, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(updateAssignment);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<SemesterAssignment> deleteAssignment(@PathVariable("id") Long id) {

        return service.delete(id) ?
                ResponseEntity.ok().build() :
                new ResponseEntity(new HashMap<String, String>() {{ put("id:" + id, "Assignment not found!"); }}, HttpStatus.BAD_REQUEST);
    }
}
