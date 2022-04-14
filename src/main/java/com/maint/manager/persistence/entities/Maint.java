package com.maint.manager.persistence.entities;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString()
@Builder
@Entity
@Table(name = "maint")
public class Maint {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "maint_identifier", unique = true)
    private String maintIdentifier;

    @Column(name = "capability_id", nullable = false)
    private Long capabilityId;

    @Column(name = "appeared_data", nullable = false)
    private LocalDate createdData;

    @Column(name = "due_data", nullable = false)
    private LocalDate dueData;

    @Column(name = "sovle_priority_id", nullable = false)
    private Integer solvePriorityId;

    @Column
    @ColumnDefault("'N/A'")
    private Integer estimate;

    @Column(nullable = false)
    private String client;

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "maint", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
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
