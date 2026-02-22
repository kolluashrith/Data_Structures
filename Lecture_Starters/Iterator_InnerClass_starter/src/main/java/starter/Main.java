package starter;

import java.util.Iterator;

@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) {

        int[] myArray = new int [5];
        for (int i = 0; i < myArray.length; i++) {
            System.out.print(myArray[i] + " ");
        }
        System.out.println("");

        IndexedList<Integer> myIndexedList = new ArrayIndexedList<>(5, 0);

        System.out.println("For loop on IndexedList: ");
        for (int i = 0; i < myIndexedList.length(); i++) {
            System.out.print(myIndexedList.get(i) + " ");
        }
        System.out.println();

        System.out.println("Enhanced for loop on array: ");
        for (int element : myArray) {
            System.out.print(element + " ");
        }
        System.out.println();

        System.out.println("Enhanced for loop on IndexedList: ");
        for (int element : myIndexedList) {
            System.out.print(element + " ");
        }
        System.out.println();

        System.out.println("IndexedList using iterator: ");
        Iterator<Integer> it = myIndexedList.iterator();
        while (it.hasNext()) {
            int element = it.next();
            System.out.print(element + " ");
        }
        System.out.println();
    }

}
