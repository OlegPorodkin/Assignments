package ru.porodkin.assigments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.porodkin.assigments.domain.Teacher;
import ru.porodkin.assigments.service.AbstractService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("teacher")
public class TeacherController extends AbstractController<Teacher> {

    @Autowired
    public TeacherController(AbstractService<Teacher> service) {
        super(service);
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> all = service.getAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("{id}")
    public ResponseEntity<Teacher> getOne(@PathVariable Long id) {
        Teacher teacher;
        try {
            teacher = service.getEntity(id).orElseThrow(() -> new EntityNotFoundException("this id: " + id + " not found!"));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(new HashMap<String, String>() {{
                put("id:" + id, "Teacher not found!");
            }}, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(teacher);
    }

    @PostMapping("/add")
    public ResponseEntity<List<Teacher>> saveTeacher(@RequestBody @Valid Teacher teacher) {
        service.save(teacher);
        List<Teacher> all = service.getAll();
        return ResponseEntity.ok(all);
    }

    @PutMapping("{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
        Teacher updateTeacher;

        try {
            updateTeacher = service.update(id, teacher).orElseThrow(() -> new EntityNotFoundException("this id: " + id + " not found!"));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(new HashMap<String, String>() {{
                put("id:" + id, "Teacher not found!");
            }}, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(updateTeacher);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Teacher> deleteTeacher(@PathVariable Long id) {
        return service.delete(id) ?
                ResponseEntity.ok().build() :
                new ResponseEntity(new HashMap<String, String>() {{
                    put("id:" + id, "Teacher not found!");
                }}, HttpStatus.BAD_REQUEST);
    }
}
