class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxLen = 1;
        for (int i = 0; i < n; i++) {
            long prod = 1;
            long currentGcd = 0;
            long currentLcm = 1;
            for (int j = i; j < n; j++) {
                int val = nums[j];
                if (prod > Long.MAX_VALUE / val) {
                    break;
                }
                long g = (j == i) ? val : gcd(currentGcd, val);
                long lcmGcd = gcd(currentLcm, val);
                if (currentLcm / lcmGcd > Long.MAX_VALUE / val) {
                    break;
                }
                prod *= val;
                currentGcd = g;
                currentLcm = (currentLcm / lcmGcd) * val;
                if (prod == currentLcm * currentGcd) {
                    maxLen = Math.max(maxLen, j - i + 1);
                } else if (j > i) {
                    break;
                }
            }
        }
        return maxLen;
    }
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}