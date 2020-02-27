package ru.porodkin.assigments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.porodkin.assigments.domain.Teacher;
import ru.porodkin.assigments.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService implements AbstractService<Teacher> {

    private TeacherRepository repository;

    @Autowired
    public TeacherService(TeacherRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Teacher> getAll() {
        return repository.findAll();
    }

    @Override
    public Teacher getEntity(Long ID) {
        return repository.getOne(ID);
    }

    @Override
    public Teacher update(Teacher teacher) {
        return repository.save(teacher);
    }

    @Override
    public Teacher save(Teacher entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Long ID) {
        Optional<Teacher> byId = repository.findById(ID);
        repository.delete(byId.get());
    }
}
