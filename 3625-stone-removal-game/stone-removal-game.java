class Solution {
    public boolean canAliceWin(int n) {
        for(int i=10;i>=1;i--){
            if(i%2==0 && i>n){
                return false;
            }else if(i>n){
                break;
            }
            n-=i;
        }
        return true;
    }
}