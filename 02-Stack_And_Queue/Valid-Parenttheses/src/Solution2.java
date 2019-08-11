/**
 * 利用自己的stack类来实现括号匹配功能
 */
public class Solution2 {
    public boolean isValid(String s) {
        //1.创建一个栈对象
        ArrayStack<Character> stack = new ArrayStack<>();
        //2.遍历字符串s
        for (int i = 0; i <s.length() ; i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{' )
                stack.push(c);
            else {
                if (stack.isEmpty())
                    return false;
                char topChar = stack.pop();
                if (c == ')' && topChar != '(')
                    return false;
                if (c == ']' && topChar != '[')
                    return false;
                if (c == '}' && topChar != '}')
                    return false;

            }

        }
        return stack.isEmpty();
    }



}
