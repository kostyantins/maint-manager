package persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import javax.persistence.EntityManagerFactory;

import static javax.persistence.Persistence.createEntityManagerFactory;

public class BasePersistenceTest {

    protected static EntityManagerFactory entityManagerFactory;

    @BeforeAll
    public static void setup() {
        entityManagerFactory = createEntityManagerFactory("MaintManagerDB_H2");
    }

    @AfterAll
    static void destroy() {
        entityManagerFactory.close();
    }
}
