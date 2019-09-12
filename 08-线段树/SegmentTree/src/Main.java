
public class Main {

    public static void main(String[] args) {
	    Integer[] nums = {1, 2, 3, 4, 5};//注意：使用泛型不能使用int基本类型

	    //创建线段树对象
	    SegmentTree<Integer> st = new SegmentTree<>(nums,new Merge<Integer>() {
            @Override
            public Integer merge(Integer a, Integer b) {
                return a + b;//实现线段树求和的功能
            }
        });

        System.out.println(st);

        //实现获取最大值的功能
        SegmentTree<Integer> st2 = new SegmentTree<>(nums, (a , b) -> Integer.max(a, b));

        System.out.println(st2);

        //测试查询功能
        Integer query = st.Query(0, 2);
        System.out.println(query);


    }
}
