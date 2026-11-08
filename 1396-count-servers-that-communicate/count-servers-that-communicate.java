class Solution {
    public int countServers(int[][] grid) {
        int count=0;
        int rowcount[]=new int[grid.length];
        int coloumncount[]=new int[grid[0].length];
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    rowcount[i]++;
                    coloumncount[j]++;
                }
            }
        }
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    if(rowcount[i]>1 || coloumncount[j]>1){
                        count++;
                    }
                }
            }
        }
        return count;
    }
}