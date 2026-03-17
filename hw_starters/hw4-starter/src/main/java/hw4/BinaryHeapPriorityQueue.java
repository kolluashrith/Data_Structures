package hw4;

import exceptions.EmptyException;

import java.util.*;

/**
 * Priority queue implemented as a binary heap with a ranked array representation.
 *
 * @param <T> Element type.
 */
public class BinaryHeapPriorityQueue<T extends Comparable<T>> implements PriorityQueue<T> {
  private final List<T> heap;
  private Comparator<T> cmp;

  /**
   * Make a BinaryHeapPriorityQueue.
   */
  public BinaryHeapPriorityQueue() {
    this(new DefaultComparator<>());
  }

  /**
   * Make a BinaryHeapPriorityQueue with a custom comparator.
   *
   * @param cmp Comparator to use.
   */
  public BinaryHeapPriorityQueue(Comparator<T> cmp) {
    this.cmp = cmp;
    heap = new ArrayList<>();
    heap.add(null); // Add a dummy element at index 0 to simplify arithmetic
  }

  @Override
  public void insert(T t) {
    heap.add(t);
    int newElementLoc = heap.size() - 1;

    T parent = heap.get(newElementLoc / 2);
    if (parent == null) {
      return;
    } else {
      int comparison = cmp.compare(t, parent); //Compare with parent
      while (comparison < 0) {
        T tempParent = heap.get(newElementLoc / 2);
        heap.set(newElementLoc / 2, t);
        heap.set(newElementLoc, tempParent);
        newElementLoc = newElementLoc / 2;
        if (newElementLoc == 1) { //Make sure we haven't reached end
          break;
        }
        comparison = cmp.compare(t, heap.get(newElementLoc / 2));
      }
    }
  }

  @Override
  public void remove() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }

    removeForReal();

  }

  private void removeForReal() {
    T lastElement = heap.get(heap.size() - 1);
    int currentIndex = popTop(lastElement);

    do {
      int leftChildIndex = currentIndex * 2;
      //Find the minimum child index
      int minIndex = findMinimumChildIndex(leftChildIndex, leftChildIndex + 1);
      if (minIndex != -1) {
        int comparison = cmp.compare(lastElement, heap.get(minIndex));
        if (comparison > 0) {
          swapSmallest(currentIndex, minIndex);
          currentIndex = minIndex;
        } else {
          return;
        }
      } else {
        return;
      }
    } while (currentIndex < heap.size() - 1);
  }

  //Swap with the smallest child
  private void swapSmallest(int currentIndex, int minIndex) {
    T temp = heap.get(minIndex);
    heap.set(minIndex, heap.get(currentIndex));
    heap.set(currentIndex, temp);
  }

  //Remove the highest priority element
  private int popTop(T lastElement) {
    heap.set(1, lastElement);
    heap.remove(heap.size() - 1);
    return 1; //Set current index in calling function to 1
  }

  //Find the minimum child index
  private int findMinimumChildIndex(int leftChildIndex, int rightChildIndex) {
    if (leftChildIndex >= heap.size()) { //No children case
      return -1;
    } else if (rightChildIndex >= heap.size()) { //One child case
      return leftChildIndex;
    } else {
      return (cmp.compare(heap.get(leftChildIndex), heap.get(rightChildIndex)) <= 0) ? leftChildIndex : rightChildIndex;
    }
  }

  @Override
  public T best() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }

    return heap.get(1);
  }

  @Override
  public boolean empty() {
    return (heap.size() == 1);
  }

  @Override
  public Iterator<T> iterator() {
    return new LevelOrderIterator();
  }

  // Default comparator is the natural order of elements that are Comparable.
  private static class DefaultComparator<T extends Comparable<T>> implements Comparator<T> {
    public int compare(T t1, T t2) {
      return t1.compareTo(t2);
    }
  }

  //Iterator class for level-order iteration starting from index 1 (skipping 0 in array)
  private class LevelOrderIterator implements Iterator<T> {

    int cursor;

    private LevelOrderIterator() {
      cursor = 1;
    }

    @Override
    public boolean hasNext() {
      return cursor < heap.size();
    }

    @Override
    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return heap.get(cursor++);
    }
  }
}
