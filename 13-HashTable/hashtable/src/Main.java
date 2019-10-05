import java.util.HashMap;
import java.util.HashSet;

public class Main {
    /**
     * 测试对应的hashcode
     * @param args
     */
    public static void main(String[] args) {
        int a = 12;
        System.out.println(((Integer)a).hashCode());//在或取基本数据类型的hashcode时，需要转换为对应的包装类

        int b = -12;
        System.out.println(((Integer)a).hashCode());

        double c = 3.1415926;
        System.out.println(((Double)c).hashCode());

        String s = "hello";
        System.out.println(s.hashCode());

        Student s1 = new Student(2, 3, "YUAN", "liao");
        System.out.println(s1.hashCode());

        Student s2 = new Student(2, 3, "YUAN", "liao");
        System.out.println(s2.hashCode());
        //需要注意的是，如果我们没有重写hashCode方法，那么我们所获取的哈希值是不一样的，如果重写了
        //那么就是一样的

        HashSet<Student> hs = new HashSet<>();
        hs.add(s1);
        System.out.println(hs.toString());

        HashMap<Student, Integer> map = new HashMap<>();
        map.put(s2, 60);
        System.out.println(map.toString());
    }
}
