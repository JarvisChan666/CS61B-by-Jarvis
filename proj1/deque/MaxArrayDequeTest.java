package deque;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MaxArrayDequeTest {
  private MaxArrayDeque<String> deque;

  @Before
  public void setUp() {
    // 创建一个自定义的 Comparator，用于比较字符串的长度
    Comparator<String> lengthComparator = Comparator.comparingInt(String::length);
    // 创建一个 MaxArrayDeque 实例，并传入自定义的 Comparator
    deque = new MaxArrayDeque<>(lengthComparator);
  }

  @Test
  public void testMax() {
    deque.addLast("apple");
    deque.addLast("banana");
    deque.addLast("orange");
    deque.addLast("kiwi");

    // 测试 max() 方法
    String maxItem = deque.max();
    assertEquals("banana", maxItem);
  }

  @Test
  public void testMaxWithComparator() {
    deque.addLast("apple");
    deque.addLast("banana");
    deque.addLast("orange");
    deque.addLast("kiwi");

    // 测试 max(Comparator<T> c) 方法
    String maxItemByComparator = deque.max(Comparator.comparingInt(s -> s.charAt(0)));
    assertEquals("orange", maxItemByComparator);
  }

  @Test
  public void testEmptyDeque() {
    // 测试空队列的情况
    String maxItem = deque.max();
    assertNull(maxItem);
  }
}
