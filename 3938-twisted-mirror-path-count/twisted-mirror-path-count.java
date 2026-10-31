class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if (m == 1 && n == 1) {
            return 1;
        }
        int MOD = 1_000_000_007;
        int[][][] dp = new int[m][n][2];
        if (n > 1) dp[0][1][0] = 1;
        if (m > 1) dp[1][0][1] = 1;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (r == 0 && c == 0) continue; 
                if (r == m - 1 && c == n - 1) continue; 
                int inRight = dp[r][c][0];
                int inDown = dp[r][c][1];
                if (inRight == 0 && inDown == 0) continue;
                if (grid[r][c] == 0) {
                    int totalWays = (inRight + inDown) % MOD;
                    if (c + 1 < n) {
                        dp[r][c + 1][0] = (dp[r][c + 1][0] + totalWays) % MOD;
                    }
                    if (r + 1 < m) {
                        dp[r + 1][c][1] = (dp[r + 1][c][1] + totalWays) % MOD;
                    }
                } else {
                    if (inRight > 0 && r + 1 < m) {
                        dp[r + 1][c][1] = (dp[r + 1][c][1] + inRight) % MOD;
                    }
                    if (inDown > 0 && c + 1 < n) {
                        dp[r][c + 1][0] = (dp[r][c + 1][0] + inDown) % MOD;
                    }
                }
            }
        }
        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
    }
}