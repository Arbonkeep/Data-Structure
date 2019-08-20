import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String fileName = "C:\\Users\\asus\\Desktop\\Computer_Learning_Notes\\data-structrue\\06-集合和映射\\Map\\src\\a.txt";

	    //1.利用链表实现的map
        LinkedListMap<String,Integer> map = new LinkedListMap<>();
        double time1 = testMap(map, fileName);

        //2.利用二分搜索树实现的map
        BSTMap<String, Integer> map1 = new BSTMap<>();
        double time2 = testMap(map1, fileName);
        System.out.println(time1);
        System.out.println(time2);

    }

    //定义一个比较程序性能的方法
    public static double testMap(Map<String,Integer> map, String fileName) {
        //1.定一起始时间
        long start = System.currentTimeMillis();

        //2.定义执行的程序
        System.out.println(fileName);
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(fileName,words)) {
            System.out.println("总单词数为：" + words.size());

            for(String word : words) {
                if (map.contains(word)) {
                    map.set(word,map.get(word) + 1);
                }else {
                    map.add(word, 1);
                }
            }
            System.out.println("不相同的总单词数为：" + map.getSize());
            System.out.println("you出现的次数：" + map.get("you"));
        }



        //2.定义结束时间
        long end = System.currentTimeMillis();

        return (end - start) / 1000.0;
    }
}
