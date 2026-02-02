package plan;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void markAsDone_setsIsDoneTrue() {
        Task t = new Todo("read book");
        t.markAsDone();
        assertEquals("X", t.getStatusIcon());
    }

    @Test
    public void markAsNotDone_resetsIsDoneFalse() {
        Task t = new Todo("read book");
        t.markAsDone();
        t.markAsNotDone();
        assertEquals(" ", t.getStatusIcon());
    }
}
