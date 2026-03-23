package hw5.adapters;

import hw5.structures.ArrayDeque;
import hw5.structures.Deque;

public class ArrayDequeAdapterTest extends AdapterContractTest {
    @Override
    public Deque<Integer> createDeque() {
        return new ArrayDeque<>();
    }
}
