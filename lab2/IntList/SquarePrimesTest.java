package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesSimple1() {
        IntList lst = IntList.of(2, 3, 5, 7, 11);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4 -> 9 -> 25 -> 49 -> 121", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesSimple3() {
        IntList lst = IntList.of(0);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("0", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesSimple4() {
        IntList lst = null;
        boolean changed = IntListExercises.squarePrimes(lst);
        assertNull(lst);
        assertFalse(changed);
    }

    @Test
    public void testSquarePrimesSimple5() {
        IntList lst = IntList.of(0, 0, 0, 0, 0);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("0 -> 0 -> 0 -> 0 -> 0", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesSimple6() {
        IntList lst = IntList.of(1000, 1001, 1002, 1003, 1009);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("1000 -> 1001 -> 1002 -> 1003 -> 1018081", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesSimple7() {
        IntList lst = IntList.of(2, -3, 5, -7, 11);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4 -> -3 -> 25 -> -7 -> 121", lst.toString());
        assertTrue(changed);
    }
}
