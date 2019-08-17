import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
      /*  //测试二叉树
        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num: nums){
            bst.add(num);
        }

        //测试前序遍历
        bst.preOrder();
        System.out.println();

        //测试中序遍历
        bst.inOrder();
        System.out.println();

        //测试后序遍历
        bst.postOrder();
        System.out.println();

        //测试非递归前序遍历
        bst.preOrderNR();
        System.out.println();

        //测试层序遍历
        bst.levelOrder();
        System.out.println();

       //System.out.println(bst);*/


        BST<Integer> bst = new BST<>();
        Random random = new Random();

        for (int i = 0; i < 1000 ; i++) {
            bst.add(random.nextInt(10000));
        }

        ArrayList<Integer> list = new ArrayList<>();
        while(!bst.isEmpty()) {//如果bst不为空
            list.add(bst.removeMin());//依次从最小值删除
        }

        System.out.println(list);//打印的结果应该是从小到大排序

        for (int i = 1; i < list.size() ; i++) {
            if (list.get(i - 1) > list.get(i)) {
                throw new IllegalArgumentException("error");
            }
        }
        System.out.println("test complete");







    }
}
