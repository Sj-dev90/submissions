class Solution {
    private boolean check(String s){
        StringBuffer sb=new StringBuffer(s);
        sb=sb.reverse();
        if(s.equals(sb.toString())){
            return true;
        }
        return false;
    }
    public int maxPalindromes(String s, int k) {
        if(k==1){return s.length();}
        int j=k-1;
        int c=0;
        for(int i=0;i<=s.length()-k;i++){
            if(j==s.length()){
                j=i+k;
                continue;
            }
            if(s.charAt(i)==s.charAt(j)){
                String l=s.substring(i,j+1);
                if(check(l) && (l.length()==k || l.length()==k+1)){
                    c++;
                    i=j;
                    j=j+k;
                }
                else{i--;j++;}
            }
            else{j++;i--;}
        }
        return c;
    }
}