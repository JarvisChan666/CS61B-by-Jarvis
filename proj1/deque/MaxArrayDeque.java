package deque;

import java.util.Comparator;
import java.util.ArrayDeque;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
  private Comparator<T> comparator;

  public MaxArrayDeque(Comparator<T> c) {
    this.comparator = c;
  }

  public T max() {
    if (isEmpty()) {
      return null;
    }

    T maxItem = null;
    for (Object item : super.toArray(new Object[0])) {
      if (maxItem == null || comparator.compare((T) item, maxItem) > 0) {
        maxItem = (T) item;
      }
    }

    return maxItem;
  }

  public T max(Comparator<T> c) {
    if (isEmpty()) {
      return null;
    }

    T maxItem = null;
    for (Object item : super.toArray(new Object[0])) {
      if (maxItem == null || c.compare((T) item, maxItem) > 0) {
        maxItem = (T) item;
      }
    }

    return maxItem;
  }
}