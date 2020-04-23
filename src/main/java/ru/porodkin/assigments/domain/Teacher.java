package ru.porodkin.assigments.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter @Setter
@Entity
@EqualsAndHashCode(callSuper = true, exclude = {"id"})
@Proxy(lazy = false)
public class Teacher extends IdentificationEntity implements Serializable {

    @NotNull
    @Column(length = 256)
    private String fullName;
    private String department;
    private String contacts;

    @Column(length = 500)
    private String info;

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", department='" + department + '\'' +
                ", contacts='" + contacts + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
