package com.maint.manager.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "priority")
public class Priorities {

    @Id
    @Column
    private Long id;

    @Column(name = "priority_name")
    private String priorityName;

    @OneToOne
    @JoinColumn(name = "maint_sovle_priority_id")
    private Maint maint;
}
