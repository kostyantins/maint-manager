package com.maint.manager.persistence.dao;

import com.maint.manager.persistence.entities.Priorities;
import com.maint.manager.util.EntityManagerUtil;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

public class PrioritiesDao implements Dao<Priorities> {

    private final EntityManagerUtil entityManagerUtil;

    private final static String PRIORITY_SHOULD_NOT_BE_NULL = "Priority should not be null !!";

    public PrioritiesDao(EntityManagerUtil entityManagerUtil) {
        this.entityManagerUtil = entityManagerUtil;
    }

    @Override
    public Optional<Priorities> findById(long id) {
        return ofNullable(entityManagerUtil.createReadOnlyQueryAndReturnObject(
                entityManager -> entityManager.find(Priorities.class, id)));
    }

    @Override
    public List<Priorities> findAll() {
        return entityManagerUtil.createReadOnlyQueryAndReturnObject(
                entityManager -> entityManager.createQuery("select p from Priorities p").getResultList());
    }

    @Override
    public void save(Priorities priority) {
        requireNonNull(priority, PRIORITY_SHOULD_NOT_BE_NULL);
        entityManagerUtil.createQuery(entityManager -> entityManager.persist(priority));
    }

    @Override
    public void remove(Priorities priority) {
        requireNonNull(priority, PRIORITY_SHOULD_NOT_BE_NULL);
        entityManagerUtil.createQuery(entityManager -> {
            final var persistedPriority = entityManager.merge(priority);
            entityManager.remove(persistedPriority);
        });
    }
}
