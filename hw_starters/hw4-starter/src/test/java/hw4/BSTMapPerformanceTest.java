package hw4;

import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BSTMapPerformanceTest {

  // Update this to suppress the output.
  public static boolean VERBOSE = true;

  // Update this to any other data file for benchmarking experiments.  `src/test/resources` contains other files; you can provide your own if you'd like
  public static String DATA_FILE = "pride_and_prejudice.txt";

  public static void main(String[] args) throws FileNotFoundException {

    List<String> wordList = readWords();

    //Benchmarking for 2 metrics:
      // 1. Testing Time To Populate Entire Map
      // 2. Testing Time to Look Up Random Elements

    System.out.println("--------Benchmarking with unsorted list----------");
    insertBenchmark(wordList);

   //Now try sorting it
      //
    System.out.println("\n--------Benchmarking with sorted list----------");
    Collections.sort(wordList);
    insertBenchmark(wordList);


    // Hints:
    // * You'll need a fair amount of data to get meaningful results.  Building a map of ten or twenty elements and then looking up elements will not show interesting results.
    // * You must time your code using the built-in System.nanoTime() method. You can see an example here: https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/System.html#nanoTime()
    // * You must run all of your tests multiple times (at least 5, ideally more) and report the number of runs, their average, and median result in milliseconds.
    // * Consider whether the order in which elements are inserted into the map matters
    // * We have provided some code below which will handle reading in all of the words in a file, which you may use for a dataset to test your maps.  Your test results *SHOULD NOT* include the time to read the file

  }

  private static void insertBenchmark(List<String> wordList) {
    long[][] mapTimes = new long[4][5];
    String[] mapNames = {"ArrayMap", "BinarySearchTreeMap", "AvlTreeMap", "TreapMap"};

    //ArrayMap Benchmarking
    for (int i = 0; i < 5; i++) {
      //Clear after every run
      Map<String, Integer> map = new ArrayMap<>();
      long startTime = System.nanoTime();

      for (String word : wordList) {
        addWord(map, word);
      }

      //Get elapsed time
      mapTimes[0][i] = (System.nanoTime() - startTime)/1000000;
    }

    //BinarySearchTreeMap Benchmarking
    for (int i = 0; i < 5; i++) {
      //Clear after every run
      Map<String, Integer> map = new BinarySearchTreeMap<>();
      long startTime = System.nanoTime();

      for (String word : wordList) {
        addWord(map, word);
      }

      //Get elapsed time
      mapTimes[1][i] = (System.nanoTime() - startTime)/1000000;
    }

    //AVLTreeMap Benchmarking
    for (int i = 0; i < 5; i++) {
      //Clear after every run
      Map<String, Integer> map = new AvlTreeMap<>();
      long startTime = System.nanoTime();

      for (String word : wordList) {
        addWord(map, word);
      }

      //Get elapsed time
      mapTimes[2][i] = (System.nanoTime() - startTime)/1000000;
    }

    //TreapMap Benchmarking
    for (int i = 0; i < 5; i++) {
      //Clear after every run
      Map<String, Integer> map = new TreapMap<>();
      long startTime = System.nanoTime();

      for (String word : wordList) {
        addWord(map, word);
      }

      //Get elapsed time
      mapTimes[3][i] = (System.nanoTime() - startTime)/1000000;
    }

    for (int i = 0; i < 4; i++) {
      Arrays.sort(mapTimes[i]);

      long averageTime = 0;
      for (int k = 0; k < 5; k++) {
        averageTime += mapTimes[i][k];
      }
      averageTime /= 5;
      System.out.println("For " + mapNames[i] + ", the mean running time is " + averageTime +
              "ms and the median running time is " + mapTimes[i][2] + "ms for a total of 5 runs.");
    }
  }

  /**
   * Reads the words from a file into a list
   * @throws FileNotFoundException if the data file does not exist.
   */
  private static List<String> readWords() throws FileNotFoundException {
    List<String> words = new ArrayList<>();

    Path resourceDirectory = getPath();
    Scanner sc = new Scanner(resourceDirectory.toFile());

    int wordCount = 0;
    while (sc.hasNext()) {
      String word = sc.next();
      if (isWord(word)) {
        words.add(word);
        wordCount++;
      }
    }

    if (VERBOSE) {
      String description = String.format("Processed %d words", wordCount);
      System.out.println(description);
    }

    return words;
}

  private static Path getPath() {
    URL url = Thread.currentThread().getContextClassLoader().getResource("");
    String path = url.getPath().replace("%20", " ")
      .replace("classes", "resources");


    //Path resourceDirectory = Paths.get(path.substring(1), DATA_FILE);

    // TODO: If you're not running on Windows, use this instead
    Path resourceDirectory = Paths.get(path, DATA_FILE);


    return resourceDirectory;
  }

  private static boolean isWord(String word) {
    // The regular expression splits strings on whitespace and
    //   non-word characters (anything except [a-zA-Z_0-9]). Far
    //   from perfect, but close enough for this simple program.
    // Skip "short" words, most of which just "dirty up" the statistics.
    return word.matches("[a-zA-Z0-9]+") && word.length() > 1;
  }

  // Add word and update frequency count.
  private static void addWord(Map<String, Integer> data, String word) {
    if (data.has(word)) {
      data.put(word, data.get(word) + 1);
    } else {
      data.insert(word, 1);
    }
  }
}