package com.maint.manager.persistence.dao;

import com.maint.manager.persistence.entities.MaintComments;
import com.maint.manager.util.EntityManagerUtil;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

public class MaintCommentDao implements Dao<MaintComments> {

    private final EntityManagerUtil entityManagerUtil;

    private final static String MAINT_COMMENT_SHOULD_NOT_BE_NULL = "Maint comment should not be null !!";

    public MaintCommentDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerUtil = new EntityManagerUtil(entityManagerFactory);
    }

    @Override
    public Optional<MaintComments> findById(long id) {
        return ofNullable(entityManagerUtil.createReadOnlyQueryAndReturnObject(
                entityManager -> entityManager.find(MaintComments.class, id)));
    }

    @Override
    public List<MaintComments> findAll() {
        return entityManagerUtil.createReadOnlyQueryAndReturnObject(
                entityManager -> entityManager.createQuery("select mc from MaintComments mc").getResultList());
    }

    @Override
    public void save(MaintComments maintComment) {
        requireNonNull(maintComment, MAINT_COMMENT_SHOULD_NOT_BE_NULL);
        entityManagerUtil.createQuery(entityManager -> entityManager.persist(maintComment));
    }

    @Override
    public void remove(MaintComments maintComment) {
        requireNonNull(maintComment, MAINT_COMMENT_SHOULD_NOT_BE_NULL);
        entityManagerUtil.createQuery(entityManager -> {
            final var persistedMaintComment = entityManager.merge(maintComment);
            entityManager.remove(persistedMaintComment);
        });
    }
}
