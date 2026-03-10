package hw4;

import org.junit.jupiter.api.Test;

/**
 * In addition to the tests in BinarySearchTreeMapTest (and in OrderedMapTest & MapTest),
 * we add tests specific to Treap.
 */
@SuppressWarnings("All")
public class TreapMapTest extends BinarySearchTreeMapTest {

  @Override
  protected Map<String, String> createMap() {
    return new TreapMap<>();
  }

  @Test
  public void insertLeftRotationNullLeft(){
    map.insert("1", "a");
    map.insert("2", "b");
  }
  // TODO Add tests
  //  (think about how you might write tests while randomness is involved in TreapMap implementation!)

}