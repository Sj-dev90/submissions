class Solution {
    private static final int MOD = 1000000007;
    private long[] sum1;
    private long[] sum2;
    private long[] lazy;
    public int sumCounts(int[] nums) {
        int n = nums.length;
        sum1 = new long[4 * n];
        sum2 = new long[4 * n];
        lazy = new long[4 * n];
        Map<Integer, Integer> lastSeen = new HashMap<>();
        long totalAns = 0;
        for (int i = 0; i < n; i++) {
            int startRange = lastSeen.getOrDefault(nums[i], -1) + 1;
            int endRange = i;
            update(1, 0, n - 1, startRange, endRange, 1);
            totalAns = (totalAns + sum2[1]) % MOD;
            lastSeen.put(nums[i], i);
        }
        return (int) totalAns;
    }
    private void apply(int node, int start, int end, long val) {
        long len = end - start + 1;
        long valSquared = (val * val) % MOD;
        long term1 = (2 * val * sum1[node]) % MOD;
        long term2 = (valSquared * len) % MOD;
        sum2[node] = (sum2[node] + term1 + term2) % MOD;
        sum1[node] = (sum1[node] + val * len) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }
    private void pushDown(int node, int start, int end) {
        if (lazy[node] != 0) {
            int mid = start + (end - start) / 2;
            apply(node * 2, start, mid, lazy[node]);
            apply(node * 2 + 1, mid + 1, end, lazy[node]);
            lazy[node] = 0;
        }
    }
    private void update(int node, int start, int end, int l, int r, long val) {
        if (start > r || end < l) return;
        if (start >= l && end <= r) {
            apply(node, start, end, val);
            return;
        }
        pushDown(node, start, end);
        int mid = start + (end - start) / 2;
        update(node * 2, start, mid, l, r, val);
        update(node * 2 + 1, mid + 1, end, l, r, val);
        sum1[node] = (sum1[node * 2] + sum1[node * 2 + 1]) % MOD;
        sum2[node] = (sum2[node * 2] + sum2[node * 2 + 1]) % MOD;
    }
}