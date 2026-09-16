class Solution {
    private int MOD = 1000000007;
    private int[][][] memo;
    public int numberOfSets(int n, int k) {
        memo = new int[n + 1][k + 1][2];
        for (int[][] plane : memo) {
            for (int[] row : plane) {
                Arrays.fill(row, -1);
            }
        }
        return solve(0, k, 0, n);
    }
    private int solve(int i, int k, int isDrawing, int n) {
        if (k == 0) return 1;
        if (i >= n) return 0;
        if (memo[i][k][isDrawing] != -1) {
            return memo[i][k][isDrawing];
        }
        long ways = 0;
        if (isDrawing == 1) {
            ways = (ways + solve(i + 1, k, 1, n)) % MOD;
            ways = (ways + solve(i, k - 1, 0, n)) % MOD;
        } else {
            ways = (ways + solve(i + 1, k, 0, n)) % MOD;
            ways = (ways + solve(i + 1, k, 1, n)) % MOD;
        }
        return memo[i][k][isDrawing] = (int) ways;
    }
}