class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new ArrayDeque<>();
        long currentCost = 0;
        long totalSubarrays = 0;
        int R = n - 1;
        for (int L = n - 1; L >= 0; L--) {
            while (!deque.isEmpty() && nums[L] >= nums[deque.peekFirst()]) {
                int poppedIndex = deque.pollFirst();
                int nextIndex = deque.isEmpty() ? R + 1 : deque.peekFirst();
                currentCost += (long) (nums[L] - nums[poppedIndex]) * (nextIndex - poppedIndex);
            }
            deque.addFirst(L);
            while (currentCost > k) {
                int lastLeader = deque.peekLast();
                currentCost -= (nums[lastLeader] - nums[R]);
                R--;
                if (!deque.isEmpty() && R < deque.peekLast()) {
                    deque.pollLast();
                }
            }
            totalSubarrays += (R - L + 1);
        }
        return totalSubarrays;
    }
}