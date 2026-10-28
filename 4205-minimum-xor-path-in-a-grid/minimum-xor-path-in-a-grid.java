class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] dp = new boolean[n][1024];
        dp[0][grid[0][0]] = true;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                boolean[] currentCellDP = new boolean[1024];
                int val = grid[i][j];
                for (int v = 0; v < 1024; v++) {
                    if (i > 0 && dp[j][v]) {
                        currentCellDP[v ^ val] = true;
                    }
                    if (j > 0 && dp[j - 1][v]) {
                        currentCellDP[v ^ val] = true;
                    }
                }
                dp[j] = currentCellDP;
            }
        }
        for (int v = 0; v < 1024; v++) {
            if (dp[n - 1][v]) {
                return v;
            }
        }
        return -1; 
    }
}