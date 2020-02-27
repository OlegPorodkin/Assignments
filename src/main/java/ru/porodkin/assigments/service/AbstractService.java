package ru.porodkin.assigments.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AbstractService<T> {

    List<T> getAll();
    T getEntity(Long ID);
    T update(T entity);
    T save(T entity);
    void delete(Long ID);
}
