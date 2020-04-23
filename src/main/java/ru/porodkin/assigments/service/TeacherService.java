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
    public Optional<Teacher> getEntity(Long ID) {
        return repository.findById(ID);
    }

    @Override
    public Optional<Teacher> update(Long id, Teacher teacher) {

        Optional<Teacher> updateTeacher = repository.findById(id);

        if (!updateTeacher.isEmpty()) {
            updateTeacher.map(ut -> {
                ut.setId(id);
                ut.setFullName(teacher.getFullName());
                ut.setDepartment(teacher.getDepartment());
                ut.setContacts(teacher.getContacts());
                ut.setInfo(teacher.getInfo());
                return ut;
            });
        } else {
            return updateTeacher;
        }

        Teacher save = repository.save(updateTeacher.get());

        return Optional.of(save);
    }

    @Override
    public Optional<Teacher> save(Teacher entity) {
        Teacher save = repository.save(entity);
        return Optional.of(save);
    }

    @Override
    public Boolean delete(Long ID) {
        Optional<Teacher> deleteTeacher = repository.findById(ID);
        if (deleteTeacher.isEmpty()) {
            return false;
        } else {
            repository.delete(deleteTeacher.get());
            return true;
        }
    }
}
