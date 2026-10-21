class Solution {
    public int minOperations(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        char[] sortedArr = s.toCharArray();
        Arrays.sort(sortedArr);
        int L = 0;
        while (L < n && arr[L] == sortedArr[L]) {
            L++;
        }
        if (L == n) {
            return 0;
        }
        if (n == 2) {
            return -1;
        }
        int R = n - 1;
        while (R >= 0 && arr[R] == sortedArr[R]) {
            R--;
        }
        if (R - L + 1 < n) {
            return 1;
        }
        char minChar = sortedArr[0];
        char maxChar = sortedArr[n - 1];
        
        int minCount = 0;
        int maxCount = 0;
        for (char c : arr) {
            if (c == minChar) minCount++;
            if (c == maxChar) maxCount++;
        }
        if (arr[0] == maxChar && maxCount == 1 && arr[n - 1] == minChar && minCount == 1) {
            return 3; 
        }
        return 2;
    }
}