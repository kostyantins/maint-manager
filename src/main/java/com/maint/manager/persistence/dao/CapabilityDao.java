package com.maint.manager.persistence.dao;

import com.maint.manager.persistence.entities.Capability;
import com.maint.manager.util.EntityManagerUtil;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

public class CapabilityDao implements Dao<Capability> {

    private final EntityManagerUtil entityManagerUtil;

    private final static String CAPABILITY_SHOULD_NOT_BE_NULL = "Capability should not be null !!";

    public CapabilityDao(EntityManagerUtil entityManagerUtil) {
        this.entityManagerUtil = entityManagerUtil;
    }

    @Override
    public Optional<Capability> findById(long id) {
        return ofNullable(entityManagerUtil.createReadOnlyQueryAndReturnObject(
                entityManager -> entityManager.find(Capability.class, id)));
    }

    @Override
    public List<Capability> findAll() {
        return entityManagerUtil.createReadOnlyQueryAndReturnObject(
                entityManager -> entityManager.createQuery("select c from Capability c").getResultList());
    }

    @Override
    public void save(Capability capability) {
        requireNonNull(capability, CAPABILITY_SHOULD_NOT_BE_NULL);
        entityManagerUtil.createQuery(entityManager -> entityManager.persist(capability));
    }

    @Override
    public void remove(Capability capability) {
        requireNonNull(capability, CAPABILITY_SHOULD_NOT_BE_NULL);
        entityManagerUtil.createQuery(entityManager -> {
            final var persistedCapability = entityManager.merge(capability);
            entityManager.remove(persistedCapability);
        });
    }
}
