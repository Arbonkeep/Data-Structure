import java.util.ArrayList;

/**
 * 在此方法中比较二分搜索树与trie的性能
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("a.txt:");

        ArrayList<String> list = new ArrayList<>();

        if (FileOperation.readFile("C:\\Users\\asus\\Desktop\\" +
                "Computer_Learning_Notes\\data-structrue\\09-trie字典树" +
                "\\trie\\src\\a.txt", list )) {

            long start = System.currentTimeMillis();

            //执行逻辑
            BSTSet<String> set = new BSTSet<>();
            for (String word : list) {
                set.add(word);
            }

            for (String word : list) {
                set.contains(word);
            }

            long end = System.currentTimeMillis();

            double time = (end - start) /1000.0;

            System.out.println("不同的单词有：" + set.getSize());

            System.out.println("BSTSet花费时间：" + time + "s");

            System.out.println("--------------------------");


            start = System.currentTimeMillis();
            Trie trie = new Trie();
            for (String word : list) {
                trie.add(word);
            }

            for (String word : list) {
                trie.contains(word);
            }
            end = System.currentTimeMillis();
            time = (end - start) / 1000.0;

            System.out.println("不同的单词有：" + trie.getSize());

            System.out.println("trie花费时间：" + time + "s");

        }
    }
}
