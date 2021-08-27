class Solution {
    int[][] directions = new int[][]{{0,-1},{0,1},{1,0},{-1,0}};
    boolean[][] visited;
    public int[][] updateMatrix(int[][] mat) {
        return dp(mat);
    }
    
    //this one passses all test cases, but takes too long
    private int[][] brute(int[][] mat) {
        //this reminds me of the "sunken island" question
        //might recursion be a good idea here?
        
        //if we see a 1, we ought to look up, down, left and right
            //left will be it's own thing
            //right will be it's own thing
            //up will be it's own thing
            //down will be it's own thing
        
        //"edge" cases: the steps could be a combination of left/right/up/down steps
        //perhaps we should identify the closest "0" in the matrix
            //instead of using a "manual" step process, we can just find the eucledian distance between those 
points
        //we are only allowed to go horizontal or vertical, so we can use teh eucledian distance to find those 
points
        //eucledian formula: dist = abs(x2 - x1) + abs(y2 - y1);
        
        if (mat.length == 1 && mat[0].length == 1) return mat;
        
        
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        int z = 0; 
        
        //find 0
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 0) {
                    List<Integer> temp = new ArrayList<Integer>();
                    temp.add(i);
                    temp.add(j);
                    list.add(new ArrayList(temp));
                }
            }
        }
        
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                int min = Integer.MAX_VALUE;
                if (mat[i][j] == 1) {
                    for (int k = 0; k < list.size(); k++) {
                        int x = list.get(k).get(0);
                        int y = list.get(k).get(1);
                        int calc = Math.abs(i - x) + Math.abs(j - y);
                        min = Math.min(min, calc);
                    }
                    mat[i][j] = min;
                }
            }
        }
        return mat;
    }
    
    //this one passes all test cases, but also takes too long
    private int[][] helper(int [][] mat) {
        int rows = mat.length, cols = mat[0].length;
        visited = new boolean[rows][cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(mat[i][j]==0){
                    dfs(mat,i,j,rows,cols,0);
                }
            }
        }
        return mat;
    }
    private void dfs(int[][] mat, int i, int j, int rows, int cols, int level){
        if(i < 0 || i >= rows || j < 0 || j >= cols || (mat[i][j]==0 && level != 0)){
            return ;
        }    
        
        if(visited[i][j] && mat[i][j] <= level){
            return;
        }
        mat[i][j] = level;
        visited[i][j] = true;
        for(int k=0;k<4;k++){
            dfs(mat,i+directions[k][0], j+directions[k][1], rows,cols,level+1);
        }
    }
    
    //DYNAMIC PROGRAMMING--works goodness me
    private int[][] dp (int[][] mat) {
    int R = mat.length, C = mat[0].length;
	// since diagonal doesn't count, the longest path is really the length of the matrix, minus itself and 
overlapped
	// given that there will always be a zero, this value can be anything big enough.
	int max = R + C - 2;
	int[][] ans = new int[R][C];
	// scan left to right, top to bottom
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			if (mat[i][j] == 0) {
				continue;
			}
			if ((i - 1 >= 0 && mat[i - 1][j] == 0) || (j - 1 >= 0 && mat[i][j - 1] == 0)) {
				ans[i][j] = 1;
			} else {
				ans[i][j] = 1 + Math.min(i - 1 >= 0 ? ans[i - 1][j] : max,
										 j - 1 >= 0 ? ans[i][j - 1] : 
max);
			}
		}
	}
	// scan bottom to top, right to left
	for (int i = R - 1; i >= 0; i--) {
		for (int j = C - 1; j >= 0; j--) {
			if (mat[i][j] == 0) {
				continue;
			}
			if ((i + 1 < R && mat[i + 1][j] == 0) || (j + 1 < C && mat[i][j + 1] == 0)) {
				ans[i][j] = 1;
			} else {
				int dist = 1 + Math.min(i + 1 < R ? ans[i + 1][j] : max,
										j + 1 < C ? ans[i][j + 1] : 
max);
				// combine result
				ans[i][j] = Math.min(ans[i][j], dist);
			}
		}
	}
	return ans;
    }
}
