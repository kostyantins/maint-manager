package com.maint.manager.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "maint_comments")
public class MaintComments {

    @Id
    private Long id;
    //TODO
}
