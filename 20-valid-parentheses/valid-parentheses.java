class Solution {
    public boolean isValid(String s) {
        char a[]=new char[s.length()];
        int t=-1;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='{' || s.charAt(i)=='(' || s.charAt(i)=='['){
                a[++t]=s.charAt(i);
            }
            else if((t!=-1)&&((s.charAt(i)=='}' && a[t]=='{') || (s.charAt(i)==')' && a[t]=='(') || (s.charAt(i)==']' && a[t]=='['))){
                t--;
            }
            else{
                return false;
            }
        }
        if(t==-1){
            return true;
        }
        return false;
    }
}