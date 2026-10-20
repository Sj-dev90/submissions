class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int sumOfSortableK = 0;
        for (int k = 1; k <= n; k++) {
            if (n % k == 0) {
                if (isSortable(nums, k)) {
                    sumOfSortableK += k;
                }
            }
        }
        return sumOfSortableK;
    }
    private boolean isSortable(int[] nums, int k) {
        int prevMax = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i += k) {
            int currentMin = nums[i];
            int currentMax = nums[i];
            int drops = 0;
            for (int j = 0; j < k - 1; j++) {
                if (nums[i + j] > nums[i + j + 1]) {
                    drops++;
                }
                currentMin = Math.min(currentMin, nums[i + j + 1]);
                currentMax = Math.max(currentMax, nums[i + j + 1]);
            }
            if (drops > 1) {
                return false; 
            }
            if (drops == 1 && nums[i + k - 1] > nums[i]) {
                return false; 
            }
            if (currentMin < prevMax) {
                return false;
            }   
            prevMax = currentMax;
        }
        return true;
    }
}