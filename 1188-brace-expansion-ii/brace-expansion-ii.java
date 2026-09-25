class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> resultSet = new TreeSet<>();
        dfs(expression, resultSet);
        return new ArrayList<>(resultSet);
    }
    private void dfs(String currentExpr, Set<String> resultSet) {
        if (currentExpr.indexOf('{') == -1) {
            String[] words = currentExpr.split(",");
            for (String word : words) {
                resultSet.add(word);
            }
            return;
        }
        int right = currentExpr.indexOf('}');
        int left = right;
        while (currentExpr.charAt(left) != '{') {
            left--;
        }
        String before = currentExpr.substring(0, left);
        String after = currentExpr.substring(right + 1);
        String[] options = currentExpr.substring(left + 1, right).split(",");
        for (String opt : options) {
            dfs(before + opt + after, resultSet);
        }
    }
}