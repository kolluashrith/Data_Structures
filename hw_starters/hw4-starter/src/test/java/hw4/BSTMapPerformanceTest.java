package hw4;

import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BSTMapPerformanceTest {

  // Update this to suppress the output.
  public static boolean VERBOSE = true;

  // Update this to any other data file for benchmarking experiments.  `src/test/resources` contains other files; you can provide your own if you'd like
  public static String DATA_FILE = "hotel_california.txt";

  public static void main(String[] args) throws FileNotFoundException {


    // TODO: Design an experiment that will let you measure the performance of your two BBST map implementations, and compare them against the (unbalanced) BSTMap and ArrayMap implementations provided

    // Hints:
    // * You'll need a fair amount of data to get meaningful results.  Building a map of ten or twenty elements and then looking up elements will not show interesting results.
    // * You must time your code using the built-in System.nanoTime() method. You can see an example here: https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/System.html#nanoTime()
    // * You must run all of your tests multiple times (at least 5, ideally more) and report the number of runs, their average, and median result in milliseconds.
    // * Consider whether the order in which elements are inserted into the map matters
    // * We have provided some code below which will handle reading in all of the words in a file, which you may use for a dataset to test your maps.  Your test results *SHOULD NOT* include the time to read the file

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


    Path resourceDirectory = Paths.get(path.substring(1), DATA_FILE);

    // TODO: If you're not running on Windows, use this instead
    //Path resourceDirectory = Paths.get(path, DATA_FILE);


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