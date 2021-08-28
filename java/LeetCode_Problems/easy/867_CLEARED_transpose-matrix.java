class Solution {
    public int[][] transpose(int[][] matrix) {
        return helper(matrix);
    }
    private int[][] helper(int[][] matrix) {
        //main diagonal is /
        //secondary diagonal is \
        
        if (matrix.length == 1 && matrix[0].length == 1) return matrix; 
        
        int[][] transpose = new int[matrix[0].length][matrix.length]; //rows columns
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                transpose[j][i] = matrix[i][j];
            }
        }
        
        
        return transpose; 
    }
}
