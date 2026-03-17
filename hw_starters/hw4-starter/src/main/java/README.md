# Discussion
**Part 1:**

BENCHMARKING RESULTS, RAW DATA FOR PRIDE_AND_PREJUDICE:===============================================
--------Benchmarking with unsorted list----------
For ArrayMap, the mean running time is 513ms and the median running time is 480ms for a total of 11 runs.
For BinarySearchTreeMap, the mean running time is 19ms and the median running time is 19ms for a total of 11 runs.
For AvlTreeMap, the mean running time is 18ms and the median running time is 18ms for a total of 11 runs.
For TreapMap, the mean running time is 26ms and the median running time is 27ms for a total of 11 runs.

--------Benchmarking with sorted list----------
For ArrayMap, the mean running time is 2459ms and the median running time is 2434ms for a total of 11 runs.
For BinarySearchTreeMap, the mean running time is 3258ms and the median running time is 3238ms for a total of 11 runs.
For AvlTreeMap, the mean running time is 11ms and the median running time is 11ms for a total of 11 runs.
For TreapMap, the mean running time is 8ms and the median running time is 8ms for a total of 11 runs.


BENCHMARKING RESULTS, RAW DATA FOR FEDERALIST01:====================================================
--------Benchmarking with unsorted list----------
For ArrayMap, the mean running time is 1ms and the median running time is 0ms for a total of 11 runs.
For BinarySearchTreeMap, the mean running time is 1ms and the median running time is 1ms for a total of 11 runs.
For AvlTreeMap, the mean running time is 1ms and the median running time is 1ms for a total of 11 runs.
For TreapMap, the mean running time is 1ms and the median running time is 1ms for a total of 11 runs.

--------Benchmarking with sorted list----------
For ArrayMap, the mean running time is 2ms and the median running time is 2ms for a total of 11 runs.
For BinarySearchTreeMap, the mean running time is 4ms and the median running time is 4ms for a total of 11 runs.
For AvlTreeMap, the mean running time is 0ms and the median running time is 0ms for a total of 11 runs.
For TreapMap, the mean running time is 0ms and the median running time is 0ms for a total of 11 runs.

BENCHMARKING RESULTS, RAW DATA FOR MOBY_DICK:======================================================
--------Benchmarking with unsorted list----------
For ArrayMap, the mean running time is 2681ms and the median running time is 2663ms for a total of 11 runs.
For BinarySearchTreeMap, the mean running time is 40ms and the median running time is 40ms for a total of 11 runs.
For AvlTreeMap, the mean running time is 38ms and the median running time is 38ms for a total of 11 runs.
For TreapMap, the mean running time is 56ms and the median running time is 56ms for a total of 11 runs.

--------Benchmarking with sorted list----------
For ArrayMap, the mean running time is 12787ms and the median running time is 12776ms for a total of 11 runs.
For BinarySearchTreeMap, the mean running time is 14660ms and the median running time is 14648ms for a total of 11 runs.
For AvlTreeMap, the mean running time is 22ms and the median running time is 22ms for a total of 11 runs.
For TreapMap, the mean running time is 16ms and the median running time is 15ms for a total of 11 runs.

BENCHMARKING RESULTS, RAW DATA FOR HOTEL_CALIFORNIA:===============================================
--------Benchmarking with unsorted list----------
For ArrayMap, the mean running time is 0ms and the median running time is 0ms for a total of 11 runs.
For BinarySearchTreeMap, the mean running time is 0ms and the median running time is 0ms for a total of 11 runs.
For AvlTreeMap, the mean running time is 0ms and the median running time is 0ms for a total of 11 runs.
For TreapMap, the mean running time is 0ms and the median running time is 0ms for a total of 11 runs.

--------Benchmarking with sorted list----------
For ArrayMap, the mean running time is 0ms and the median running time is 0ms for a total of 11 runs.
For BinarySearchTreeMap, the mean running time is 0ms and the median running time is 0ms for a total of 11 runs.
For AvlTreeMap, the mean running time is 0ms and the median running time is 0ms for a total of 11 runs.
For TreapMap, the mean running time is 0ms and the median running time is 0ms for a total of 11 runs.

=======================================================================================================

When we benchmarked with unsorted data, the ordering of the words was dependent on the order in which they naturally show up in the text. This also means that it is very unlikely for the regular BinarySearchTreeMap to degenerate into a linear linked list since the words are not in order. This is the result that we see, with this implementation actually outperforming TreapMap. However, the AvlTreeMap implementation still performs better than the BinarySearchTreeMap, suggesting that the balancing helps to reduce the overall time spent on searching for and updating key-value pairs. Unsorted, the performance of BinarySearchTreeMap relative to Treap and AvlTreeMap is purely dependent on the natural distribution of words in the text. Given the right distribution, the exclusion of any rotations actually can make the BinarySearchTreeMap perform faster. Because of the O(n^2) time complexity of the ArrayMap implementation, arising from the need to iterate through each and every element to find the right key, this implementation consistently performs worse than the others. We see more interesting results when we benchmark with a sorted list. When the list is sorted, insertions of new keys in the BinarySearchTreeMap always occur to the left of every subtree, causing the tree to degenerate into a linear structure. Every key access would have to repeatedly traverse to the end of the singular branch, causing this implementation to perform even worse than the ArrayMap. With a linear linked list, which is what we expect, this would become O(n^2) as well. On the other hand, the near-perfect balancing guaranteed by AvlTreeMap ensures that the running time is minimally affected. The probabilistic balancing by TreapMap also has an extremely high likelihood of preventing the otherwise linear linked list structure from developing, driving down runtime to actually outperform AvlTreeMap. Whereas before the rotations added more operations and drove up the runtime, with sorted data they helped keep the find functionality optimized, reducing overall runtime when the function is called repeatedly while inserting.

**Part 2:**

Strategy 1 is correct because this is exactly how a binary heap is intended to work. Every time an element is removed, it represents the removal of the best element in the list. In this case, when the heap is implemented as a max heap, every "best" element will always be the highest value in the list. Thus, removing the best element k times and then storing the kth removal will yield the kth best element. To populate the heap, O(n lg n) time and O(n) space is needed to add each and every element to the heap, and adding each element requires bubbling up the depth of the heap at worst. Then, each removal would take O(lg n) time and O(1) space because the tree would have a max height of ceil(lg n), and removal would at worst require traversing as deep as the tree goes. With k removals, we get O(k lg n) time. O((n+k) lg n) simplifies to O(n lg n). In total, this strategy would be O(n lg n) time and O(n) space.

Strategy 2 is also correct because this method still guarantees that the kth best element becomes stored as the best element of the heap. When implemented as a min heap, the element with the lowest value is stored at the top. With the described algorithm in which the least-valued element is overwritten as necessary across n-k passes, it is guaranteed that the resulting heap will contain the k best elements in the list. All lower elements would have been overwritten since it is always guaranteed that the lowest element will be at the top. Also, with k elements in the heap, the element at the top of the min heap would have the least value, so it would be the kth highest in the list. Therefore, this strategy is also valid. Populating the k elements would take O(k) time while checking and removing when a larger element is found would take O((n-k) * lg k) time. Since the heap only contains k elements, the space would be O(k) while the removals and replacements would still be constant O(1) space operations. O(k) + O((n-k) * lg k) = O(n lg k).

