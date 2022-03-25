package com.maint.manager.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "maint")
public class Maint {

    @Id
    @GeneratedValue
    private Long id;
    //TODO
}
