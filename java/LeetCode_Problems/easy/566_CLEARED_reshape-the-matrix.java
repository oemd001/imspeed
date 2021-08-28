class Solution {
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        return helper(mat, r, c);
    }
    
    private int[][] helper(int[][] mat, int r, int c) {
        //r denotes "rows"
        //c denoters "columns"
        //m denotes "rows"
        //n denotes "columns"
        
        //edge cases: if r or c exceed the capacity of said matrix, we want to just return the matrix
        //how to check for these cases? 
            //if r x c is greater than mat[i].length x mat.length, we just return mat
        
        /*
        r = 1; c = 4
        1 2 --> 1 2 3 4
        3 4 --> 
        
        r = 2; c = 2
        1 2 --> 1 2
        3 4 --> 3 4
        
        r = 4; c = 1
        1 2 
        3 4 --> 1
                2
                3
                4
        r = 2; c = 6
        1 2
        3 4
        */
        
        if (r * c > mat.length * mat[0].length || c * r < mat.length * mat[0].length) return mat; 
        int[][] result = new int[r][c];
        int[] arr = new int[mat.length * mat[0].length];
        int count = 0; 
        
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                arr[count] = mat[i][j];
                count++;
            }
        }
        
        
        count = 0; 
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                result[i][j] = arr[count];
                count++;
            }
        }
        return result; 
    }
}
