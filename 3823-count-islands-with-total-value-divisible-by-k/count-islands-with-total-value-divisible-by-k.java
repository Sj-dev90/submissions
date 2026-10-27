class Solution {
    public int countIslands(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;
        int validIslandCount = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] > 0) {
                    long islandSum = dfs(grid, i, j);
                    if (islandSum % k == 0) {
                        validIslandCount++;
                    }
                }
            }
        }
        return validIslandCount;
    }
    private long dfs(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0) {
            return 0;
        }
        long currentVal = grid[r][c];
        grid[r][c] = 0;
        long totalSum = currentVal;
        totalSum += dfs(grid, r + 1, c); 
        totalSum += dfs(grid, r - 1, c); 
        totalSum += dfs(grid, r, c + 1); 
        totalSum += dfs(grid, r, c - 1); 
        return totalSum;
    }
}