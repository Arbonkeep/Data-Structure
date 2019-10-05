/**
 * 解决leetcode中387号问题：字符串中的第一个唯一的字符（只包含小写字母）
 * 举例：
 *      s = "leetcode"
 *      返回0
 *      s = "loveleetcode"
 *      返回2
 */
public class Solution {

    public int firstUniqChar(String s) {
        //1. 定义一个数组存储26字母对应的值
        int[] arr = new int[26];

        //2. 遍历字符串。获取对应字母的索引a->0,b->1...
        for (int i = 0; i < s.length(); i++ ) {
            arr[s.charAt(i) - 'a']++;//进行++表示出现的频次
        }

        //3.遍历字符串判断该字符是否第一个唯一的字符
        for (int i = 0; i < arr.length; i++) {
            if (arr[s.charAt(i) - 'a'] == 1) {//如果出现的频次为1
                return i;//将对应的索引返回
            }
        }
        return -1;
    }

}
