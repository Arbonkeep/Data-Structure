public class AVLSet<E extends Comparable<E>> implements Set<E> {
    private AVLTree<E, Object> set;

    public AVLSet() {
        set = new AVLTree<E,Object>();
    }

    @Override
    public void add(E e) {
        set.add(e, null);
    }

    @Override
    public void remove(E e) {
        set.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return set.contains(e);
    }

    @Override
    public int getSize() {
        return set.getSize();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }
}
