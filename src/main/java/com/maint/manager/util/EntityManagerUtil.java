package com.maint.manager.util;

import com.maint.manager.util.exceptions.runtime.EntityManagerUtilException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.function.Consumer;
import java.util.function.Function;
@Slf4j
public class EntityManagerUtil {

    private final EntityManagerFactory entityManagerFactory;

    private static final String TRANSACTION_ROLLED_BACK = "Transaction is rolled back !!";

    public EntityManagerUtil(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void createQuery(Consumer<EntityManager> entityManagerConsumer) {
        final var entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManagerConsumer.accept(entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new EntityManagerUtilException(TRANSACTION_ROLLED_BACK, e);
        } finally {
            entityManager.close();
        }
    }

    public <T> T createQueryAndReturnObject(Function<EntityManager, T> entityManagerFunction) {
        final var entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            T result = entityManagerFunction.apply(entityManager);
            entityManager.getTransaction().commit();
            return result;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new EntityManagerUtilException(TRANSACTION_ROLLED_BACK, e);
        } finally {
            entityManager.close();
        }
    }

    public <T> T createReadOnlyQueryAndReturnObject(Function<EntityManager, T> entityManagerConsumer) {
        final var entityManager = entityManagerFactory.createEntityManager();
        entityManager.unwrap(Session.class).setDefaultReadOnly(true);
        entityManager.getTransaction().begin();
        try {
            T result = entityManagerConsumer.apply(entityManager);
            entityManager.getTransaction().commit();
            return result;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new EntityManagerUtilException(TRANSACTION_ROLLED_BACK, e);
        } finally {
            entityManager.close();
        }
    }
}
