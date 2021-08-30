class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        return optimize(matrix);
    }
    private boolean slow(int[][] matrix) {
        //we are to check if the matrix values are diaganol
        //this reminds me of the "add values" to the next diagnol 
        
        //logic to do this:
        /*
        we can just iterate from the top and iterate from the left area
        because there is only one diaganol we need to worry about, we just need to 
        do i + 1 and j + 1 where i is the rows value and j is the columns value
        */
        
        //code that would check the top iteration
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i > 0 && j > 0 && matrix[i - 1][j - 1] != matrix[i][j]) return false; 
            }
        }
        return true;
    }
    
    private boolean optimize(int[][] matrix) {
        for (int i = 0; i < matrix[0].length; i++) {
            int j = 0; 
            if (i > 0 && j > 0 && i < matrix[0].length && j < matrix.length && matrix[i][j] != matrix[i - 1][j - 
1] ) return false; 
            j++;
        }
        for (int i = 1; i < matrix.length; i++) {
            int j = 0; 
            if (i > 0 && j > 0 && i < matrix[0].length && j < matrix.length && matrix[i][j] != matrix[i - 1][j - 
1]) return false; 
            j++;
        }
        return true; 
    }
}
