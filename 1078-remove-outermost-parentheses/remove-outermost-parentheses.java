class Solution {
    public String removeOuterParentheses(String s) {
        int depth=0;int start=0;String res="";
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='(') depth++;
            if(s.charAt(i)==')'){
                depth--;
                if(depth==0){
                    res+=s.substring(start+1,i);
                    start=i+1;
                }
            }
        }
        return res;
    }
}