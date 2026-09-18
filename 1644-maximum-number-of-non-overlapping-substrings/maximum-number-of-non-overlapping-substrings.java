class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] firstOccurrence = new int[26];
        int[] lastOccurrence = new int[26];
        Arrays.fill(firstOccurrence, -1);
        for (int i = 0; i < n; i++) {
            int charIndex = s.charAt(i) - 'a';
            if (firstOccurrence[charIndex] == -1) {
                firstOccurrence[charIndex] = i;
            }
            lastOccurrence[charIndex] = i;
        }
        List<String> result = new ArrayList<>();
        int right = -1;
        for (int i = 0; i < n; i++) {
            if (i == firstOccurrence[s.charAt(i) - 'a']) {
                int end = checkSubString(s, i, firstOccurrence, lastOccurrence);
                if (end != -1) {
                    if (i > right) {
                        result.add(s.substring(i, end + 1));
                    } else {
                        result.set(result.size() - 1, s.substring(i, end + 1));
                    }
                    right = end; 
                }
            }
        }
        return result;
    }
    private int checkSubString(String s, int i, int[] firstOccurrence, int[] lastOccurrence) {
        int end = lastOccurrence[s.charAt(i) - 'a'];
        for (int j = i; j <= end; j++) {
            int charIndex = s.charAt(j) - 'a';
            if (firstOccurrence[charIndex] < i) {
                return -1;
            }
            end = Math.max(end, lastOccurrence[charIndex]);
        }
        return end;
    }
}