package ru.porodkin.assigments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.porodkin.assigments.domain.Schedule;
import ru.porodkin.assigments.repository.ScheduleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ScheduleService implements AbstractService<Schedule>{

    private ScheduleRepository repository;

    @Autowired
    public ScheduleService(ScheduleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Schedule> getAll() {
        return repository.findAll();
    }

    @Override
    public Schedule getEntity(Long ID) {
        return repository.getOne(ID);
    }

    @Override
    public Schedule update(Schedule entity) {
        return repository.save(entity);
    }

    @Override
    public Schedule save(Schedule entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Long ID) {
        Optional<Schedule> byId = repository.findById(ID);
        repository.delete(byId.get());
    }
}
