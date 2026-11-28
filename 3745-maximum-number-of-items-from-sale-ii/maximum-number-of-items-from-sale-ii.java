class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int minPrice = Integer.MAX_VALUE;
        int maxFactor = 0;
        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }
        int[] cap = new int[n];
        if ((long) n * n <= (long) maxFactor * 20) {
            for (int i = 0; i < n; i++) {
                int count = 0;
                int f = items[i][0];
                for (int j = 0; j < n; j++) {
                    if (i != j && items[j][0] % f == 0) {
                        count++;
                    }
                }
                cap[i] = count;
            }
        } else {
            int[] freq = new int[maxFactor + 1];
            for (int[] item : items) {
                freq[item[0]]++;
            }
            int[] multSum = new int[maxFactor + 1];
            for (int d = 1; d <= maxFactor; d++) {
                if (freq[d] > 0) {
                    for (int m = d; m <= maxFactor; m += d) {
                        multSum[d] += freq[m];
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                cap[i] = multSum[items[i][0]] - 1;
            }
        }
        int[][] groups = new int[n][2];
        for (int i = 0; i < n; i++) {
            groups[i][0] = items[i][1];
            groups[i][1] = cap[i];
        }
        Arrays.sort(groups, (a, b) -> Integer.compare(a[0], b[0]));
        long spent = 0;
        long bundles = 0;
        long ans = budget / minPrice;
        for (int[] g : groups) {
            long price = g[0];
            long count = g[1];
            if (count == 0) continue;
            if (spent + price > budget) break;
            long take = Math.min(count, (budget - spent) / price);
            spent += take * price;
            bundles += take;
            long totalItems = bundles * 2 + (budget - spent) / minPrice;
            ans = Math.max(ans, totalItems);
        }
        return (int) ans;
    }
}