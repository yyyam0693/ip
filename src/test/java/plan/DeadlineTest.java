package plan;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @Test
    public void toString_formatsDateCorrectly() {
        Deadline d = new Deadline("return book", "2019-12-02");
        String output = d.toString();

        assertTrue(output.contains("Dec"));
        assertTrue(output.contains("2019"));
        assertTrue(output.contains("return book"));
    }

    @Test
    public void toFileString_savesInIsoFormat() {
        Deadline d = new Deadline("return book", "2019-12-02");
        String fileString = d.toFileString();

        assertEquals("D | 0 | return book | 2019-12-02", fileString);
    }
}
