package hw6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ChainingHashMap<K, V> implements Map<K, V> {

  private static final double DEFAULT_LOAD_FACTOR = 0.75;
  private static final int[] PRIMES = {5, 11, 23, 47, 97, 197, 397, 797, 1597, 3203, 6421, 12853, 25717, 51437, 102877,
                                       205759, 411527, 823117, 1646249, 3292507};

  private ArrayList<Pair<K, V>>[] pairListList;
  private int numElements;
  private int currentPrimeIndex;
  private final double loadFactor;

  /**
   * Constructor for ChainingHashMap.
   */
  public ChainingHashMap() {
    currentPrimeIndex = 0;
    pairListList = (ArrayList<Pair<K, V>>[]) new ArrayList[PRIMES[currentPrimeIndex]];
    numElements = 0;
    loadFactor = DEFAULT_LOAD_FACTOR;
  }

  /**
   * Overloaded constructor for ChainingHashMap allowing for the modification of the load factor
   * @param loadFactor double corresponding load factor to use
   */
  public ChainingHashMap(double loadFactor) {
    currentPrimeIndex = 0;
    pairListList = (ArrayList<Pair<K, V>>[]) new ArrayList[PRIMES[currentPrimeIndex]];
    numElements = 0;
    this.loadFactor = loadFactor;
  }

  @Override
  public void insert(K k, V v) throws IllegalArgumentException {
    if (k == null) {
      throw new IllegalArgumentException("Key cannot be null");
    }

    //This repeated code (from findKey) is required to find the index to initialize the ArrayList if needed
    int hash = Math.abs(k.hashCode());
    int pairListToInsert =  hash % pairListList.length;

    //Collision Probe
    checkDuplicate(findKey(k));

    //Initialize the ArrayList if it's null
    if (pairListList[pairListToInsert] == null) {
      pairListList[pairListToInsert] = new ArrayList<>();
    }

    //Here, we know where key belongs and that it is not already contained. Just add it.
    Pair<K, V> pairToAdd = new Pair<>(k, v);
    pairListList[pairListToInsert].add(pairToAdd);
    numElements++;

    if (((float) numElements / pairListList.length) > loadFactor) {
      grow();
    }
  }

  //Helper method to throw exception if key to insert was already found in map.
  private void checkDuplicate(Pair<Integer, Integer> findKeyResult) throws IllegalArgumentException {
    if (findKeyResult.value != null) { //Second number, value, corresponds to key match
      throw new IllegalArgumentException("Duplicate key found");
    }
  }

  //Helper method to find a key in the hash table, if it exists. Returns a pair with the first integer corresponding
  //to the array position and the second integer corresponding to the ArrayList position of the key.
  private Pair<Integer, Integer> findKey(K k) {
    int hash = Math.abs(k.hashCode());
    int pairListToInsert =  hash % pairListList.length;

    if (pairListList[pairListToInsert] == null) {
      return new Pair<>(null, null);
    } else if (pairListList[pairListToInsert].isEmpty()) {
      return new Pair<>(pairListToInsert, null);
    } else {
      int counter = 0;
      for (Pair<K, V> key : pairListList[pairListToInsert]) {
        if (key.key.equals(k)) {
          return new Pair<>(pairListToInsert, counter);
        } else {
          counter++;
        }
      }
      return new Pair<>(pairListToInsert, null);
    }
  }



  //Helper method to grow the data array when the number of elements exceeds load factor.
  private void grow() {
    numElements = 0;
    ArrayList<Pair<K, V>>[] oldData = pairListList;
    pairListList = (ArrayList<Pair<K, V>>[]) new ArrayList[PRIMES[++currentPrimeIndex]];

    for (ArrayList<Pair<K, V>> list : oldData) {
      if (list != null) {
        for (Pair<K, V> pairs : list) {
          insert(pairs.key, pairs.value);
        }
      }
    }
  }

  @Override
  public V remove(K k) throws IllegalArgumentException {
    Pair<Integer, Integer> findKeyResult = checkKeyExists(k);
    numElements--;

    return pairListList[findKeyResult.key].remove((int) findKeyResult.value).value;
  }

  //Helper method to verify that the specified key exists in the map
  private Pair<Integer, Integer> checkKeyExists(K k) {
    if  (k == null) {
      throw new IllegalArgumentException("Key cannot be null");
    }
    Pair<Integer, Integer> findKeyResult = findKey(k);
    if (findKeyResult.value == null) {
      throw new IllegalArgumentException("Key not found");
    }
    return findKeyResult;
  }

  @Override
  public void put(K k, V v) throws IllegalArgumentException {
    Pair<Integer, Integer> findKeyResult = checkKeyExists(k);

    pairListList[findKeyResult.key].get((int) findKeyResult.value).value = v;
  }

  @Override
  public V get(K k) throws IllegalArgumentException {
    Pair<Integer, Integer> findKeyResult = checkKeyExists(k);

    return pairListList[findKeyResult.key].get((int) findKeyResult.value).value;
  }

  @Override
  public boolean has(K k) {
    Pair<Integer, Integer> findKeyResult = findKey(k);

    return findKeyResult.value != null;
  }

  @Override
  public int size() {
    return numElements;
  }

  @Override
  public Iterator<K> iterator() {
    return new PairListListIterator();
  }

  private static class Pair<K, V> {
    K key;
    V value;

    Pair(K k, V v) {
      key = k;
      value = v;
    }
  }

  private class PairListListIterator implements Iterator<K> {

    int trueIndex;
    int localIndex;
    int currentList;

    PairListListIterator() {
      trueIndex = 0;
      localIndex = 0;
      currentList = 0;

      while (currentList < pairListList.length
              && (pairListList[currentList] == null || pairListList[currentList].isEmpty())) {
        currentList++;
      }
    }

    @Override
    public boolean hasNext() {
      return trueIndex < numElements;
    }

    @Override
    public K next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      trueIndex++;
      K toReturn = pairListList[currentList].get(localIndex).key;
      if ((localIndex + 1) == pairListList[currentList].size()) {
        localIndex = 0;
        do {
          currentList++;
        } while (currentList < pairListList.length
                && (pairListList[currentList] == null || pairListList[currentList].isEmpty()));
      } else {
        localIndex++;
      }

      return toReturn;
    }
  }
}
