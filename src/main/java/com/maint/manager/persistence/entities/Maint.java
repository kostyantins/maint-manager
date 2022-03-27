package com.maint.manager.persistence.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "maint")
public class Maint {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column(name = "maint_identifier", unique = true)
    private String maintIdentifier;

    @Column(name = "capability_id", nullable = false)
    private Long capabilityId;

    @Column(name = "appeared_data", nullable = false)
    private LocalDateTime createdData;

    @Column(name = "due_data", nullable = false)
    private LocalDateTime dueData;

    @Column(name = "sovle_priority_id", nullable = false)
    private Integer solvePriorityId;

    @Column
    private Integer estimate;

    @Column(nullable = false)
    private String client;

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "maint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaintComments> comments = new ArrayList<>();

    public void addComment(MaintComments comment) {
        comments.add(comment);
        comment.setMaint(this);
    }

    public void removeComment(MaintComments comment) {
        comments.remove(comment);
        comment.setMaint(null);
    }
}
