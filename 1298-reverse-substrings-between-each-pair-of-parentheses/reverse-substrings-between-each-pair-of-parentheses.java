class Solution {
    public String reverseParentheses(String s) {
        while (s.indexOf(')') != -1) {
            int close = s.indexOf(')');
            int open = s.lastIndexOf('(', close);
            String inside = s.substring(open + 1, close);
            String reversed = new StringBuilder(inside).reverse().toString();
            s = s.substring(0, open) + reversed + s.substring(close + 1);
        }
        return s;
    }
}