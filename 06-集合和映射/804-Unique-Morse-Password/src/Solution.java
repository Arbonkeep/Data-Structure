import java.util.TreeSet;

public class Solution {
    public int uniqueMorseRepresentations(String[] words) {
        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

        //1.创建treeSet集合
        TreeSet<String> set = new TreeSet<>();
        //2.遍历传入的字符串数组
        for (String word : words) {
            //3.创建StringBuilder对象
            StringBuilder sb = new StringBuilder();
            //4.遍历每一个字符串获取字符，并获取到摩斯码codes中的索引,将其添加到sb
            for (int i = 0; i < word.length() ; i++) {
                sb.append(codes[word.charAt(i) - 'a']);
            }
            //5.将sb添加到集合中
            set.add(sb.toString());
        }
        return set.size();

    }
}
