class Solution {
    class Interval {
        int start, end, weight, index;
        Interval(int s, int e, int w, int idx) {
            this.start = s; 
            this.end = e; 
            this.weight = w; 
            this.index = idx;
        }
    }
    class Result {
        long weight;
        List<Integer> indices;
        Result(long weight, List<Integer> indices) {
            this.weight = weight;
            this.indices = indices;
        }
    }
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Interval[] arr = new Interval[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Interval(
                intervals.get(i).get(0), 
                intervals.get(i).get(1), 
                intervals.get(i).get(2), 
                i
            );
        }
        Arrays.sort(arr, (a, b) -> {
            if (a.start != b.start) return Integer.compare(a.start, b.start);
            return Integer.compare(a.index, b.index);
        });
        Result[][] memo = new Result[n][5];
        Result res = dp(0, 4, arr, memo);
        int[] ans = new int[res.indices.size()];
        for (int i = 0; i < res.indices.size(); i++) {
            ans[i] = res.indices.get(i);
        }
        return ans;
    }
    private Result dp(int i, int k, Interval[] arr, Result[][] memo) {
        if (k == 0 || i == arr.length) {
            return new Result(0L, new ArrayList<>());
        }
        if (memo[i][k] != null) {
            return memo[i][k];
        }
        Result skip = dp(i + 1, k, arr, memo);
        int nextIdx = findNext(arr, arr[i].end, i + 1, arr.length - 1);
        Result takeNext = dp(nextIdx, k - 1, arr, memo);
        long takeWeight = arr[i].weight + takeNext.weight;
        List<Integer> takeIndices = new ArrayList<>(takeNext.indices);
        takeIndices.add(arr[i].index);
        Collections.sort(takeIndices); 
        Result take = new Result(takeWeight, takeIndices);
        Result best;
        if (take.weight > skip.weight) {
            best = take;
        } else if (skip.weight > take.weight) {
            best = skip;
        } else {
            best = getLexicographicallySmaller(take, skip);
        }

        memo[i][k] = best;
        return best;
    }
    private int findNext(Interval[] arr, int targetEnd, int left, int right) {
        int ans = arr.length;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid].start > targetEnd) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
    private Result getLexicographicallySmaller(Result r1, Result r2) {
        List<Integer> l1 = r1.indices;
        List<Integer> l2 = r2.indices;
        for (int i = 0; i < Math.min(l1.size(), l2.size()); i++) {
            int cmp = Integer.compare(l1.get(i), l2.get(i));
            if (cmp < 0) return r1;
            if (cmp > 0) return r2;
        }
        return l1.size() < l2.size() ? r1 : r2;
    }
}