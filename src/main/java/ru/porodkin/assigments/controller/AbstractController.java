package ru.porodkin.assigments.controller;

import ru.porodkin.assigments.service.AbstractService;

public abstract class AbstractController<T> {

    protected AbstractService<T> service;

    public AbstractController(AbstractService<T> service) {
        this.service = service;
    }
}
