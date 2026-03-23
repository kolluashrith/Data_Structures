package hw5.deque;

import hw5.structures.ArrayDeque;
import hw5.structures.Deque;

public class ArrayDequeTest extends DequeTest {

    @Override
    public Deque<Integer> createDeque() {
        return new ArrayDeque<>();
    }
}
