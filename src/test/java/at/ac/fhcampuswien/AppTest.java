package at.ac.fhcampuswien;

import org.junit.jupiter.api.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

@Timeout(2)
class AppTest {

    private PrintStream originalOut;
    private InputStream originalIn;
    private ByteArrayOutputStream bos;
    private PrintStream ps;

    @BeforeAll
    public static void init() {
        System.out.println("Testing Exercise2");
    }

    @AfterAll
    public static void finish() {
        System.out.println("Finished Testing Exercise2");
    }

    @BeforeEach
    public void setupStreams() throws IOException {
        originalOut = System.out;
        originalIn = System.in;

        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis = new PipedInputStream(pos);
        System.setIn(pis);
        ps = new PrintStream(pos, true);
    }

    @AfterEach
    public void tearDownStreams() {
        // undo the binding in System
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void oneMonthCalendar1() {
        String output = "                1  2 " + System.lineSeparator() +
                " 3  4  5  6  7  8  9 " + System.lineSeparator() +
                "10 11 12 13 14 15 16 " + System.lineSeparator() +
                "17 18 19 20 21 22 23 " + System.lineSeparator() +
                "24 25 26 27 28 29 30 " + System.lineSeparator();

        // action
        App.oneMonthCalendar(30, 6);

        // assertion
        assertEquals(output, bos.toString());
    }

    @Test
    public void oneMonthCalendar2() {
        String output = "    1  2  3  4  5  6 " + System.lineSeparator() +
                " 7  8  9 10 11 12 13 " + System.lineSeparator() +
                "14 15 16 17 18 19 20 " + System.lineSeparator() +
                "21 22 23 24 25 26 27 " + System.lineSeparator() +
                "28 " + System.lineSeparator();

        // action
        App.oneMonthCalendar(28, 2);

        // assertion
        assertEquals(output, bos.toString());
    }

    @Test
    public void lcg() {
        long[] test = new long[]{12345,
                1406932606,
                654583775,
                1449466924,
                229283573,
                1109335178,
                1051550459,
                1293799192,
                794471793,
                551188310};

        // action
        long[] output = App.lcg(0);

        // assertion
        Assertions.assertArrayEquals(test, output);
    }

    @Test
    public void guessingGame1() {
        ps.println(50);
        ps.println(15);
        ps.println(49);


        String output = "Guess number 1: " +
                "The number AI picked is lower than your guess." + System.lineSeparator() +
                "Guess number 2: " +
                "The number AI picked is higher than your guess." + System.lineSeparator() +
                "Guess number 3: " +
                "You won wisenheimer!" + System.lineSeparator();

        // action
        App.guessingGame(49);

        // assertion
        assertEquals(output, bos.toString());
    }

    @Test
    public void guessingGame2() {
        ps.println(50);
        ps.println(50);
        ps.println(50);
        ps.println(50);
        ps.println(50);
        ps.println(50);
        ps.println(50);
        ps.println(50);
        ps.println(50);
        ps.println(50);

        String output = "Guess number 1: " +
                "The number AI picked is lower than your guess." + System.lineSeparator() +
                "Guess number 2: " +
                "The number AI picked is lower than your guess." + System.lineSeparator() +
                "Guess number 3: " +
                "The number AI picked is lower than your guess." + System.lineSeparator() +
                "Guess number 4: " +
                "The number AI picked is lower than your guess." + System.lineSeparator() +
                "Guess number 5: " +
                "The number AI picked is lower than your guess." + System.lineSeparator() +
                "Guess number 6: " +
                "The number AI picked is lower than your guess." + System.lineSeparator() +
                "Guess number 7: " +
                "The number AI picked is lower than your guess." + System.lineSeparator() +
                "Guess number 8: " +
                "The number AI picked is lower than your guess." + System.lineSeparator() +
                "Guess number 9: " +
                "The number AI picked is lower than your guess." + System.lineSeparator() +
                "Guess number 10: " +
                "You lost! Have you ever heard of divide & conquer?" + System.lineSeparator();

        // action
        App.guessingGame(49);

        // assertion
        assertEquals(output, bos.toString());
    }

    @Test
    public void randomNumberBetweenOneAndHundred() {
        int test = 0;

        // assertion
        for (int i = 0; i < 100; i++) {
            test = App.randomNumberBetweenOneAndHundred();
            assertTrue(1 <= test && test <= 100);
        }
    }

    @Test
    public void swapArrays1() {
        int[] array1 = new int[]{1, 2, 3, 4, 5, 6};
        int[] array2 = new int[]{100, 202, 30, 14, 15, 16};

        // action
        boolean result = App.swapArrays(array1, array2);

        // assertion
        assertTrue(result);
        assertArrayEquals(array1, new int[]{100, 202, 30, 14, 15, 16});
        assertArrayEquals(array2, new int[]{1, 2, 3, 4, 5, 6});
    }

    @Test
    public void swapArrays2() {
        int[] array1 = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] array2 = new int[]{100, 202, 30, 14, 15, 16};

        // action
        boolean result = App.swapArrays(array1, array2);

        // assertion
        assertFalse(result);
        assertArrayEquals(array2, new int[]{100, 202, 30, 14, 15, 16});
        assertArrayEquals(array1, new int[]{1, 2, 3, 4, 5, 6, 7});
    }

    @Test
    public void camelCase1() {
        String test = "MyNameIsntAlice";

        // action
        String result = App.camelCase("my name isn't Alice!");

        // assertion
        assertEquals(test, result);
    }

    @Test
    public void camelCase2() {
        String test = "ButANoisyNoiseAnnoysAnOysterMore";

        // action
        String result = App.camelCase("but a noisY noise annoYs an oYster more.");

        // assertion
        assertEquals(test, result);
    }

    @Test
    public void checkDigit1() {
        int test = 2;

        // action
        int result = App.checkDigit(new int[]{4, 0, 4, 4, 4, 8, 2, 9, 3});

        // assertion
        assertEquals(test, result);
    }

    @Test
    public void checkDigit2() {
        int test = 5;

        // action
        int result = App.checkDigit(new int[]{3, 9, 1, 5, 8});

        // assertion
        assertEquals(test, result);
    }

    @Test
    public void checkDigit3() {
        int test = 0;

        // action
        int result = App.checkDigit(new int[]{0, 0, 4, 4, 4, 8, 2, 9, 3});

        // assertion
        assertEquals(test, result);
    }
}