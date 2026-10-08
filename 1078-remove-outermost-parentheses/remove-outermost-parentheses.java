class Solution {
    public String removeOuterParentheses(String s) {
        int depth=0;
        StringBuilder sb=new StringBuilder();
        for(char c:s.toCharArray()){
            if(c=='(' && depth==0){
                depth++;
            }else if(c=='('){
                depth++;
                sb.append(c);
            }
            if(c==')'){
                depth--;
                if(depth>0){
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}