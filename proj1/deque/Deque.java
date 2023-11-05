package deque;

public interface Deque<T> {
  void addFirst(T item);
  void addLast(T item);
  T removeFirst();
  T removeLast();
  T getFirst();
  T getLast();
  default boolean isEmpty() {
    return size() == 0;
  }
  int size();
}