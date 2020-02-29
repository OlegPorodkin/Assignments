package ru.porodkin.assigments.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
public class Schedule extends IdentificationEntity implements Serializable {

    @Column(name = "begin_time")
    @Temporal(TemporalType.TIME)
    private Date beginTime;

    @Column(name = "end_tame")
    @Temporal(TemporalType.TIME)
    private Date endTime;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", date=" + date +
                '}';
    }
}
