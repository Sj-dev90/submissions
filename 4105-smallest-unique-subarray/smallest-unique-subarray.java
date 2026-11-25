class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        if (n == 1) return 1;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int uniqueCount = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || sorted[i] != sorted[i - 1]) {
                sorted[uniqueCount++] = sorted[i];
            }
        }
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[i] = Arrays.binarySearch(sorted, 0, uniqueCount, nums[i]);
        }
        int[] sa = new int[n];
        int[] tmp = new int[n];
        int[] cnt = new int[Math.max(n, uniqueCount) + 1];
        for (int i = 0; i < n; i++) cnt[rank[i]]++;
        for (int i = 1; i < uniqueCount; i++) cnt[i] += cnt[i - 1];
        for (int i = n - 1; i >= 0; i--) sa[--cnt[rank[i]]] = i;
        for (int k = 1; k < n; k <<= 1) {
            int p = 0;
            for (int i = n - k; i < n; i++) tmp[p++] = i;
            for (int i = 0; i < n; i++) {
                if (sa[i] >= k) tmp[p++] = sa[i] - k;
            }
            Arrays.fill(cnt, 0, uniqueCount, 0);
            for (int i = 0; i < n; i++) cnt[rank[tmp[i]]]++;
            for (int i = 1; i < uniqueCount; i++) cnt[i] += cnt[i - 1];
            for (int i = n - 1; i >= 0; i--) sa[--cnt[rank[tmp[i]]]] = tmp[i];
            tmp[sa[0]] = 0;
            int classes = 1;
            for (int i = 1; i < n; i++) {
                int cur1 = rank[sa[i]], prev1 = rank[sa[i - 1]];
                int cur2 = (sa[i] + k < n) ? rank[sa[i] + k] : -1;
                int prev2 = (sa[i - 1] + k < n) ? rank[sa[i - 1] + k] : -1;
                if (cur1 != prev1 || cur2 != prev2) classes++;
                tmp[sa[i]] = classes - 1;
            }
            System.arraycopy(tmp, 0, rank, 0, n);
            uniqueCount = classes;
            if (uniqueCount == n) break;
        }
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) pos[sa[i]] = i;
        int[] lcp = new int[n + 1];
        int h = 0;
        for (int i = 0; i < n; i++) {
            if (pos[i] > 0) {
                int j = sa[pos[i] - 1];
                while (i + h < n && j + h < n && nums[i + h] == nums[j + h]) {
                    h++;
                }
                lcp[pos[i]] = h;
                if (h > 0) h--;
            }
        }
        int ans = n;
        for (int k = 0; k < n; k++) {
            int reqLen = Math.max(lcp[k], lcp[k + 1]) + 1;
            if (sa[k] + reqLen <= n) {
                ans = Math.min(ans, reqLen);
            }
        }
        return ans;
    }
}