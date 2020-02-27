package ru.porodkin.assigments.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.porodkin.assigments.domain.SemesterAssignment;
import ru.porodkin.assigments.repository.AssignmentRepository;

import java.util.List;

@Transactional
@Service
public class AssignmentsService implements AbstractService<SemesterAssignment>{

    private AssignmentRepository repository;

    public AssignmentsService(AssignmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SemesterAssignment> getAll() {
        List<SemesterAssignment> all = repository.findAll();
        return all;
    }

    @Override
    public SemesterAssignment getEntity(Long ID) {
        return null;
    }

    @Override
    public SemesterAssignment update(SemesterAssignment assignment) {
        return assignment;
    }

    @Override
    public SemesterAssignment save(SemesterAssignment entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Long ID) {

    }

}
