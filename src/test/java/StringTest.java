import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class StringTest {

    private String str;

    @BeforeAll
    static void beforeAll() {
        System.out.printf("Establishing connection to database%n");
    }

    @AfterAll
    static void afterAll() {
        System.out.printf("Disconnecting from database%n");
    }

    @BeforeEach
    void beforeEach(TestInfo info) {
        System.out.printf("Initialize test data for %s%n", info.getDisplayName());
    }

    @AfterEach
    void afterEach(TestInfo info) {
        System.out.printf("Cleaning up test data for %s%n", info.getDisplayName());
    }

    @Test
    void lengthBasic() {
        int actualLength = "ABCD".length();
        int expectedLength = 4;

        assertEquals(actualLength, actualLength);
    }

    @DisplayName("String.length when string is null.")
    @Test
    void lengthException() {
        String str = null;
        assertThrows(NullPointerException.class,
                () -> {
                    str.length();
                }
        );
    }

    @Test
    void toUpperCaseBasic() {
        String letters = "abcd";
        String result = letters.toUpperCase();
        assertEquals("ABCD", result);
        assertNotNull(result);
    }

    @Test
    void containsBasic() {
        String letters = "abcdefgh";
        boolean result = letters.contains("ijk");
        assertFalse(result);
    }

    @RepeatedTest(10)
    void containsBasicRepeated() {
        assertFalse("abcdefgh".contains("ijk"));
    }


    @Test
    void splitBasic() {
        String letters = "abc def ghi";
        String[] actualResult = letters.split(" ");
        String[] expectedResult = {"abc", "def", "ghi"};
        assertArrayEquals(expectedResult, actualResult);

    }

    @ParameterizedTest
    @ValueSource(strings = {"ABCD", "ABC", "A", "DEF"})
    void lengthGreaterThanZeroUsingParameterizedTest(String str) {
        assertTrue(str.length() > 0);
    }

    @ParameterizedTest(name = "{0} to UpperCase is {1}")
    @CsvSource(value = {"abcd, ABCD", "abc, ABC", "'',''", "abcdefg, ABCDEFG"})
    void uppercase(String word, String capitalizedWord) {
        assertEquals(capitalizedWord, word.toUpperCase());
    }

    @ParameterizedTest(name = "{0} length is {1}")
    @CsvSource(value = {"abcd, 4", "abc, 3", "'',0", "abcdefg, 7"})
    void length(String word, int expectedLength) {
        assertEquals(expectedLength, word.length());
    }

    @Disabled
    @Test
    void performanceTest() {
        assertTimeout(Duration.ofSeconds(5), () -> {
                    for (int i = 0; i <= 1000000; i++) {
                        int j = i;
                        System.out.println(j);
                    }
                }
        );
    }

    @Nested
    @DisplayName("For an empty string.")
    class EmptyStringTests {

        @BeforeEach
        void setToEmpty() {
            str = "";
        }

        @Test
        @DisplayName("length should be zero")
        void lengthIsZero() {
            assertEquals(0, str.length());
        }

        @Test
        @DisplayName("upper case is empty")
        void upperCaseIsEmpty() {
            assertEquals("", str.toUpperCase());
        }
    }

    @Nested
    @DisplayName("For large strings.")
    class LargeStringTests {

        @BeforeEach
        void setToALargeString() {
            str = "qwertyuiopasdfghjklzxcvbnm";
        }

        @Test
        @DisplayName("String has more than ten characters.")
        void lengthIsMoreThanTen() {
            assertTrue(str.length() > 10);
        }

        @Test
        @DisplayName("String is not null")
        void stringIsNotNull() {
            assertNotNull(str);
        }
    }
}
