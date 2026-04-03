package starter;

import java.util.Arrays;

public class Demo {

  /**
   * Sorts the input arr.
   *
   * @param arr an array of integers.
   */
  public static void quicksort(int[] arr) {
    quicksort(arr, 0, arr.length);
  }

  // Pre: 0 <= left <= right <= arr.length.
  // Post: arr[left] ... arr[right-12] is sorted.
  private static void quicksort(int[] arr, int left, int right) {

    int n = right - left; //number of elements to sort

    if (n < 2) { //Base case
      return;
    } else {
      int p = partition(arr, left, right);
      quicksort(arr, left, p);
      quicksort(arr, p + 1, right); //Pivot is already in sorted location, so we don't have to include again
    }

  }

  // Partition by taking the right-most element as pivot.
  // Pre: 0 <= left <= right <= arr.length;
  // Post: a[left] <= .. <= pivot <= .. <= a[right - 1]
  // Returns: index of pivot element in array.
  private static int partition(int[] arr, int left, int right) {
    int p = right - 1; //Pivot index
    int i = left; //left marker index
    int j = p - 1; //right marker index

    while (i <= j) {
      if (arr[i] <= arr[p]) {
        i++;
      } else if (arr[j] > arr[p]) {
        j--;
      } else {
        //Now we have both markers at a position where they need to be swapped.
        swap(arr, i, j);
      }
    }
    swap(arr, i, p);
    return i;
  }

  // Swap arr[i] with arr[j]
  private static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  /**
   * Demo starts here.
   *
   * @param args command-line arguments.
   */
  public static void main(String[] args) {
    int[] arr = {20, 13, 7, 71, 31, 10, 5, 50, 100};
    System.out.println("Input: " + Arrays.toString(arr));
    quicksort(arr);
    System.out.println("Output: " + Arrays.toString(arr));
  }
}
