public interface Queue<E> {
    public int getSize();
    public boolean isEmpty();
    public void enqueue(E e);
    public E dequeue();
    public E getFront();
}
