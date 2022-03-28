package com.maint.manager.persistence.repositories;

import com.maint.manager.persistence.entities.MaintComments;

import static com.maint.manager.persistence.repositories.BaseRepo.CURRENT_DATA;
import static com.maint.manager.persistence.repositories.BaseRepo.FAKER;

public class MaintCommentsRepo {

    public static MaintComments createMaintCommentModel() {
        return MaintComments.builder()
                .commentText(FAKER.gameOfThrones().dragon())
                .createdData(CURRENT_DATA)
                .build();
    }
}
