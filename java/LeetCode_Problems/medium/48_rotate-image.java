class Solution {
    public void rotate(int[][] matrix) {
        /*
        lin alg: 
        reflect, transpose
        I hate this problem
        */
        transpose(matrix);
        reflect(matrix);
    }
    private void reflect(int[][] matrix) {
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length / 2; j++) { //we want to divide the columns by two 
                                                            //as we are not interested in the middle columns
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length - j - 1];
                matrix[i][matrix.length - j - 1] = temp; 
            }
        }
    }
    
    private void transpose(int[][] matrix) {
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                int temp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = temp; 
            }
        }
    }
}
