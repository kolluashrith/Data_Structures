package starter;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class IndexedListTest {

  private final static int SIZE = 5;
  private final static Integer DEFAULT_VALUE = 10;
  IndexedList<Integer> numbers;

  @BeforeEach //Runs this code before every test to avoid repeated code
  void setup(){
    numbers = new ArrayIndexedList<>(SIZE, DEFAULT_VALUE);
  }

  @Test
  @DisplayName("length() returns the number of elements in the list")
  void testLengthAfterConstruction() {
    // TODO Implement me!
    assertEquals(SIZE, numbers.length());
  }

  @Test
  @DisplayName("get() returns the default value after IndexedList is instantiated.")
  void testGetAfterConstruction() {
    // TODO Implement me!
    numbers = new ArrayIndexedList<>(SIZE, DEFAULT_VALUE);
    for (int i = 0; i < SIZE; i++) {
      assertEquals(DEFAULT_VALUE, numbers.get(i));
    }
  }

  @Test
  @DisplayName("put() changes the default value after IndexedList is instantiated.")
  void testPutChangesValueAfterConstruction() {
    // TODO Implement me!
    numbers.put(2, 7);
    assertEquals(7, numbers.get(2));
  }

  @Test
  @DisplayName("put() overwrites the existing value at given index to provided value.")
  void testPutUpdatesValueAtGivenIndex() {
    // TODO Implement me!
    numbers.put(2, 7);
    assertEquals(7, numbers.get(2));
    numbers.put(1, -5);
    assertEquals(-5, numbers.get(1));
  }
}