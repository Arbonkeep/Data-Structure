import java.util.ArrayList;

/**
 * 对红黑树的性能进行测试
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("a.txt");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("C:\\Users\\asus\\Desktop\\Computer_Learning_Notes\\data-structrue\\12-RedBlackTree\\redblacktree\\src\\a.txt", words)) {
            System.out.println("总字数：" + words.size());

            //BST
            long start = System.currentTimeMillis();

            BST<String, Integer> bst = new BST<>();
            for (String word : words) {
                if (bst.contains(word)) {
                    bst.set(word,  bst.get(word) + 1);
                }else {
                    bst.add(word, 1);
                }
            }

            for (String word: words) {
                bst.contains(word);
            }

            long end =System.currentTimeMillis();

            System.out.println((end - start) / 1000.0);

            //AVLTree
            start = System.currentTimeMillis();

            AVLTree<String,Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                }else {
                    map.add(word, 1 );
                }
            }

            for (String word :words) {
                map.contains(word);
            }

            end = System.currentTimeMillis();
            System.out.println((end - start) / 1000.0);

            //BRTree
            start = System.currentTimeMillis();

            RBTree<String,Integer> rbt = new RBTree<>();
            for (String word : words) {
                if (rbt.contains(word)) {
                    rbt.set(word, rbt.get(word) + 1);
                }else {
                    rbt.add(word, 1 );
                }
            }

            for (String word :words) {
                rbt.contains(word);
            }

            end = System.currentTimeMillis();
            System.out.println((end - start) / 1000.0);

        }

    }
}
