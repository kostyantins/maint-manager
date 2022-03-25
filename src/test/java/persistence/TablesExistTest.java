package persistence;

import com.maint.manager.util.EntityManagerUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;

import static javax.persistence.Persistence.createEntityManagerFactory;

public class TablesExistTest {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManagerUtil entityManager;

    @BeforeAll
    public static void setup() {
        entityManagerFactory = createEntityManagerFactory("MaintManagerDB_H2");
        entityManager = new EntityManagerUtil(entityManagerFactory);
    }

    @AfterAll
    static void destroy() {
        entityManagerFactory.close();
    }

    @Test
    public void testMandatoryTablesExist() {
        //TODO
    }
}
