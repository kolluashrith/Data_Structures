# Discussion
All benchmarking code was included in the Benchmarking file while the AVL Tree implementation, with some edits, is included in AvlTreeMap.

--------Benchmarking with random164.txt----------
For Open Addressing Hash Map, with a load factor of 0.6, the mean running time is 335ms and the median running time is 374ms for a total of 5 runs.
For Open Addressing Hash Map, with a load factor of 0.7, the mean running time is 301ms and the median running time is 278ms for a total of 5 runs.
For Open Addressing Hash Map, with a load factor of 0.8, the mean running time is 271ms and the median running time is 275ms for a total of 5 runs.
For Open Addressing Hash Map, with a load factor of 0.9, the mean running time is 1453ms and the median running time is 1255ms for a total of 5 runs.
For Open Addressing Hash Map, with a load factor of 0.95, the mean running time is 910ms and the median running time is 941ms for a total of 5 runs.
For Chaining Hash Map, with a load factor of 0.6, the mean running time is 1069ms and the median running time is 1025ms for a total of 5 runs.
For Chaining Hash Map, with a load factor of 0.7, the mean running time is 731ms and the median running time is 672ms for a total of 5 runs.
For Chaining Hash Map, with a load factor of 0.8, the mean running time is 1094ms and the median running time is 933ms for a total of 5 runs.
For Chaining Hash Map, with a load factor of 0.9, the mean running time is 732ms and the median running time is 657ms for a total of 5 runs.
For Chaining Hash Map, with a load factor of 0.95, the mean running time is 639ms and the median running time is 650ms for a total of 5 runs.
For the AVL Tree, the mean running time is 1709 ms and the median running time is 1726 ms for a total of 5 runs.

--------Benchmarking with newegg.txt----------
For Open Addressing Hash Map, with a load factor of 0.6, the mean running time is 49ms and the median running time is 41ms for a total of 5 runs.
For Open Addressing Hash Map, with a load factor of 0.7, the mean running time is 51ms and the median running time is 43ms for a total of 5 runs.
For Open Addressing Hash Map, with a load factor of 0.8, the mean running time is 64ms and the median running time is 63ms for a total of 5 runs.
For Open Addressing Hash Map, with a load factor of 0.9, the mean running time is 41ms and the median running time is 40ms for a total of 5 runs.
For Open Addressing Hash Map, with a load factor of 0.95, the mean running time is 52ms and the median running time is 49ms for a total of 5 runs.
For Chaining Hash Map, with a load factor of 0.6, the mean running time is 73ms and the median running time is 60ms for a total of 5 runs.
For Chaining Hash Map, with a load factor of 0.7, the mean running time is 64ms and the median running time is 59ms for a total of 5 runs.
For Chaining Hash Map, with a load factor of 0.8, the mean running time is 76ms and the median running time is 75ms for a total of 5 runs.
For Chaining Hash Map, with a load factor of 0.9, the mean running time is 71ms and the median running time is 72ms for a total of 5 runs.
For Chaining Hash Map, with a load factor of 0.95, the mean running time is 67ms and the median running time is 63ms for a total of 5 runs.
For the AVL Tree, the mean running time is 151 ms and the median running time is 121 ms for a total of 5 runs.

--------Benchmarking with apache.txt----------
For Open Addressing Hash Map, with a load factor of 0.6, the mean running time is 82ms and the median running time is 73ms for a total of 5 runs.
For Open Addressing Hash Map, with a load factor of 0.7, the mean running time is 63ms and the median running time is 56ms for a total of 5 runs.
For Open Addressing Hash Map, with a load factor of 0.8, the mean running time is 105ms and the median running time is 101ms for a total of 5 runs.
For Open Addressing Hash Map, with a load factor of 0.9, the mean running time is 110ms and the median running time is 107ms for a total of 5 runs.
For Open Addressing Hash Map, with a load factor of 0.95, the mean running time is 136ms and the median running time is 121ms for a total of 5 runs.
For Chaining Hash Map, with a load factor of 0.6, the mean running time is 172ms and the median running time is 175ms for a total of 5 runs.
For Chaining Hash Map, with a load factor of 0.7, the mean running time is 158ms and the median running time is 154ms for a total of 5 runs.
For Chaining Hash Map, with a load factor of 0.8, the mean running time is 168ms and the median running time is 156ms for a total of 5 runs.
For Chaining Hash Map, with a load factor of 0.9, the mean running time is 113ms and the median running time is 112ms for a total of 5 runs.
For Chaining Hash Map, with a load factor of 0.95, the mean running time is 112ms and the median running time is 105ms for a total of 5 runs.
For the AVL Tree, the mean running time is 309 ms and the median running time is 306 ms for a total of 5 runs.

These benchmarking tests test the insert method from the Map interface because this method is the most involved and includes a lot of the other core operations. This means it forms a comprehensive overview about how most of the core operations perform for each implementation. From these results, it appears that the hash table implementations consistently outperform the AVL Tree implementation from hw4. We can see this across all the texts that resulted in > 1 ms runtimes. Datasets too small to result in 1+ ms runtimes were excluded for the sake of relevancy. With random164.txt, the worst hash table runtime, which was with open addressing with a high load factor, was 1453 ms. However, the AVL Tree did significantly worse, running at 1709 ms. Similar results are observed across the other datasets tested. Thus, it must be concluded that the hash tables nearly always run faster than the AVL Tree for the Map interface because of the O(1) average runtime instead of the O(log n) runtime for the AVL tree. 

The load factor does significantly affect performance because it affects the frequency of collisions and affects the frequency at which the grow function is called. Though there are some outliers, across the datasets it appears that Open Addressing does typically perform better with lower load factors. This makes sense because lower load factors prevents dense arrays, reducing the collisions and decreasing the number of cells that need to be searched on average to find an empty slot for insertion. There are tests in which Open Addressing does well with a relatively high load factor of 0.9, which could be attributed to the datasets happening to cause an unusually low collision frequency. The differences matter a lot for open addressing. With random164.txt, for example, the difference between the performance with a load factor of 0.6 vs 0.9 was almost a full second -- about 350% -- slower. This difference in performance based on load factor is visible with chaining as well, but in the opposite direction. The results consistently show that higher load factors result in faster runtimes than lower ones. This makes sense for chaining because it's likely faster to iterate through the few collisions stored linearly instead of having to transfer over each and every ArrayList. 

New question: Which 