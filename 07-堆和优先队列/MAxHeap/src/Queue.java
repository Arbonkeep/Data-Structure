public interface Queue<E> {
    boolean isEmpty();
    void enQueue(E e);
    E getFront();
    E deQueue();
    int getSize();

}
