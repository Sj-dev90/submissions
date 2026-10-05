class Solution {
    public int scoreOfParentheses(String s) {
        int n=0;int j=0;int d=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='(') ++n;
            if(s.charAt(i)==')') --n;
            if(n==0 && i!=0){
                d+=calculate(s.substring(j,i+1));
                j=i+1;
            }
        }
        return d;
    }
    private int calculate(String s){
        if(s.length()==2){
            return 1;
        }
        return 2*scoreOfParentheses(s.substring(1,s.length()-1));
    }
}