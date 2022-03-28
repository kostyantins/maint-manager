package com.maint.manager.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "capability")
public class Capability {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "capabilety_name")
    private String capabilityName;

    @OneToOne
    @JoinColumn(name = "maint_capability_id")
    private Maint maint;
}
