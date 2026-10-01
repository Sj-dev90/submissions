class Solution {
    public int removeDuplicates(int[] nums) {
        Arrays.sort(nums);
        Set<Integer> t=new LinkedHashSet<>();
        for(int i:nums) t.add(i);
        int j=0;
        for(int i:t) nums[j++]=i;
        return t.size();
    }
}