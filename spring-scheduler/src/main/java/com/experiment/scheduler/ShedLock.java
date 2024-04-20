package com.experiment.scheduler;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="shedlock")
public class ShedLock {


    @Column(name = "name")
    @Id
    private String name;

    @Column(name = "lock_until")
    private Timestamp lock_until;

    @Column(name = "locked_at")
    private Timestamp locked_at;

    @Column(name = "locked_by")
    private String locked_by;
}
