import java.util.ArrayList;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        int n = 200000;

        Random random = new Random();
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i ++) {
            list.add(random.nextInt(Integer.MAX_VALUE));
        }

        //BST
        long start = System.currentTimeMillis();

        BST<Integer, Integer> bst = new BST<>();
        for (Integer i : list) {
            bst.add(i, null);
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000.0);

        //AVLTree
        start = System.currentTimeMillis();
        AVLTree<Integer, Integer> map = new AVLTree<>();
        for (Integer i : list) {
            map.add(i, null);
        }
        end = System.currentTimeMillis();
        System.out.println((end - start) / 1000.0);

        //RBTree
        start = System.currentTimeMillis();
        RBTree<Integer, Integer> rbt = new RBTree<>();
        for (Integer i : list) {
            rbt.add(i, null);
        }
        end = System.currentTimeMillis();
        System.out.println((end - start) / 1000.0);

    }


}
