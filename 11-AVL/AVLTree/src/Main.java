import java.util.ArrayList;

/**
 * 在main方法中测试BST与AVLTree的性能
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("a.txt");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("C:\\Users\\asus\\Desktop\\Computer_Learning_Notes\\data-structrue\\11-AVL\\AVLTree\\src\\a.txt", words)) {
            //测试BST
            long start = System.currentTimeMillis();

            BST<String, Integer> bst = new BST<>();
            for (String word : words) {
                if (bst.contains(word)) {
                    bst.set(word, bst.get(word) + 1);
                }else {
                    bst.add(word, 1);
                }
            }

            for (String word : words) {
                bst.contains(word);
            }

            long end = System.currentTimeMillis();

            System.out.println("bst time:" + ((end - start) / 1000.0) + "s");

            //测试AVLTree
            start = System.currentTimeMillis();

            AVLTree<String, Integer> map = new AVLTree<>();

            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                }else {
                    map.add(word, 1);
                }
            }

            for (String word : words) {
                map.contains(word);
            }

            end = System.currentTimeMillis();

            System.out.println("AVLTree time:" +((end - start ) / 1000.0) + "s");

        }
    }
}
