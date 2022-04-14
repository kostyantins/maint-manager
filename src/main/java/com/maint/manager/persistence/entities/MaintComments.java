package com.maint.manager.persistence.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "maint_comments")
public class MaintComments {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "comment_text", nullable = false)
    private String commentText;

    @Column(name = "created_data", nullable = false)
    private LocalDate createdData;

    @ManyToOne
    @JoinColumn(name = "maint_id")
    private Maint maint;

    public MaintComments(String commentText, Maint maint) {
        this.commentText = commentText;
        this.maint = maint;
        this.createdData = LocalDate.now();
    }
}
