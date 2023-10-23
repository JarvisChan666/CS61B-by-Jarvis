package deque;

import java.util.Iterator;
import java.util.Objects;

public class LinkedListDeque<T> {
  private Node<T> sentinel; // sentinel node of the deque
  private int size; // size of the deque

  public LinkedListDeque() {
    sentinel = new Node<T>(null);
    sentinel.next = sentinel;
    sentinel.prev = sentinel;
    size = 0;
  }

  /**
   *
   * @param item
   * RULES: add and remove must not involve any looping or recursion
   */
  public void addFirst(T item) {
    Node<T> newNode = new Node<T>(item);
    newNode.prev = sentinel;
    newNode.next = sentinel.next;
    sentinel.next.prev = newNode;
    sentinel.next = newNode;
    size++;
  }

  public void addLast(T item) {
    Node<T> newNode = new Node<T>(item);
    newNode.next = sentinel;
    newNode.prev = sentinel.prev;
    sentinel.prev.next = newNode;
    sentinel.prev = newNode;
    size++;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  public void printDeque() {
    Node<T> current = sentinel.next;
    while (current != sentinel) {
      System.out.println(current.item + " ");
      current = current.next;
    }
    System.out.println();
  }

  public T removeFirst() {
    if (isEmpty()) {
      return null;
    }
    Node<T> firstNode = sentinel.next;
    T item = firstNode.item;
    sentinel.next = firstNode.next;
    firstNode.next.prev = sentinel;
    firstNode.next = null;
    firstNode.prev = null;
    firstNode.item = null;
    size--;
    return item;
  }

  public T removeLast() {
    if (isEmpty()) {
      return null;
    }
    Node<T> lastNode = sentinel.prev;
    T item = lastNode.item;
    //use a node to mark last node
    sentinel.prev = lastNode.prev;
    lastNode.prev.next = sentinel;
    lastNode.prev = null;
    lastNode.next = null;
    lastNode.item = null;
    size--;
    return item;
  }

  /** must use iteration*/
  public T get(int index) {
    if (index < 0 || index >= size) {
      return null;
    }
    Node<T> current = sentinel.next;
    for (int i = 0; i < index; i++) {
      current = current.next;
    }
    return current.item;
  }

  public T getRecursive(int index) {
    if (index < 0 || index >= size) {
      return null;
    }
    return getRecursiveHelper(sentinel.next, index);
  }

  /**
   *
   * @param current
   * @param index
   * @return
   *
   * The getRecursiveHelper method
   * takes in the current node and the index as parameters.
   * If the index reaches 0,
   * it means we have reached the desired node,
   * and it returns the item of that node.
   * Otherwise, it calls itself recursively with the next node
   * and decrements the index by 1.
   */
  private T getRecursiveHelper(Node<T> current, int index) {
    if(index == 0) {
      return current.item;
    }
    return getRecursiveHelper(current.next, index - 1);
  }

  /**
   *
   * @return
   *
   * By implementing the iterator() method
   * and the DequeIterator inner class, the Deque object becomes iterable,
   * allowing you to iterate over its elements
   * using a foreach loop or other iterator-based constructs.
   */
  public Iterator<T> iterator() {
    return new DequeIterator();
  }

  private class DequeIterator implements Iterator<T> {
    private Node<T> current;

    public DequeIterator() {
      current = sentinel.next;
    }

    @Override
    public boolean hasNext() {
      return current != sentinel;
    }

    @Override
    public T next() {
      if (!hasNext()) {
        return null;
      }
      T item = current.item;
      current = current.next;
      return item;
    }
  }

  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof LinkedListDeque<?>)) {
      return false;
    }
    LinkedListDeque<?> other = (LinkedListDeque<?>) o;
    if (size != other.size()) {
      return false;
    }
    /** Finally, it iterates over both deques using their respective iterators.*/
    Iterator<T> thisIterator = iterator();
    Iterator<?> otherIterator = other.iterator();
    while (thisIterator.hasNext() && otherIterator.hasNext()) {
      T thisItem = thisIterator.next();
      Object otherItem = otherIterator.next();
      if (!Objects.equals(thisItem, otherItem)) {
        return false;
      }
    }
    return true;
  }

  private static class Node<T> {
    private T item;
    private Node<T> prev;
    private Node<T> next;

    public Node(T item) {
      this.item = item;
      this.prev = null;
      this.next = null;
    }
  }
}
