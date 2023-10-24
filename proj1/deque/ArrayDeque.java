package deque;

public class ArrayDeque<T> {
  private Object[] arrayDeque;
  private int size;
  private int front;
  private int rear;
  private static final int DEFAULT_CAPACITY = 16;
  private static final double USAGE_FACTOR_THRESHOLD = 0.25;

  public ArrayDeque() {
    arrayDeque = new Object[DEFAULT_CAPACITY];
    size = 0;
    front = 0;
    rear = 0;
  }

  public void addFirst(T item) {
    if (size == arrayDeque.length) {
      resize();
    }
    // circular
    front = (front - 1 + arrayDeque.length) % arrayDeque.length;
    arrayDeque[front] = item;
    size++;
  }

  public void addLast(T item) {
    if (size == arrayDeque.length) {
      resize();
    }
    arrayDeque[rear] = item;
    // circular
    rear = (rear + 1) % arrayDeque.length;
  }

  public T removeFirst() {
    if (isEmpty()) {
      return null;
    }
    T item = (T) arrayDeque[front];
    arrayDeque[front] = null;
    front = (front + 1) % arrayDeque.length;
    size--;
    if (arrayDeque.length > DEFAULT_CAPACITY
        && (double) size / arrayDeque.length < USAGE_FACTOR_THRESHOLD) {
      resize();
    }
    return item;
  }

  public T removeLast() {
    if (isEmpty()) {
      return null;
    }
    // circular
    rear = (rear - 1 + arrayDeque.length) % arrayDeque.length;
    T item = (T) arrayDeque[rear];
    arrayDeque[rear] = null;
    size--;
    if (arrayDeque.length > DEFAULT_CAPACITY
        && (double) size / arrayDeque.length < USAGE_FACTOR_THRESHOLD) {
      resize();
    }
    return item;
  }

  public T getFirst() {
    if (isEmpty()) {
      return null;
    }
    return (T) arrayDeque[front];
  }

  public T getLast() {
    if (isEmpty()) {
      return null;
    }
    return (T) arrayDeque[(rear - 1 + arrayDeque.length) / arrayDeque.length];
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  private void resize() {
    int newSize = Math.max(DEFAULT_CAPACITY, (int) (size / USAGE_FACTOR_THRESHOLD));
    Object[] newArrayDeque = new Object[newSize];
    for (int i = 0; i < size; i++) {
      newArrayDeque[i] = arrayDeque[(front + i) % arrayDeque.length];
    }
    arrayDeque = newArrayDeque;
    front = 0;
    rear = size;
  }
}
