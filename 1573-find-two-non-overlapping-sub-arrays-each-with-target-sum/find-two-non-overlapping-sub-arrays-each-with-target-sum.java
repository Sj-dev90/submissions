class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n=arr.length;
        int left=0;
        int minlen[]=new int[n];
        int best=Integer.MAX_VALUE;
        int curr=0;
        int mintl=Integer.MAX_VALUE;
        Arrays.fill(minlen,Integer.MAX_VALUE);
        for(int right=0;right<n;right++){
            curr+=arr[right];
            while(curr>target && left<=right){
                curr-=arr[left];
                left++;
            }
            if(curr==target){
                int currlen=right-left+1;
                if(left>0 && minlen[left-1]!=Integer.MAX_VALUE){
                    mintl=Math.min(mintl,currlen+minlen[left-1]);
                }
                best=Math.min(currlen,best);
            }
            minlen[right]=best;
        }
        return mintl==Integer.MAX_VALUE?-1:mintl;
    }
}