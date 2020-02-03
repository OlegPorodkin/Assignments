package ru.porodkin.assigments.domain;

public enum TypeOfWork {
    TEST("Контрольная работа"),
    COURSE_WORK("Курсовая работа"),
    COURSE_PROJECT("Курсовой проект"),
    EXAM("Экзамен"),
    NOT_DEFINED("Не известно");

    String value;

    TypeOfWork(String value) {
        this.value = value;
    }
}
