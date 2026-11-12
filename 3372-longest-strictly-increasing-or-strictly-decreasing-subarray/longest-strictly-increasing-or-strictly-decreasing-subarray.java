class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int max=0;
        int moves=1;
        for(int i=0;i<nums.length-1;i++){
            if(nums[i]<nums[i+1]){
                moves++;
            }else{
                max=Math.max(max,moves);
                moves=1;
            }
        }
        max=Math.max(max,moves);
        moves=1;
        for(int i=0;i<nums.length-1;i++){
            if(nums[i]>nums[i+1]){
                moves++;
            }else{
                max=Math.max(max,moves);
                moves=1;
            }
        }
        max=Math.max(max,moves);
        return max;
    }
}