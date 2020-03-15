package ru.porodkin.assigments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.porodkin.assigments.domain.Schedule;
import ru.porodkin.assigments.service.AbstractService;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController extends AbstractController<Schedule> {

    @Autowired
    public ScheduleController(AbstractService<Schedule> service) {
        super(service);
    }

    @GetMapping
    public ResponseEntity<List<Schedule>> getAllSchedule(){
        List<Schedule> all = service.getAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("{id}")
    public ResponseEntity<Schedule> getOne(@PathVariable Long id){
        Schedule schedule;

        try{
            schedule = service.getEntity(id).orElseThrow(() -> new EntityNotFoundException("this id: " + id + " not found!"));
        }catch (Exception e){
            return new ResponseEntity(new HashMap<String, String>() {{
                put("id:" + id, "Schedule not found!");
            }}, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(schedule);
    }

    @PostMapping("/add")
    public ResponseEntity<List<Schedule>> saveSchedule(@RequestBody Schedule schedule){
        service.save(schedule);
        List<Schedule> all = service.getAll();
        return ResponseEntity.ok(all);
    }

    @PutMapping("{id}")
    public ResponseEntity<Schedule> viewUpdateSchedule(@PathVariable("id") Long id, Schedule schedule){
        Schedule updateSchedule;

        try {
            updateSchedule = service.update(id, schedule).orElseThrow(() -> new EntityNotFoundException("this id: " + id + " not found!"));
        } catch (Exception e) {
            return new ResponseEntity(new HashMap<String, String>() {{
                put("id:" + id, "schedule not found!");
            }}, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(updateSchedule);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Schedule> deleteSchedule(@PathVariable("id")Long id){
        return service.delete(id) ?
                ResponseEntity.ok().build() :
                new ResponseEntity(new HashMap<String, String>() {{ put("id:" + id, "Schedule not found!"); }}, HttpStatus.BAD_REQUEST);
    }
}
