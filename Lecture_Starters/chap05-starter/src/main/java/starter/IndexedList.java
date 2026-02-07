package starter;

/**
 * IndexedList ADT.
 * @param <T> the base type of the items in the IndexedList.
 */
public interface IndexedList<T> {

  /**
   * Change the value at the given index.
   *
   * @param index representing a position in this list.
   *              Pre: 0 <= index < length
   * @param value to be written at the given index.
   *              Post: this.get(index) == value
   */
  void put(int index, T value);

  /**
   * Retrieve the value stored at the given index.
   *
   * @param index representing a position in this list.
   *              Pre: 0 <= index < length
   * @return value at the given index.
   * @throws IndexException when index < 0 or index >= length
   */
  T get(int index);

  /**
   * Get the declared capacity of this list.
   *
   * @return the length
   *         Inv: length() >= 0
   * @throws IndexException when index < 0 or index >= length
   */
  int length();
}
