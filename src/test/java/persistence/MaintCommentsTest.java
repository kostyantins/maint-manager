package persistence;

import com.maint.manager.persistence.dao.MaintDao;
import com.maint.manager.persistence.entities.Maint;
import com.maint.manager.persistence.entities.MaintComments;
import org.junit.jupiter.api.Test;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

import static com.maint.manager.persistence.repositories.MaintCommentsRepo.createMaintCommentModel;
import static com.maint.manager.persistence.repositories.MaintRepo.createMaintModel;
import static java.lang.reflect.Modifier.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;

public class MaintCommentsTest extends BasePersistenceTest {

    private final MaintDao maintDao = new MaintDao(entityManagerFactory);

    @Test
    public void testCommentsListIsInitialized() {
        final var maint = new Maint();
        final var comments = maint.getComments();

        assertThat(comments).isNotNull();
    }

    @Test
    public void testCommentsSetterIsPrivate() throws NoSuchMethodException {
        final var setComments = Maint.class.getDeclaredMethod("setComments", List.class);

        assertThat(setComments.getModifiers()).isEqualTo(PRIVATE);
    }

    @Test
    public void testCommentTextIsNotNull() throws NoSuchFieldException {
        final var commentText = MaintComments.class.getDeclaredField("commentText");
        final var constraint = commentText.getAnnotation(Column.class);

        assertThat(constraint.nullable()).isFalse();
    }

    @Test
    public void testCreatedDataIsNotNull() throws NoSuchFieldException {
        final var commentText = MaintComments.class.getDeclaredField("createdData");
        final var constraint = commentText.getAnnotation(Column.class);

        assertThat(constraint.nullable()).isFalse();
    }

    @Test
    public void testCascadeTypeAllIsEnabledForComments() throws NoSuchFieldException {
        final var comments = Maint.class.getDeclaredField("comments");
        final var oneToMany = comments.getAnnotation(OneToMany.class);
        CascadeType[] cashType = {CascadeType.ALL};

        assertThat(oneToMany.cascade()).isEqualTo(cashType);
    }

    @Test
    public void testOrphanRemovalIsEnabledForComments() throws NoSuchFieldException {
        final var comments = Maint.class.getDeclaredField("comments");
        final var oneToMany = comments.getAnnotation(OneToMany.class);

        assertThat(oneToMany.orphanRemoval()).isTrue();
    }

    @Test
    public void testSaveMaintOnly() {
        final var maint = createMaintModel();

        maintDao.save(maint);

        assertThat(maint.getId()).isNotNull();
    }
}
