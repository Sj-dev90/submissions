class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        boolean isInc = true;
        boolean isDec = true;
        int idxZero = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                idxZero = i;
            }
            if (nums[(i + 1) % n] != (nums[i] + 1) % n) {
                isInc = false;
            }
            if (nums[(i + 1) % n] != (nums[i] - 1 + n) % n) {
                isDec = false;
            }
        }
        if (!isInc && !isDec) {
            return -1;
        }
        int minOps = Integer.MAX_VALUE;
        if (isInc) {
            int op1 = idxZero;
            int op2 = 2 + (n - idxZero) % n; 
            minOps = Math.min(minOps, Math.min(op1, op2));
        }
        if (isDec) {
            int op3 = (idxZero + 1) % n + 1;
            int op4 = n - idxZero;
            minOps = Math.min(minOps, Math.min(op3, op4));
        }
        return minOps;
    }
}