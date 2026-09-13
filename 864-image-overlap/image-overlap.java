class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int a=0,b=0;
        List<int[]> ol1=new ArrayList<>();
        List<int[]> ol2=new ArrayList<>();
        for(int i=0;i<img1.length;i++){
            for(int j=0;j<img1.length;j++){
                if(img1[i][j]==1){
                    ol1.add(new int[]{i,j});
                }
                if(img2[i][j]==1){
                    ol2.add(new int[]{i,j});
                }
            }
        }
        int count[][]=new int[2*img1.length+1][2*img1.length+1];
        int m=0;
        for (int[] p1:ol1) {
            for (int[] p2:ol2) {
                int dr=p2[0]-p1[0]+img1.length;
                int dc=p2[1]-p1[1]+img1.length;
                count[dr][dc]++;
                m=Math.max(m,count[dr][dc]);
            }
        }
        return m;
    }
}