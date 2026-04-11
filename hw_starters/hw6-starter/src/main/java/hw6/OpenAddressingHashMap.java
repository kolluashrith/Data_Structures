package hw6;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")

public class OpenAddressingHashMap<K, V> implements Map<K, V> {

  private static final int DEFAULT_INITIAL_CAPACITY = 16;
  private static final double DEFAULT_LOAD_FACTOR = 0.75;

  private DataWrapper<K, V>[] data;
  private int numElements;
  private int numTombstones;

  /**
   * Constructor for the OpenAddressingHashMap.
   */
  public OpenAddressingHashMap() {

    data = (DataWrapper<K, V>[]) new DataWrapper[DEFAULT_INITIAL_CAPACITY];
    numElements = 0;

  }

  @Override
  public void insert(K k, V v) throws IllegalArgumentException {
    if (k == null) {
      throw new IllegalArgumentException("Key cannot be null");
    }

    int hash = k.hashCode();
    int toInsert =  hash % data.length;

    //Probe
    while (data[toInsert] != null) {
      if (data[toInsert].key == k) {
        throw new IllegalArgumentException("Key is already in the map");
      } else {
        //Linear probing implementation
        toInsert = (toInsert + 1) % data.length;
      }
    }

    //Found empty space
    data[toInsert] = new DataWrapper<>(k, v);
    numElements++;

    //At this point, we are sure that our key is not already present in the map
    //We can proceed with checking size and growing if needed

    if ((numElements + numTombstones + 1.00) / data.length >= DEFAULT_LOAD_FACTOR) {
      grow();
    }
  }

  //Helper method to grow the data array when the number of elements and tombstones exceeds load
  //factor. Clear all tombstones in the process.
  private void grow() {
    data = (DataWrapper<K, V>[]) new DataWrapper[data.length * 2];
    numTombstones = 0;

    for (DataWrapper<K, V> dataElement : data) {
      if (dataElement != null && !dataElement.tombstone) {
        insert(dataElement.key, dataElement.value);
      }
    }
  }

  @Override
  public V remove(K k) throws IllegalArgumentException {

    if (k == null) {
      throw new IllegalArgumentException("Key cannot be null");
    }

    int locationAt = find(k);
    if (locationAt == -1) {
      throw new  IllegalArgumentException("Key not found");
    }

    V toReturn = data[locationAt].value;
    data[locationAt] = new DataWrapper<>(true);
    numElements--;

    return toReturn;
  }

  //Returns the index in the array where the key is, if it exists. If not, returns -1.
  private int find(K k) {
    int hash = k.hashCode();
    int locationAt =  hash % data.length;
    while (data[locationAt] != null) {
      if (data[locationAt].key == k) {
        return locationAt;
      } else {
        locationAt = (locationAt + 1) % data.length;
      }
    }
    return -1;
  }

  @Override
  public void put(K k, V v) throws IllegalArgumentException {

    if (k == null) {
      throw new IllegalArgumentException("Key cannot be null");
    }

    //Don't use has(k) function because we need to store location anyway
    int locationAt = find(k);
    if (locationAt == -1) {
      throw new  IllegalArgumentException("Key not found");
    }
    data[locationAt].value = v;
  }

  @Override
  public V get(K k) throws IllegalArgumentException {
    if (k == null || !has(k)) {
      throw new IllegalArgumentException("Key cannot be null");
    }

    //Don't use has(k) function because we need to store location anyway
    int locationAt = find(k);
    if (locationAt == -1) {
      throw new  IllegalArgumentException("Key not found");
    }

    return data[locationAt].value;
  }

  @Override
  public boolean has(K k) {

    int locationAt = find(k);

    return (locationAt != -1);
  }

  @Override
  public int size() {
    return numElements;
  }

  @Override
  public Iterator<K> iterator() {
    return new DataIterator();
  }

  /*
  Nested class to wrap data to handle the functionality of tombstones in the hash map.
   */
  private static class DataWrapper<K, V> {
    K key;
    V value;
    boolean tombstone;

    DataWrapper(K key, V value) {
      this.key = key;
      this.value = value;
      tombstone = false;
    }

    DataWrapper(boolean tombstone) {
      key = null;
      value = null;
      this.tombstone = tombstone;
    }
  }

  private class DataIterator implements Iterator<K> {

    private int cursor;

    DataIterator() {
      cursor = 0;

      //Start cursor at first element
      while (cursor < data.length && (data[cursor] == null || data[cursor].tombstone)) {
        cursor++;
      }
    }

    @Override
    public boolean hasNext() {
      return cursor < data.length;
    }

    @Override
    public K next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      DataWrapper<K, V> returnPair = data[cursor];

      do {
        cursor++;
      } while (cursor < data.length && (data[cursor] == null || data[cursor].tombstone));

      return returnPair.key;
    }
  }

}
