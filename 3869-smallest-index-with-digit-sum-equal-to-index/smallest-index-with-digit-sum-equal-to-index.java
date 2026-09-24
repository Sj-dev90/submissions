class Solution {
    public int smallestIndex(int[] nums) {
        for(int i=0;i<nums.length;i++){
            int d=nums[i];
            int j=0;
            while(d!=0){
                j+=d%10;
                d/=10;
            }
            if(j==i){
                return j;
            }
        }
        return -1;
    }
}