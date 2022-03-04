class Solution {
    public void rotate(int[][] matrix) {
        solution2(matrix);
    }
    
    //this solution I don't get
    public void solution1(int[][] matrix) {
        if (matrix.length == 1) {
            return;
        }
        
        //hc is the height and column of the matrix, given that they are both the same
        int hc = matrix.length; 
        int temp = 0; 
        
        for (int i = 0; i < (hc + 1) / 2; i++) {
            for (int j = 0; j < hc / 2; j++) {
                temp = matrix[hc - 1 - j][i];
                matrix[hc - 1 - j][i] = matrix[hc - 1 - i][hc - j - 1];
                matrix[hc - 1 - i][hc - j - 1] = matrix[j][hc - 1 - i];
                matrix[j][hc - 1 - i] = matrix[i][j];
                matrix[i][j] = temp; 
            }
            
        }
    }
    
    public void solution2(int[][] matrix) {
        if (matrix.length == 1) {
            return; 
        }
        transpose(matrix);
        reflect(matrix);
    }
    
    public void transpose(int[][] matrix) {
        int len = matrix.length; 
        int temp = 0; 
        
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                temp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = temp; 
            }
        }
    }
    
    public void reflect(int[][] matrix) {
        int len = matrix.length; 
        int temp = 0; 
        
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len / 2; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][len - j - 1];
                matrix[i][len - j - 1] = temp;
            }
        }
    }
}

