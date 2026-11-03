class Solution {
    public int minMoves(int[] nums) {
        Arrays.sort(nums);
        int moves=0;
        for(int i:nums){
            moves+=(nums[nums.length-1]-i);
        }
        return moves;
    }
}