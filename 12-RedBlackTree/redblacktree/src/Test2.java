import java.util.ArrayList;

/**
 * 测试
 */
public class Test2 {
    public static void main(String[] args) {
        int n = 200000;


        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i ++) {
            list.add(i);
        }


        //AVLTree
        long start = System.currentTimeMillis();
        AVLTree<Integer, Integer> map = new AVLTree<>();
        for (Integer i : list) {
            map.add(i, null);
        }
        long end = System.currentTimeMillis();
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
