public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {
    private MaxHeap<E> maxHeap;

    public PriorityQueue() {
        maxHeap = new MaxHeap<>();
    }

    public int getSize() {
        return maxHeap.getSize();
    }

    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    public E getFront() {
        return maxHeap.findMax();
    }

    public void enQueue(E e) { 
        maxHeap.add(e);
    }

    public E deQueue() {
        return maxHeap.extractMax();
    }
}
