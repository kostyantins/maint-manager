package com.maint.manager.persistence.dao;

import com.maint.manager.persistence.entities.Maint;
import com.maint.manager.persistence.entities.MaintComments;
import com.maint.manager.util.EntityManagerUtil;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

public class MaintDao implements Dao<Maint> {

    private final EntityManagerUtil entityManagerUtil;

    private final static String MAINT_SHOULD_NOT_BE_NULL = "Maint should not be null !!";

    public MaintDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerUtil = new EntityManagerUtil(entityManagerFactory);
    }

    @Override
    public Optional<Maint> findById(long id) {
        return ofNullable(
                entityManagerUtil.createReadOnlyQueryAndReturnObject(
                        entityManager -> entityManager.find(Maint.class, id)));
    }

    @Override
    public List<Maint> findAll() {
        return entityManagerUtil.createReadOnlyQueryAndReturnObject(
                entityManager -> entityManager.createQuery("select m from Maint m").getResultList());
    }

    @Override
    public void save(Maint maint) {
        requireNonNull(maint, MAINT_SHOULD_NOT_BE_NULL);
        entityManagerUtil.createQuery(entityManager -> entityManager.persist(maint));
    }

    @Override
    public void remove(Maint maint) {
        requireNonNull(maint, MAINT_SHOULD_NOT_BE_NULL);
        entityManagerUtil.createQuery(entityManager -> {
            final var persistedMaint = entityManager.merge(maint);
            entityManager.remove(persistedMaint);
        });
    }

    public void addComment(long maintId, String comment) {
        entityManagerUtil.createQuery(entityManager -> {
            final var maintReference = entityManager.getReference(Maint.class, maintId);
            final var maintComment = new MaintComments(comment, maintReference);
            entityManager.persist(maintComment);
        });
    }
}
