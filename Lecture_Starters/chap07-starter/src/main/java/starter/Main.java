package starter;

public class Main {

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();

        linkedList.addFirst(10);
        linkedList.addFirst(7);
        linkedList.addFirst(9);
        linkedList.addFirst(5);
        linkedList.insert(2, 2);

        System.out.println("List has " + linkedList.length() + " elements.");

        linkedList.println();

        System.out.println("List at index 2 holds value " + linkedList.get(2));

        System.out.println("Trying new enhanced for loop on linked list: ");
        //Class needs to be iterable, requirement implementing iterable interface, to use enhanced for loop
        for (int element : linkedList) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
