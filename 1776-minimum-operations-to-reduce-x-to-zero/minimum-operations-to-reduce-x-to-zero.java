class Solution {
    public int minOperations(int[] nums, int x) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        int target = totalSum - x;
        if (target == 0) return nums.length;
        if (target < 0) return -1;
        int maxLength = -1;
        int curr = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            curr += nums[right];
            while (curr > target && left <= right) {
                curr -= nums[left];
                left++;
            }
            if (curr == target) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }
        return maxLength == -1 ? -1 : nums.length - maxLength;
    }
}