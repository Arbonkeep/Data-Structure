public class AVLMap<K extends Comparable<K>, V> implements Map<K, V> {
    private AVLTree<K, V> avlMap;

    public AVLMap() {
        avlMap = new AVLTree<>();
    }

    @Override
    public void add(K key, V value) {
        avlMap.add(key, value);
    }

    @Override
    public V remove(K key) {
        return avlMap.remove(key);
    }

    @Override
    public boolean contains(K key) {
        return avlMap.contains(key);
    }

    @Override
    public V get(K key) {
        return avlMap.get(key);
    }

    @Override
    public void set(K key, V value) {
        avlMap.set(key, value);
    }

    @Override
    public int getSize() {
        return avlMap.getSize();
    }

    @Override
    public boolean isEmpty() {
        return avlMap.isEmpty();
    }
}
