import java.util.Stack;

/**
 * 利用java提供的栈实现括号匹配功能
 */
class Solution {
    public boolean isValid(String s) {
        //1.创建一个栈对象
        Stack<Character> stack = new Stack<Character>();
        //2.对传进来的字符串进行遍历
        for (int i = 0; i < s.length() ; i++) {
            char c = s.charAt(i);//获取每一个字符
            //3.对每一个字符进行判断,如果都是左括号就将其添加进栈
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }else {
                //4.进行下一步判断，如果栈为空说明匹配失败，如果栈顶元素与压进栈的字符不匹配，匹配失败
                if (stack.isEmpty()) {
                    return false;
                }
                Character topChar = stack.pop();//获取栈顶元素
                if (c == ')' && topChar != '(') {
                    return false;
                }
                if (c ==']' && topChar != '[') {
                    return false;
                }
                if (c == '}' && topChar != '{') {
                    return false;
                }
            }
        }

        return stack.isEmpty();

    }

    //定义一个main函数测试上面代码

    public static void main(String[] args) {
        //1.定义字符串
        String s  = "()[]{}";
        String s2 = "[[()(}";

        //2.创建Solution对象，调用方法测试功能
        Solution solution = new Solution();
        boolean valid = solution.isValid(s);
        System.out.println(valid);
        boolean valid1 = solution.isValid(s2);
        System.out.println(valid1);


    }
}
