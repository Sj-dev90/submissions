class Solution {
    public int maxDepth(String s) {
        int n=0;
        int max=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                n++;
                max=Math.max(max,n);
            }else if(s.charAt(i)==')'){
                n--;
            }
        }
        return max;
    }
}