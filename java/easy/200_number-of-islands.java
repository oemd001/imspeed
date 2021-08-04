class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0; 
        }
        
        
        int count = 0; 
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    count = count + dfs(grid, i, j);
                }
            }
        }
        return count; 
    }
    
    //big O analysis: O(x log x), where x is m • n
    private int dfs(char[][] grid, int i, int j) { //this sets all the 1s in question to 0
        if (i < 0 || i >= grid.length || j < 0 ||  j >= grid[i].length || grid[i][j] == '0') { //the last part basically checks if the number in question is 0. If so, return 0, because there is no island. 
            return 0;
        }
        
        grid[i][j] = '0'; //sets the island in question to 0
        
        //traversal code
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
        return 1; 
    }
}


//edge cases
/*
1 - 300;
m = 1
n = 1

*/

