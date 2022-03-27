package persistence;

import com.maint.manager.persistence.entities.Capability;
import com.maint.manager.persistence.entities.Maint;
import com.maint.manager.persistence.entities.MaintComments;
import com.maint.manager.persistence.entities.Priorities;
import com.maint.manager.util.EntityManagerUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import static javax.persistence.Persistence.createEntityManagerFactory;
import static org.assertj.core.api.Assertions.assertThat;

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
    void testMaintTableHasCorrectName() {
        final var table = Maint.class.getAnnotation(Table.class);
        final var tableName = table.name();

        assertThat(tableName).isEqualTo("maint");
    }

    @Test
    void testMaintCommentsTableHasCorrectName() {
        final var table = MaintComments.class.getAnnotation(Table.class);
        final var tableName = table.name();

        assertThat(tableName).isEqualTo("maint_comments");
    }

    @Test
    void testCapabilityTableHasCorrectName() {
        final var table = Capability.class.getAnnotation(Table.class);
        final var tableName = table.name();

        assertThat(tableName).isEqualTo("capability");
    }

    @Test
    void testPriorityTableHasCorrectName() {
        final var table = Priorities.class.getAnnotation(Table.class);
        final var tableName = table.name();

        assertThat(tableName).isEqualTo("priority");
    }

    @Test
    void testForeignKeyColumnHasCorrectName() throws NoSuchFieldException {
        final var maint = Maint.class.getDeclaredField("comments");
        final var oneToMany = maint.getAnnotation(OneToMany.class);
        final var foreignKeyColumnName = oneToMany.mappedBy();

        assertThat(foreignKeyColumnName).isEqualTo("maint");
    }

    @Test
    void testCapabilityTableColumnForeignKeyHasCorrectName() throws NoSuchFieldException {
        final var capability = Capability.class.getDeclaredField("maint");
        final var joinColumn = capability.getAnnotation(JoinColumn.class);
        final var foreignKeyColumnName = joinColumn.name();

        assertThat(foreignKeyColumnName).isEqualTo("maint_capability_id");
    }

    @Test
    void testPriorityTableColumnForeignKeyHasCorrectName() throws NoSuchFieldException {
        final var priorities = Priorities.class.getDeclaredField("maint");
        final var joinColumn = priorities.getAnnotation(JoinColumn.class);
        final var foreignKeyColumnName = joinColumn.name();

        assertThat(foreignKeyColumnName).isEqualTo("maint_sovle_priority_id");
    }
}
