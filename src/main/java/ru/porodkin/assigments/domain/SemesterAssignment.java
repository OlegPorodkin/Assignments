package ru.porodkin.assigments.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@NamedEntityGraph(name = "Assignment.all", includeAllAttributes = true)
@Entity
@Table(name = "Assignments")
public class SemesterAssignment extends IdentificationEntity implements Serializable {

    @NotNull
    @Column(length = 100)
    private String title;

    @NotNull
    @OneToOne
    @JoinColumn(name = "teacher_assign", nullable = false)
    private Teacher teacher;

    @Column(name = "type_work")
    @JoinTable(name = "assign_typeofwork")
    @ElementCollection(targetClass = TypeOfWork.class, fetch = FetchType.EAGER)
    private Set<TypeOfWork> typeOfWorkSet = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "assignments_fk")
    private List<Schedule> schedule = new ArrayList<>();

    @NotNull
    @Column(length = 2000)
    private String task;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @JoinTable(name = "resources")
    private byte[] resources;

    @Override
    public String toString() {
        return "SemesterAssignment{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", teacher=" + teacher +
                ", typeOfWorkSet=" + typeOfWorkSet +
                ", schedule=" + schedule +
                ", task='" + task + '\'' +
                ", resources=[" + Arrays.toString(resources) + ']' +
                '}';
    }
}
