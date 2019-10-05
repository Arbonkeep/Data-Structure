import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        System.out.println("a.txt");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("C:\\Users\\asus\\Desktop\\Computer_Learning_Notes\\data-structrue\\13-HashTable\\hashtable\\src\\a.txt",words)) {
            System.out.println("总字数为：" + words.size());

            //Test BST
            long start = System.currentTimeMillis();
            BST<String, Integer> map = new BST<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            for (String word : words) {
                map.contains(word);
            }

            long end = System.currentTimeMillis();
            System.out.println("bst:" + (end - start) / 1000.0);

            //Test AVLTree
            start = System.currentTimeMillis();
            AVLTree<String, Integer> map1 = new AVLTree<>();
            for (String word : words) {
                if (map1.contains(word))
                    map1.set(word, map1.get(word) + 1);
                else
                    map1.add(word, 1);
            }

            for (String word : words) {
                map1.contains(word);
            }

            end = System.currentTimeMillis();
            System.out.println("AVLTree:" + (end - start) / 1000.0);

            //Test RBTree
            start = System.currentTimeMillis();
            RBTree<String, Integer> map2 = new RBTree<>();
            for (String word : words) {
                if (map2.contains(word))
                    map2.set(word, map2.get(word) + 1);
                else
                    map2.add(word, 1);
            }

            for (String word : words) {
                map2.contains(word);
            }

            end = System.currentTimeMillis();
            System.out.println("RBTree:" + (end - start) / 1000.0);


            //Test HashTable
            start = System.currentTimeMillis();
            HashTable<String, Integer> map3 = new HashTable<>();
            for (String word : words) {
                if (map3.contains(word))
                    map3.set(word, map3.get(word) + 1);
                else
                    map3.add(word, 1);
            }

            for (String word : words) {
                map3.contains(word);
            }

            end = System.currentTimeMillis();
            System.out.println("hashtable:" + (end - start) / 1000.0);
        }
    }
}
