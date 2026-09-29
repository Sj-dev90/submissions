class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if ((m + n - 1) % 2 != 0 || grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }
        Boolean[][][] memo = new Boolean[m][n][m + n];
        return dfs(grid, 0, 0, 0, memo);
    }
    private boolean dfs(char[][] grid, int r, int c, int balance, Boolean[][][] memo) {
        if (grid[r][c] == '(') {
            balance++;
        } else {
            balance--;
        }
        if (balance < 0) {
            return false;
        }
        if (r == grid.length - 1 && c == grid[0].length - 1) {
            return balance == 0;
        }
        if (memo[r][c][balance] != null) {
            return memo[r][c][balance];
        }
        boolean canReachEnd = false;
        if (r + 1 < grid.length) {
            canReachEnd = canReachEnd || dfs(grid, r + 1, c, balance, memo);
        }
        if (!canReachEnd && c + 1 < grid[0].length) {
            canReachEnd = canReachEnd || dfs(grid, r, c + 1, balance, memo);
        }
        memo[r][c][balance] = canReachEnd;
        return canReachEnd;
    }
}