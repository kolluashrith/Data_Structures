package hw4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * In addition to the tests in BinarySearchTreeMapTest (and in OrderedMapTest & MapTest),
 * we add tests specific to AVL Tree.
 */
@SuppressWarnings("All")
public class AvlTreeMapTest extends BinarySearchTreeMapTest {

  @Override
  protected Map<String, String> createMap() {
    return new AvlTreeMap<>();
  }

  @Test
  public void insertLeftRotation() {
    map.insert("1", "a");
    // System.out.println(avl.toString());
    // must print
    /*
        1:a
     */

    map.insert("2", "b");
    // System.out.println(avl.toString());
    // must print
    /*
        1:a,
        null 2:b
     */

    map.insert("3", "c"); // it must do a left rotation here!
    // System.out.println(avl.toString());
    // must print
    /*
        2:b,
        1:a 3:c
     */

    String[] expected = new String[]{
        "2:b",
        "1:a 3:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertMultipleLeftRotations() {
    map.insert("1", "a");
    map.insert("2", "b");
    map.insert("3", "c");
    map.insert("4", "d");
    map.insert("5", "e");
    String[] expected = new String[]{
            "2:b",
            "1:a 4:d",
            "null null 3:c 5:e"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());

  }

  @Test
  public void insertRightRotation() {
    map.insert("3", "c");
    map.insert("2", "b");
    map.insert("1", "a");

    String[] expected = new String[]{
            "2:b",
            "1:a 3:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertMultipleRightRotations() {
    map.insert("5", "e");
    map.insert("4", "d");
    map.insert("3", "c");
    map.insert("2", "b");
    map.insert("1", "a");

    String[] expected = new String[]{
            "4:d",
            "2:b 5:e",
            "1:a 3:c null null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());

  }

  @Test
  public void insertRightLeftRotation() {
    map.insert("3", "c");
    map.insert("7", "g");
    map.insert("5", "e");

    String[] expected = new String[]{
            "5:e",
            "3:c 7:g"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());

  }

  @Test
  public void insertMultipleRightLeftRotations() {
    map.insert("3", "c");
    map.insert("9", "i");
    map.insert("4", "d");
    map.insert("7", "g");
    map.insert("5", "e");
    map.insert("6", "f");



    String[] expected = new String[]{
            "5:e",
            "4:d 7:g",
            "3:c null 6:f 9:i",
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());

  }

  @Test
  public void insertLeftRightRotation() {
    map.insert("7", "g");
    map.insert("3", "c");
    map.insert("5", "e");

    String[] expected = new String[]{
            "5:e",
            "3:c 7:g"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());

  }

  @Test
  public void insertMultipleLeftRightRotations() {
    map.insert("9", "i");
    map.insert("5", "e");
    map.insert("8", "g");
    map.insert("3", "c");
    map.insert("6", "f");
    map.insert("7", "d");

    String[] expected = new String[]{
            "6:f",
            "5:e 8:g",
            "3:c null 7:d 9:i"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());

  }

  @Test
  public void insertAllRotations() {
    map.insert("1", "i");
    map.insert("3", "e");
    map.insert("7", "g");
    map.insert("6", "c");
    map.insert("4", "f");
    map.insert("5", "d");
    map.insert("2", "k");

    String[] expected = new String[]{
            "4:f",
            "2:k 6:c",
            "1:i 3:e 5:d 7:g"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());

  }

  @Test
  public void removeLeftRotation() {
    map.insert("1", "a");
    map.insert("2", "b");
    map.insert("3", "c");
    map.insert("4", "d");
    map.remove("1");

    String[] expected = new String[]{
            "3:c",
            "2:b 4:d"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeRightRotation() {
    map.insert("4", "d");
    map.insert("2", "b");
    map.insert("3", "c");
    map.insert("1", "a");
    map.remove("4");

    String[] expected = new String[]{
            "2:b",
            "1:a 3:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeLeftRightRotation() {
    map.insert("4", "d");
    map.insert("1", "a");
    map.insert("3", "c");
    map.insert("2", "b");
    map.remove("4");

    String[] expected = new String[]{
            "2:b",
            "1:a 3:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void removeRightLeftRotation() {
    map.insert("1", "a");
    map.insert("2", "b");
    map.insert("4", "d");
    map.insert("3", "c");
    map.remove("1");

    String[] expected = new String[]{
            "3:c",
            "2:b 4:d"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

}
