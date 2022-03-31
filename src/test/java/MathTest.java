import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MathTest {
    @Test
    void checkMathMin() {
        int lowNo = 1;
        int highNo = 2;

        assertEquals(lowNo, Math.min(lowNo, highNo));

        assertNotEquals(highNo, Math.min(lowNo, highNo));
    }

    @Test
    void checkMathMax() {
        int lowNo = 1;
        int highNo = 2;

        assertEquals(highNo, Math.max(lowNo, highNo));

        assertNotEquals(lowNo, Math.max(lowNo, highNo));
    }
}
