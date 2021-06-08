```java
/**
 * regular expression
 * private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
 * <p>
 * public boolean isNumeric(String strNum) {
 * if (strNum == null) {
 * return false;
 * }
 * return pattern.matcher(strNum).matches();
 * }
 */
public class Solution {
    public boolean isValidNumber(String s) {
        // 123.4 valid
        // 123.4.5 invalid
        // .34 not valid
        // 0123 not valid
        // 5. not valid
        s = s.trim();

        if (s.charAt(0) == '0' && s.length() != 1) return false;

        boolean pointSeen = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (i == 0 && (c == '+' || c == '-')) continue;
            if (c == '.') {
                if (pointSeen) return false;
                pointSeen = true;
                if (i == s.length() - 1) return false;
            } else {
                if (!Character.isDigit(c)) return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isValidNumber("5."));
        System.out.println(solution.isValidNumber("5.0"));
        System.out.println(solution.isValidNumber("5.1.1"));
        System.out.println(solution.isValidNumber("5"));
        System.out.println(solution.isValidNumber("05."));
        System.out.println(solution.isValidNumber("+52"));
        System.out.println(solution.isValidNumber("-52"));
        System.out.println(solution.isValidNumber(".3"));
    }
}
```
