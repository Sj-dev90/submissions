class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long cost;
        if(need1==0 && need2==0){
            return 0;
        }
        long need=Math.max(need1,need2);
        long cost3=(long)costBoth*need;
        long cost4=(long)cost1*need1+(long)cost2*need2;
        if(need2>need1)
        cost=(long)costBoth*need1+(long)cost2*(need2-need1);
        else
        cost=(long)costBoth*need2+(long)cost1*(need1-need2);
        return Math.min(cost3,Math.min(cost4,cost));
    }
}