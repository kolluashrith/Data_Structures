package hw6;

class OpenAddressingHashMapTest extends MapTest {
  @Override
  protected Map<String, String> createMap() {
    return new OpenAddressingHashMap<>();
  }
}