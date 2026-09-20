class Solution {
    public int lengthOfLastWord(String s) {
        int n=0;int d=0;
        for(int i=s.length()-1;i>=0;i--){
            if(s.charAt(i)!=' '){
                n++;
                d=1;
            }else if(s.charAt(i)==' ' && d==1){
                break;
            }
        }
        return n;
    }
}