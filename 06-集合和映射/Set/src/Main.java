import java.util.ArrayList;

public class Main {
    //定义一个私有的测试方法
    private static double testSet(Set<String> set, String fileName) {
        //定义开始时间
        long start = System.currentTimeMillis();

        //定义执行程序
        ArrayList<String> words1 = new ArrayList<>();
        FileOperation.readFile("C:\\Users\\asus\\Desktop\\Computer_Learning_Notes\\data-structrue\\06-集合和映射\\Set\\src\\a.txt",words1);
        System.out.println("total words :" + words1.size());

        for( String word : words1) {
            set.add(word);

        }
        System.out.println("total different words : " + set.getSize());

        //定义结束时间
        long end = System.currentTimeMillis();

        return (end - start) / 1000.0;
    }

    //定以一个主方法
    public static void main(String[] args) {
        //定义文件名
        String fileName = "C:\\Users\\asus\\Desktop\\Computer_Learning_Notes\\data-structrue\\06-集合和映射\\Set\\src\\a.txt";
        //创建BSTSet对象
        BSTSet<String> set = new BSTSet<>();
        double time1 = testSet(set, fileName);

        //创建链表对象
        LinkedListSet<String> set2 = new LinkedListSet<>();
        double time2 = testSet(set2, fileName);

        System.out.println(time1);
        System.out.println(time2);

    }

}
