package starter;

/**
 * Sets of arbitrary values (not necessarily Comparable).
 * Iteration order is undefined.
 *
 * @param <T> Element type.
 */
/*Not bounded (meaning this interface doesn't extend Comparable<T>, so the elements of T don't
need to have some concept of order. */
public interface Set<T> extends Iterable<T> {
  /**
   * Insert a value.
   * Set doesn't change if we try to insert an existing value.
   * Post: has(t) == true.
   *
   * @param t Value to insert.
   */
  void insert(T t);

  /**
   * Remove a value.
   * Set doesn't change if we try to remove a non-existent value.
   * Post: has(t) == false.
   *
   * @param t Value to remove.
   */
  void remove(T t);

  /**
   * Test membership of a value.
   *
   * @param t Value to test.
   * @return True if t is in the set, false otherwise.
   */
  boolean has(T t);

  /**
   * Number of values.
   *
   * @return Number of values in the set, always greater equal to 0.
   */
  int size();
}
