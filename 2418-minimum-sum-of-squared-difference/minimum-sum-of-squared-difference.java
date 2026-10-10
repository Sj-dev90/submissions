class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int maxDiff = 0;
        int[] diffs = new int[n];
        for (int i = 0; i < n; i++) {
            diffs[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diffs[i]);
        }
        int[] count = new int[maxDiff + 1];
        for (int diff : diffs) {
            count[diff]++;
        }
        long k = (long) k1 + k2;
        for (int i = maxDiff; i > 0; i--) {
            if (k <= 0) break;
            if (count[i] > 0) {
                long reductions = Math.min((long) count[i], k);
                count[i] -= reductions;
                count[i - 1] += reductions;
                k -= reductions;
            }
        }
        long ans = 0;
        for (int i = 1; i <= maxDiff; i++) {
            if (count[i] > 0) {
                ans += (long) count[i] * (long) i * i;
            }
        }
        return ans;
    }
}