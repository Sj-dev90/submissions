class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(new int[]{edge[1], edge[2]});
        }
        int[] minStreak = new int[n];
        Arrays.fill(minStreak, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{0, 0, 1});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int weight = curr[0];
            int u = curr[1];
            int streak = curr[2];
            if (u == n - 1) {
                return weight;
            }
            if (streak >= minStreak[u]) {
                continue;
            }
            minStreak[u] = streak;
            for (int[] neighbor : adj[u]) {
                int v = neighbor[0];
                int edgeWeight = neighbor[1];
                int nextStreak = (labels.charAt(u) == labels.charAt(v)) ? streak + 1 : 1;
                if (nextStreak <= k) {
                    pq.offer(new int[]{weight + edgeWeight, v, nextStreak});
                }
            }
        }
        return -1;
    }
}