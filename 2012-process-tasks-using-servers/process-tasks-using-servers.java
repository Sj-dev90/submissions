class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });
        PriorityQueue<long[]> busyServers = new PriorityQueue<>((a, b) -> {
            return Long.compare(a[0], b[0]);
        });
        for (int i = 0; i < servers.length; i++) {
            freeServers.offer(new int[]{servers[i], i});
        }
        int[] ans = new int[tasks.length];
        long currentTime = 0;
        for (int j = 0; j < tasks.length; j++) {
            currentTime = Math.max(currentTime, (long) j);
            if (freeServers.isEmpty()) {
                currentTime = Math.max(currentTime, busyServers.peek()[0]);
            }
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                long[] finishedServer = busyServers.poll();
                int serverIndex = (int) finishedServer[1];
                freeServers.offer(new int[]{servers[serverIndex], serverIndex});
            }
            int[] bestServer = freeServers.poll();
            ans[j] = bestServer[1];
            busyServers.offer(new long[]{currentTime + tasks[j], bestServer[1]});
        }
        return ans;
    }
}