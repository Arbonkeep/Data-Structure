import java.util.ArrayList;

/**
 * 定义一个通过二分查找树实现的集合类
 * @param <E>
 */
public class BSTSet<E extends Comparable<E>> implements Set<E> {
    private BST<E> bst;

    public BSTSet() {
        bst = new BST<>();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public int getSize() {
        return bst.size();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    public static void main(String[] args) {
        //测试单词统计
        //1.查看文档中一共有多少个单词（包含重复）
        System.out.println("a.txt");
        //1.1创建ArrayList集合
        ArrayList<String> words1 = new ArrayList<>();
        FileOperation.readFile("C:\\Users\\asus\\Desktop\\Computer_Learning_Notes\\data-structrue\\06-集合和映射\\Set\\src\\a.txt",words1);
        System.out.println("total words :" + words1.size());

        //创建自己的集合类，将words1单词添加到集合中
        BSTSet<String> set1 = new BSTSet<>();
        for(String word : words1) {
            set1.add(word);
        }
        System.out.println("total difenrent words:" + set1.getSize());

    }

}
