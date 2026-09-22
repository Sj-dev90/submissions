class Solution {
    class Node {
        int totalProd;
        long[] prefixCounts;
        Node(int k) {
            prefixCounts = new long[k];
            totalProd = 1;
        }
    }
    Node[] tree;
    int k;
    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        int n = nums.length;
        this.tree = new Node[4 * n];
        build(0, 0, n - 1, nums);
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int targetX = queries[i][3];
            update(0, 0, n - 1, index, value);
            Node queryResult = query(0, 0, n - 1, start, n - 1);
            result[i] = (int) queryResult.prefixCounts[targetX];
        }
        
        return result;
    }
    private void merge(Node parent, Node left, Node right) {
        parent.totalProd = (left.totalProd * right.totalProd) % k;
        for (int i = 0; i < k; i++) {
            parent.prefixCounts[i] = left.prefixCounts[i];
        }
        for (int i = 0; i < k; i++) {
            if (right.prefixCounts[i] > 0) {
                int newMod = (left.totalProd * i) % k;
                parent.prefixCounts[newMod] += right.prefixCounts[i];
            }
        }
    }
    private void build(int node, int start, int end, int[] nums) {
        if (start == end) {
            tree[node] = new Node(k);
            int modVal = nums[start] % k;
            tree[node].totalProd = modVal;
            tree[node].prefixCounts[modVal] = 1;
            return;
        }
        int mid = start + (end - start) / 2;
        build(2 * node + 1, start, mid, nums);
        build(2 * node + 2, mid + 1, end, nums);
        
        tree[node] = new Node(k);
        merge(tree[node], tree[2 * node + 1], tree[2 * node + 2]);
    }
    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            int modVal = val % k;
            tree[node].totalProd = modVal;
            for (int i = 0; i < k; i++) {
                tree[node].prefixCounts[i] = 0;
            }
            tree[node].prefixCounts[modVal] = 1;
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node + 1, start, mid, idx, val);
        } else {
            update(2 * node + 2, mid + 1, end, idx, val);
        }
        merge(tree[node], tree[2 * node + 1], tree[2 * node + 2]);
    }
    private Node query(int node, int start, int end, int qStart, int qEnd) {
        if (qStart <= start && end <= qEnd) {
            return tree[node];
        }
        int mid = start + (end - start) / 2;
        if (qEnd <= mid) {
            return query(2 * node + 1, start, mid, qStart, qEnd);
        }
        if (qStart > mid) {
            return query(2 * node + 2, mid + 1, end, qStart, qEnd);
        }
        Node leftResult = query(2 * node + 1, start, mid, qStart, qEnd);
        Node rightResult = query(2 * node + 2, mid + 1, end, qStart, qEnd);
        Node mergedResult = new Node(k);
        merge(mergedResult, leftResult, rightResult);
        return mergedResult;
    }
}