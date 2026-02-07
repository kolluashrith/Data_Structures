package starter;

@SuppressWarnings("all")
public class Main {

    public static void main(String[] args) {

        IndexedList<Integer> list = new ArrayIndexedList(5, -1);
        list.put(0,1);
        list.put(1, 2);
        list.put(2, 3);
        for (int i = 0; i < list.length(); i++) {
            int value = list.get(i);
            System.out.println(value);
        }
    }
}
