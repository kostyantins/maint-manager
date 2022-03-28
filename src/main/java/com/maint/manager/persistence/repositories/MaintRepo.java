package com.maint.manager.persistence.repositories;

import com.maint.manager.persistence.entities.Maint;

import static java.lang.String.format;

public class MaintRepo extends BaseRepo {

    public static Maint createMaintModel() {
        return Maint.builder()
                .maintIdentifier(format("MAINT-%s", FAKER.number().digits(5)))
                .capabilityId(FAKER.number().numberBetween(1L, 9L))
                .createdData(CURRENT_DATA)
                .dueData(CURRENT_DATA.plusMonths(1))
                .solvePriorityId(FAKER.number().numberBetween(1, 4))
                .estimate(FAKER.number().numberBetween(1, 4))
                .client("MCB")
                .build();
    }
}
