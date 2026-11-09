class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] counts = new int[k];
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        for (int i = 0; i < arrival.length; i++) {
            int currentArrival = arrival[i];
            int duration = load[i];
            while (!busy.isEmpty() && busy.peek()[0] <= currentArrival) {
                available.add(busy.poll()[1]);
            }
            if (available.isEmpty()) {
                continue; 
            }
            Integer assignedServer = available.ceiling(i % k);
            if (assignedServer == null) {
                assignedServer = available.first(); 
            }
            counts[assignedServer]++;
            available.remove(assignedServer);
            busy.offer(new int[]{currentArrival + duration, assignedServer});
        }
        int maxRequests = 0;
        for (int count : counts) {
            maxRequests = Math.max(maxRequests, count);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (counts[i] == maxRequests) {
                result.add(i);
            }
        }
        return result;
    }
}