package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
  @Test
  public void testThreeAddThreeRemove() {
    AListNoResizing<Integer> ListA = new AListNoResizing<Integer>();
    BuggyAList<Integer> ListB = new BuggyAList<Integer>();

    for (int i = 4; i < 7; i++) {
      ListA.addLast(i);
      ListB.addLast(i);
    }

    for (int i = 0; i < ListA.size(); i++) {
      ListA.removeLast();
      ListB.removeLast();
      for (int j = 0; j < ListA.size(); j++) {
        assertEquals(ListA.get(j), ListB.get(j));
      }
    }
  }

  @Test
  public void randomizedTest() {
    AListNoResizing<Integer> L = new AListNoResizing<>();
    BuggyAList<Integer> L1 = new BuggyAList<>();
    int N = 5000;
    for (int i = 0; i < N; i += 1) {
      int operationNumber = StdRandom.uniform(0, 4);
      if (operationNumber == 0) {
        // addLast
        int randVal = StdRandom.uniform(0, 100);
        L.addLast(randVal);
        L1.addLast(randVal);
      } else if (operationNumber == 1) {
        // size
        int size = L.size();
        int size1 = L1.size();
        assertEquals(size, size1);
      } else if (operationNumber == 2) {
        // getLast
        if (L.size() == 0)
          continue;
        int lastVal = L.getLast();
        int lastVal1 = L1.getLast();
        assertEquals(lastVal,lastVal1);
      } else if (operationNumber == 3) {
        if (L.size() == 0)
          continue;
        L.removeLast();
        L1.removeLast();

      }
    }
  }

}
