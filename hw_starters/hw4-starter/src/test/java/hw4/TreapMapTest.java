package hw4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * In addition to the tests in BinarySearchTreeMapTest (and in OrderedMapTest & MapTest),
 * we add tests specific to Treap.
 */
@SuppressWarnings("All")
public class TreapMapTest extends BinarySearchTreeMapTest {

  //Using the overridden method, we create a seeded TreapMap whose priority sequence is always:
  // 16, 3, 2, 13, 6, 18, 17, 10, 15, 1, 14, 7, 4, 9, 12, 5, 8, 11, 19... repeat
  @Override
  protected Map<String, String> createMap() {
    return new TreapMap<>(true);
  }

  @Test
  public void insertLeftRotation() {
    map.insert("1", "a"); //Priority 16
    map.insert("2", "b"); //Priority 3

    String[] expected = new String[]{
            "2:b:3",
            "1:a:16 null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertDoubleLeftRotation() {
    map.insert("1", "a"); //Priority 16
    map.insert("2", "b"); //Priority 3
    map.insert("3", "c"); //Priority 2

    String[] expected = new String[]{
            "3:c:2",
            "2:b:3 null",
            "1:a:16 null null null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertRightRotation() {
    map.insert("2", "b"); //Priority 16
    map.insert("1", "a"); //Priority 3

    String[] expected = new String[]{
            "1:a:3",
            "null 2:b:16"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertDoubleRightRotation() {
    map.insert("3", "c"); //Priority 16
    map.insert("2", "b"); //Priority 3
    map.insert("1", "a"); //Priority 2

    String[] expected = new String[]{
            "1:a:2",
            "null 2:b:3",
            "null null null 3:c:16"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertRightLeftRotation() {
    map.insert("2", "b"); //Priority 16
    map.insert("1", "a"); //Priority 3
    map.insert("3", "c"); //Priority 2

    String[] expected = new String[]{
            "3:c:2",
            "1:a:3 null",
            "null 2:b:16 null null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertLeftRightRotation() {
    map.insert("2", "b"); //Priority 16
    map.insert("3", "c"); //Priority 3
    map.insert("1", "a"); //Priority 2

    String[] expected = new String[]{
            "1:a:2",
            "null 3:c:3",
            "null null 2:b:16 null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertSevenElements() {
    map.insert("5", "e"); //Priority 16
    map.insert("3", "c"); //Priority 3
    map.insert("1", "a"); //Priority 2
    map.insert("4", "d"); //Priority 13
    map.insert("6", "f"); //Priority 6
    map.insert("7", "g"); //Priority 18
    map.insert("2", "b"); //Priority 17

    String[] expected = new String[]{
            "1:a:2",
            "null 3:c:3",
            "null null 2:b:17 6:f:6",
            "null null null null null null 4:d:13 7:g:18",
            "null null null null null null null null null null null null null 5:e:16 null null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeWithLeftRotation() {
    map.insert("1", "a"); //Priority 16
    map.insert("3", "c"); //Priority 3
    map.insert("2", "b"); //Priority 2

    map.remove("2");

    String[] expected = new String[]{
            "3:c:3",
            "1:a:16 null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeWithRightRotation() {
    map.insert("3", "c"); //Priority 16
    map.insert("2", "b"); //Priority 3
    map.insert("4", "d"); //Priority 2
    map.insert("1", "a"); //Priority 13

    map.remove("4");

    String[] expected = new String[]{
            "2:b:3",
            "1:a:13 3:c:16"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeWithRightLeftRotation() {
    map.insert("3", "c"); //Priority 16
    map.insert("2", "b"); //Priority 3
    map.insert("4", "d"); //Priority 2
    map.insert("1", "a"); //Priority 13
    map.insert("5", "e"); //Priority 6
    map.insert("6", "f"); //Priority 18

    map.remove("4");

    String[] expected = new String[]{
            "2:b:3",
            "1:a:13 5:e:6",
            "null null 3:c:16 6:f:18"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeMultipleElements() {
    map.insert("3", "c"); //Priority 16
    map.insert("2", "b"); //Priority 3
    map.insert("4", "d"); //Priority 2
    map.insert("1", "a"); //Priority 13
    map.insert("5", "e"); //Priority 6
    map.insert("6", "f"); //Priority 18

    map.remove("4");
    map.remove("5");
    map.remove("2");

    String[] expected = new String[]{
            "1:a:13",
            "null 3:c:16",
            "null null null 6:f:18"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void getValidKey() {
    map.insert("3", "c"); //Priority 16
    map.insert("2", "b"); //Priority 3
    map.insert("4", "d"); //Priority 2
    map.insert("1", "a"); //Priority 13
    map.insert("5", "e"); //Priority 6
    map.insert("6", "f"); //Priority 18

    assertTrue("e".equals(map.get("5")));
  }

  @Test
  public void hasChecksMembership() {
    map.insert("3", "c"); //Priority 16
    map.insert("2", "b"); //Priority 3
    map.insert("4", "d"); //Priority 2
    map.insert("1", "a"); //Priority 13
    map.insert("5", "e"); //Priority 6
    map.insert("6", "f"); //Priority 18

    assertTrue(map.has("5"));
    assertTrue(map.has("6"));
    assertFalse(map.has("7"));
  }

  @Test
  public void sizeReturnsCorrectSize() {
    map.insert("3", "c"); //Priority 16
    map.insert("2", "b"); //Priority 3
    map.insert("4", "d"); //Priority 2
    map.insert("1", "a"); //Priority 13
    map.insert("5", "e"); //Priority 6
    map.insert("6", "f"); //Priority 18

    assertEquals(6, map.size());
  }
}