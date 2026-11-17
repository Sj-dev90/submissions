class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        char[] chars = s.toCharArray();
        List<Integer>[] divisors = new ArrayList[n + 1];
        for (int len = 2; len <= n; len++) {
            divisors[len] = new ArrayList<>();
            for (int d = 1; d * d <= len; d++) {
                if (len % d == 0) {
                    if (d < len) divisors[len].add(d);
                    if (d != len / d && len / d < len) divisors[len].add(len / d);
                }
            }
        }
        int INF = 1_000_000;
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cost[i], INF);
            for (int j = i + 1; j < n; j++) {
                int len = j - i + 1;
                int minChanges = INF;
                for (int d : divisors[len]) {
                    int currentChanges = 0;
                    for (int start = 0; start < d; start++) {
                        int left = i + start;
                        int right = j - (d - 1 - start);
                        while (left < right) {
                            if (chars[left] != chars[right]) {
                                currentChanges++;
                            }
                            left += d;
                            right -= d;
                        }
                    }
                    minChanges = Math.min(minChanges, currentChanges);
                }
                cost[i][j] = minChanges;
            }
        }
        int[][] dp = new int[k + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        dp[0][0] = 0;
        for (int p = 1; p <= k; p++) {
            for (int i = p * 2; i <= n; i++) {
                for (int m = (p - 1) * 2; m <= i - 2; m++) {
                    if (dp[p - 1][m] != INF && cost[m][i - 1] != INF) {
                        dp[p][i] = Math.min(dp[p][i], dp[p - 1][m] + cost[m][i - 1]);
                    }
                }
            }
        }
        return dp[k][n];
    }
}