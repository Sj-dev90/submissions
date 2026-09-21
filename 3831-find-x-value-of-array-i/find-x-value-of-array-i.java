class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] dp = new long[k]; 
        for (int i = 0; i < nums.length; i++) {
            long[] nextDp = new long[k];
            int val = nums[i] % k;
            nextDp[val]++;
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newRemainder = (r * val) % k;
                    nextDp[newRemainder] += dp[r];
                }
            }
            for (int r = 0; r < k; r++) {
                result[r] += nextDp[r];
            }
            dp = nextDp;
        }
        return result;
    }
}