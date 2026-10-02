class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }
    private void backtrack(List<String> result, StringBuilder currentString, int open, int close, int n) {
        if (currentString.length() == n * 2) {
            result.add(currentString.toString());
            return;
        }
        if (open < n) {
            currentString.append("(");
            backtrack(result, currentString, open + 1, close, n);
            currentString.deleteCharAt(currentString.length() - 1);
        }
        if (close < open) {
            currentString.append(")");
            backtrack(result, currentString, open, close + 1, n);
            currentString.deleteCharAt(currentString.length() - 1);
        }
    }
}