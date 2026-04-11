package hw6;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressWarnings("unchecked")
public class ChainingHashMap<K, V> implements Map<K, V> {

  private static final int DEFAULT_INITIAL_CAPACITY = 16;
  private static final double DEFAULT_LOAD_FACTOR = 0.75;

  private ArrayList<K>[] keys;
  private int numElements;

  /**
   * Constructor for ChainingHashMap.
   */
  public ChainingHashMap() {
    keys = (ArrayList<K>[]) new ArrayList[DEFAULT_INITIAL_CAPACITY];

  }

  @Override
  public void insert(K k, V v) throws IllegalArgumentException {
    if (k == null) {
      throw new IllegalArgumentException("Key cannot be null");
    }

    int hash = k.hashCode();
    int toInsert =  hash % keys.length;

    //Collision Probe
    for (K key : keys[toInsert]) {
      if (key.equals(k)) {
        throw new IllegalArgumentException("Key is already in the map");
      }
    }

    //Here, we know where key belongs and that it is not already contained. Just add it.
    keys[toInsert].add(k);
    numElements++;


  }

  //Helper method to grow the data array when the number of elements exceeds load factor.
  private void grow() {
    keys = (ArrayList<K>[]) new ArrayList[keys.length * 2];

    for (ArrayList<K> list : keys) {
      for (K key : list) {
      }
    }
  }

  @Override
  public V remove(K k) throws IllegalArgumentException {
    // TODO Implement Me!
    return null;
  }

  @Override
  public void put(K k, V v) throws IllegalArgumentException {
    // TODO Implement Me!
  }

  @Override
  public V get(K k) throws IllegalArgumentException {
    // TODO Implement Me!
    return null;
  }

  @Override
  public boolean has(K k) {
    // TODO Implement Me!
    return false;
  }

  @Override
  public int size() {
    return numElements;
  }

  @Override
  public Iterator<K> iterator() {
    // TODO Implement Me!
    return null;
  }
}
