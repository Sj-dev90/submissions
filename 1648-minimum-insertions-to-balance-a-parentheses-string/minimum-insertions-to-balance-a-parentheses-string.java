class Solution {
    public int minInsertions(String s) {
        int need=0,insert=0;
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c=='(') {
                if(need%2!=0){
                    insert++;
                    need--;
                }
                need+=2;
            }
            else{
                need--;
                if(need<0){
                    insert++;
                    need=1;
                }
            }
        }
        return need+insert;
    }
}