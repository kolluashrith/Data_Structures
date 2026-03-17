package hw4;

import exceptions.EmptyException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * Priority queue implemented as a binary heap with a ranked array representation.
 * Implemented as a max-heap.
 * @param <T> Element type.
 */
public class BinaryHeapPriorityQueue<T extends Comparable<T>> implements PriorityQueue<T> {
  private final List<T> heap;
  private final Comparator<T> cmp;

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

    int parentindex = newElementLoc / 2;
    if (parentindex <= 0) {
      return;
    } else {
      while (newElementLoc > 1 && cmp.compare(t, heap.get(parentindex)) > 0) {
        T parent = heap.get(parentindex);
        heap.set(newElementLoc / 2, t);
        heap.set(newElementLoc, parent);
        newElementLoc = newElementLoc / 2;
        parentindex = newElementLoc / 2;
        if (parentindex == 0) {
          return;
        }
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
      //Find the maximum child index
      int maxIndex = findMaximumChildIndex(leftChildIndex, leftChildIndex + 1);
      if (maxIndex != -1) {
        int comparison = cmp.compare(lastElement, heap.get(maxIndex));
        if (comparison < 0) {
          swapBiggest(currentIndex, maxIndex);
          currentIndex = maxIndex;
        } else {
          return;
        }
      } else {
        return;
      }
    } while (currentIndex * 2 < heap.size()); //Check until no children are left
  }

  //Swap with the biggest child
  private void swapBiggest(int currentIndex, int maxIndex) {
    T temp = heap.get(maxIndex);
    heap.set(maxIndex, heap.get(currentIndex));
    heap.set(currentIndex, temp);
  }

  //Remove the highest priority element
  private int popTop(T lastElement) {
    heap.set(1, lastElement);
    heap.remove(heap.size() - 1);
    return 1; //Set current index in calling function to 1
  }

  //Find the minimum child index
  private int findMaximumChildIndex(int leftChildIndex, int rightChildIndex) {
    if (leftChildIndex >= heap.size()) { //No children case
      return -1;
    } else if (rightChildIndex >= heap.size()) { //One child case
      return leftChildIndex;
    } else {
      return (cmp.compare(heap.get(leftChildIndex), heap.get(rightChildIndex)) >= 0) ? leftChildIndex : rightChildIndex;
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
