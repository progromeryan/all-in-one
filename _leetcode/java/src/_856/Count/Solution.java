package _856.Count;

/**
 * 遍历
 * 记录(的个数d,就是嵌套深度
 * 当发现)的时候加上2^(d - 1)
 * 转换成加的形式
 * (()(())) -> (()) + ((()))
 * (()(()())) -> (()) + ((())) + ((()))
 */
public class Solution {
    public int scoreOfParentheses(String s) {
        int ans = 0;
        int d = -1;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            d += c == '(' ? 1 : -1;

            if (s.charAt(i) == '(' && s.charAt(i + 1) == ')') {
                ans += 1 << d;
            }
        }
        return ans;
    }
}
