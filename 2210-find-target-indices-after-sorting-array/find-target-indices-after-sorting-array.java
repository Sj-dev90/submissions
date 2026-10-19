class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        List<Integer> arr=new ArrayList<>();
        int idx=0;int count=0;
        for(int i=0;i<nums.length;i++){
            if(target>nums[i]) idx++;
            if(target==nums[i]) count++;
        }
        while(count!=0){
            arr.add(idx);
            idx++;
            count--;
        }
        return arr;
    }
}