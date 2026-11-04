class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] costAt = new long[n - x + 1];
        SlidingWindowMedian swm = new SlidingWindowMedian(x);
        for (int i = 0; i < x; i++) {
            swm.insert(nums[i]);
        }
        costAt[0] = swm.getCost();
        for (int i = x; i < n; i++) {
            swm.erase(nums[i - x]);
            swm.insert(nums[i]);
            costAt[i - x + 1] = swm.getCost();
        }
        long[] dp = new long[n + 1];
        for (int j = 1; j <= k; j++) {
            long[] nextDp = new long[n + 1];
            Arrays.fill(nextDp, (long) 1e15);
            for (int i = j * x; i <= n; i++) {
                long skipCost = nextDp[i - 1];
                long takeCost = dp[i - x] + costAt[i - x];
                nextDp[i] = Math.min(skipCost, takeCost);
            }
            dp = nextDp;
        }
        return dp[n];
    }
    class SlidingWindowMedian {
        PriorityQueue<Integer> low = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> high = new PriorityQueue<>();
        Map<Integer, Integer> deleted = new HashMap<>();
        long lowSum = 0;
        long highSum = 0;
        int lowSize = 0;
        int highSize = 0;
        int windowSize;
        public SlidingWindowMedian(int windowSize) {
            this.windowSize = windowSize;
        }
        public void insert(int num) {
            if (low.isEmpty() || num <= low.peek()) {
                low.offer(num);
                lowSum += num;
                lowSize++;
            } else {
                high.offer(num);
                highSum += num;
                highSize++;
            }
            balance();
        }
        public void erase(int num) {
            deleted.put(num, deleted.getOrDefault(num, 0) + 1);
            if (!low.isEmpty() && num <= low.peek()) {
                lowSum -= num;
                lowSize--;
            } else {
                highSum -= num;
                highSize--;
            }
            balance();
        }
        private void prune(PriorityQueue<Integer> pq) {
            while (!pq.isEmpty() && deleted.getOrDefault(pq.peek(), 0) > 0) {
                int val = pq.poll();
                deleted.put(val, deleted.get(val) - 1);
            }
        }
        private void balance() {
            prune(low);
            prune(high);
            while (lowSize > highSize + 1) {
                int val = low.poll();
                lowSum -= val;
                lowSize--;
                high.offer(val);
                highSum += val;
                highSize++;
                prune(low);
                prune(high);
            }
            while (lowSize < highSize) {
                int val = high.poll();
                highSum -= val;
                highSize--;
                low.offer(val);
                lowSum += val;
                lowSize++;
                prune(low);
                prune(high);
            }
        }
        public long getCost() {
            long median = low.peek();
            if (windowSize % 2 != 0) {
                return highSum - lowSum + median;
            } else {
                return highSum - lowSum;
            }
        }
    }
}