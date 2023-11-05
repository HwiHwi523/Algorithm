package L295;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFinder {

    private final PriorityQueue<Integer> minHeap;
    private final PriorityQueue<Integer> maxHeap;

    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public void addNum(int num) {
        minHeap.add(num);

        if (getLengthDifference() > 1) {
            maxHeap.add(minHeap.poll());
        }

        while (!minHeap.isEmpty() && !maxHeap.isEmpty() && minHeap.peek() < maxHeap.peek()) {
            maxHeap.add(minHeap.poll());
            minHeap.add(maxHeap.poll());
        }
    }

    private int getLengthDifference() {
        return Math.abs(minHeap.size() - maxHeap.size());
    }

    public double findMedian() {
        if (minHeap.size() > maxHeap.size()) {
            return (double) minHeap.peek();
        }

        return (minHeap.peek() + maxHeap.peek()) / 2.0;
    }
}
