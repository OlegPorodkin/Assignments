package ru.porodkin.assigments.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Getter @Setter
@Entity
public class Teacher extends IdentificationEntity {

    @NotNull
    @Column(length = 50)
    private String firstName;

    @NotNull
    @Column(length = 50)
    private String lastName;

    @NotNull
    @Column(length = 50)
    private String middleName;
    private String department;
    private String contacts;

    @Column(length = 500)
    private String info;

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", department='" + department + '\'' +
                ", contacts='" + contacts + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
