public class Main {

    public static void main(String[] args) {
        //测试链表类
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0 ; i < 5 ; i++) {
            linkedList.addFirst(i);//在链表头添加元素
            System.out.println(linkedList);
        }

        linkedList.add(2,666);//在2的位置添加666
        System.out.println(linkedList);

        linkedList.remove(2);
        System.out.println(linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);

        linkedList.removeLast();
        System.out.println(linkedList);
    }
}
