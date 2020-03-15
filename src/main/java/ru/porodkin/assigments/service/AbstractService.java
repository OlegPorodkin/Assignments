package ru.porodkin.assigments.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface AbstractService<T> {

    List<T> getAll();
    Optional<T> getEntity(Long ID);
    Optional<T> update(Long id, T entity);
    Optional<T> save(T entity);
    Boolean delete(Long ID);
}
