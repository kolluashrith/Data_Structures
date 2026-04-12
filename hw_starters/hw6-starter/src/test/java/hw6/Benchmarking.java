package hw6;

import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Benchmarking {

    // Update this to suppress the output.
    public static boolean VERBOSE = true;

    // Update this to any other data file for benchmarking experiments.  `src/test/resources` contains other files; you can provide your own if you'd like
    public static String DATA_FILE = "apache.txt";

    public static void main(String[] args) throws FileNotFoundException {

        List<String> wordList = readWords();

        System.out.println("--------Benchmarking with " + DATA_FILE + "----------");
        insertBenchmark(wordList);

    }

    private static void insertBenchmark(List<String> wordList) {
        long[][][] hashMapTimes = new long[2][6][5];
        long[] AVLTreeTimes = new long[5];
        String[] mapNames = {"Open Addressing Hash Map", "Chaining Hash Map", "AVL Tree Map"};
        double[] lfs = {0.6, 0.7, 0.8, 0.9, 0.95};

        //OpenAddressing Benchmarking
        for (int lf = 0; lf < 5; lf++) {
            for (int trial = 0; trial < 5; trial++) {
                //Clear after every run
                Map<String, Integer> map = new OpenAddressingHashMap<>(lfs[lf]);
                long startTime = System.nanoTime();

                for (String word : wordList) {
                    addWord(map, word);
                }

                //Get elapsed time
                hashMapTimes[0][lf][trial] = (System.nanoTime() - startTime)/1000000;
            }
        }

        //Chaining Benchmarking
        for (int lf = 0; lf < 5; lf++) {
            for (int trial = 0; trial < 5; trial++) {
                //Clear after every run
                Map<String, Integer> map = new ChainingHashMap<>(lfs[lf]);
                long startTime = System.nanoTime();

                for (String word : wordList) {
                    addWord(map, word);
                }

                //Get elapsed time
                hashMapTimes[1][lf][trial] = (System.nanoTime() - startTime) / 1000000;
            }
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
            AVLTreeTimes[i] = (System.nanoTime() - startTime)/1000000;
        }

        //Reporting results
        for (int i = 0; i < 2; i++) {
            for (int lfIndex = 0; lfIndex < 5; lfIndex++) {
                Arrays.sort(hashMapTimes[i][lfIndex]);

                long averageTime = 0;
                for (int d = 0; d < 5; d++) {
                    averageTime += hashMapTimes[i][lfIndex][d];
                }

                averageTime /= 5;

                System.out.println("For " + mapNames[i] + ", with a load factor of " + lfs[lfIndex] + ", the mean running time is " + averageTime +
                    "ms and the median running time is " + hashMapTimes[i][lfIndex][2] + "ms for a total of 5 runs.");
            }
        }

        long AVLaverageTime = 0;
        for (int i = 0; i < 5; i++) {
            Arrays.sort(AVLTreeTimes);
            AVLaverageTime += AVLTreeTimes[i];
        }
        AVLaverageTime /= 5;

        System.out.println("For the AVL Tree, the mean running time is " + AVLaverageTime +
                " ms and the median running time is " + AVLTreeTimes[2] + " ms for a total of 5 runs.");
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