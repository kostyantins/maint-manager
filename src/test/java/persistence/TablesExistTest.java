package persistence;

import com.maint.manager.persistence.entities.Capability;
import com.maint.manager.persistence.entities.Maint;
import com.maint.manager.persistence.entities.MaintComments;
import com.maint.manager.persistence.entities.Priorities;
import org.junit.jupiter.api.Test;

import javax.persistence.Table;

import static org.assertj.core.api.Assertions.assertThat;

public class TablesExistTest {

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
}
