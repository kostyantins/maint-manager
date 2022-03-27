package persistence;

import com.maint.manager.persistence.entities.Capability;
import com.maint.manager.persistence.entities.Maint;
import com.maint.manager.persistence.entities.MaintComments;
import com.maint.manager.persistence.entities.Priorities;
import org.junit.jupiter.api.Test;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import static org.assertj.core.api.Assertions.assertThat;

public class TablesRelationsTest {

    @Test
    void testMaintTableColumnForeignKeyHasCorrectName() throws NoSuchFieldException {
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

    @Test
    void testMaintCommentsTableColumnForeignKeyHasCorrectName() throws NoSuchFieldException {
        final var priorities = MaintComments.class.getDeclaredField("maint");
        final var joinColumn = priorities.getAnnotation(JoinColumn.class);
        final var foreignKeyColumnName = joinColumn.name();

        assertThat(foreignKeyColumnName).isEqualTo("maint_id");
    }
}
