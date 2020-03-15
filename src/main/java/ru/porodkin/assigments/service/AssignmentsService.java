package ru.porodkin.assigments.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.porodkin.assigments.domain.SemesterAssignment;
import ru.porodkin.assigments.repository.AssignmentRepository;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class AssignmentsService implements AbstractService<SemesterAssignment>{

    private AssignmentRepository repository;

    public AssignmentsService(AssignmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SemesterAssignment> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<SemesterAssignment> getEntity(Long ID) {
        return repository.findById(ID);
    }

    @Override
    public Optional<SemesterAssignment> update(Long id, SemesterAssignment assignment) {
        Optional<SemesterAssignment> updateAssignment = repository.findById(id);

        if (!updateAssignment.isEmpty()){
            updateAssignment.map(ua -> {
                ua.setTitle(assignment.getTitle());
                ua.setTeacher(assignment.getTeacher());
                ua.setTypeOfWorkSet(assignment.getTypeOfWorkSet());
                ua.setSchedule(assignment.getSchedule());
                ua.setTask(assignment.getTask());
                ua.setResources(assignment.getResources());

                return ua;
            });
        } else {
            return updateAssignment;
        }

        repository.save(updateAssignment.get());

        return updateAssignment;
    }

    @Override
    public Optional<SemesterAssignment> save(SemesterAssignment entity) {

        SemesterAssignment save = repository.save(entity);
        return Optional.of(save);
    }

    @Override
    public Boolean delete(Long ID) {
        Optional<SemesterAssignment> deleteAssignment = repository.findById(ID);
        if (deleteAssignment.isEmpty())
            return false;
        else {
            repository.delete(deleteAssignment.get());
            return true;
        }
    }

}
