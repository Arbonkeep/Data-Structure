import java.util.ArrayList;

/**
 * 定义一个基于链表实现的集合类
 * @param <E>
 */
public class LinkedListSet<E> implements Set<E> {
    private LinkedList<E> list;

    public LinkedListSet() {
        list = new LinkedList<>();
    }

    @Override
    public boolean contains(E e) {
        return list.comtains(e);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public void add(E e) {
        if(!list.comtains(e)) {
            list.addFirst(e);
        }
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    public static void main(String[] args) {
        System.out.println("a.txt");
        //1.1创建ArrayList集合
        ArrayList<String> words1 = new ArrayList<>();
        FileOperation.readFile("C:\\Users\\asus\\Desktop\\Computer_Learning_Notes\\data-structrue\\06-集合和映射\\Set\\src\\a.txt",words1);
        System.out.println("total words :" + words1.size());

        LinkedListSet<String> set1 = new LinkedListSet<>();
        for(String word : words1) {
            set1.add(word);
        }
        System.out.println("total difenrent words:" + set1.getSize());
    }
}
