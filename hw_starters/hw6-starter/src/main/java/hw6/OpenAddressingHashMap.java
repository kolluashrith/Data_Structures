package hw6;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")

public class OpenAddressingHashMap<K, V> implements Map<K, V> {

  private static final double DEFAULT_LOAD_FACTOR = 0.75;
  private static final int[] PRIMES = {5, 11, 23, 47, 97, 197, 397, 797, 1597, 3203, 6421, 12853, 25717, 51437, 102877,
                                       205759, 411527, 823117, 1646249, 3292507, 6584983, 13169977, 26339969, 52679969,
                                       105359939, 210719881, 421439783, 842879579, 1685759167};
  private static final DataWrapper<?, ?> TOMBSTONE = new DataWrapper<>(null, null);

  private DataWrapper<K, V>[] data;
  private int numElements;
  private int numTombstones;
  private int currentPrimeIndex;
  private final double loadFactor;

  /**
   * Constructor for the OpenAddressingHashMap.
   */
  public OpenAddressingHashMap() {

    currentPrimeIndex = 0;
    data = (DataWrapper<K, V>[]) new DataWrapper[PRIMES[currentPrimeIndex]];
    numElements = 0;
    loadFactor = DEFAULT_LOAD_FACTOR;

  }

  /**
   * Overloaded constructor for OpenAddressingHashMap allowing for the modification of the load factor
   * @param loadFactor double corresponding load factor to use
   */
  public OpenAddressingHashMap(double loadFactor) {

    currentPrimeIndex = 0;
    data = (DataWrapper<K, V>[]) new DataWrapper[PRIMES[currentPrimeIndex]];
    numElements = 0;
    this.loadFactor = loadFactor;

  }

  @Override
  public void insert(K k, V v) throws IllegalArgumentException {
    if (k == null) {
      throw new IllegalArgumentException("Key cannot be null");
    }

    //Find empty space
    int toInsert = toInsert(k);
    data[toInsert] = new DataWrapper<>(k, v);
    numElements++;

    //At this point, we are sure that our key is not already present in the map
    //We can proceed with checking size and growing if needed

    if (((float) numElements + numTombstones) / data.length >= loadFactor) {
      grow();
    }
  }

  //Helper method to return the index at which there is empty space to insert the new key-value pair.
  private int toInsert(K k) throws IllegalArgumentException {
    int hash = Math.abs(k.hashCode());
    int toInsert =  hash % data.length;

    // The line below is only required for quadratic probing.
    // int numProbeMovements = 0;

    //Collision Probe
    while (data[toInsert] != null) {
      if (data[toInsert] == TOMBSTONE || !data[toInsert].key.equals(k)) {
        //Linear probing implementation
        toInsert = (toInsert + 1) % data.length;

        //Quadratic probing implementation; uncomment line 36 when using this as well
        //toInsert = (toInsert + numProbeMovements * numProbeMovements) & data.length;

      } else { //implies data[toInsert].key.equals(k) is true
        throw new IllegalArgumentException("Key is already in the map");
      }
    }

    //If here, we found an empty space. Return index.
    return toInsert;
  }

  //Helper method to grow the data array when the number of elements and tombstones exceeds load
  //factor. Clear all tombstones in the process.
  private void grow() {

    numTombstones = 0;
    numElements = 0;

    DataWrapper<K, V>[] oldData = data;
    data = (DataWrapper<K, V>[]) new DataWrapper[PRIMES[++currentPrimeIndex]];

    for (DataWrapper<K, V> dataElement : oldData) {
      if (dataElement != null && dataElement != TOMBSTONE) {
        int toInsert = toInsert(dataElement.key);
        data[toInsert] = new DataWrapper<>(dataElement.key, dataElement.value);
        numElements++;
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

    numTombstones++;
    V toReturn = data[locationAt].value;
    data[locationAt] = (DataWrapper<K, V>) TOMBSTONE;
    numElements--;

    return toReturn;
  }

  //Returns the index in the array where the key is, if it exists. If not, returns -1.
  private int find(K k) {
    int hash = Math.abs(k.hashCode());
    int locationAt = hash % data.length;
    while (data[locationAt] != null) {
      if (data[locationAt] != TOMBSTONE && data[locationAt].key.equals(k)) {
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
    if (k == null) {
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

    DataWrapper(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }

  private class DataIterator implements Iterator<K> {

    private int cursor;

    DataIterator() {
      cursor = 0;

      //Start cursor at first element
      while (cursor < data.length && (data[cursor] == null || data[cursor] ==  TOMBSTONE)) {
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
      } while (cursor < data.length && (data[cursor] == null || data[cursor] == TOMBSTONE));

      return returnPair.key;
    }
  }

}
