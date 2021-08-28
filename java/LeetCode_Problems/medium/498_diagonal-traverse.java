class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        return optimize(mat);
    }
    
    private int[] helper(int[][] mat) {
        //the first value (pos 0, 0) will always be the first value of the array
        //as a result, we will always go down first, and down values are always odd
        //up values will always be even
        
        //we will need to traverse through the entire array
        //after we start from pos 0,0 we will need to snake our way down, until we reach the corner
        //cases
        /* down cases: 
        (how to get from (0,1) to (1,0)) or 
        because we are going down, we will need to +1 for row
        because we are going left, we will need to -1 for column
        
        up cases: 
        (how to get from (2,0) to (1,1)) 
        because we are going back up, we need to -1 for row
        because we are going right, we need to +1 for column
        */
        int[] result = new int[mat.length * mat[0].length];
        
        if (mat.length == 1 && mat[0].length == 1) {
            result[0] = mat[0][0];
            return result; 
        } 
        
        int rows = 0;
        int columns = 0;
        
        
        for (int i = 0; i < result.length; i++) {
            result[i] = mat[rows][columns];
            if ((rows + columns) % 2 == 0) { //this part goes "up"
                if (columns == mat[0].length - 1) {
                    rows++;
                }
                else if (rows == 0) {
                    columns++;
                }
                else {
                    rows--;
                    columns++;
                }
            }
            else { //this part goes down
                if (rows == mat.length - 1) {
                    columns++;
                }
                else if (columns == 0) {
                    rows++;
                }
                else {
                    rows++;
                    columns--;
                }
            }
        }
        return result; 
    }
    
    private int[] optimize(int[][] mat) {
        //handling edge cases
        int[] result = new int[mat.length * mat[0].length];
        if (result.length == 1) {
            result[0] = mat[0][0];
            return result; 
        }
        
        int rows = mat.length; 
        int columns = mat[0].length; 
        int result_row = 0; 
        int result_col = 0; 
        
        for (int i = 0; i < result.length; i++) {
            result[i] = mat[result_row][result_col];
            if ((result_row + result_col) % 2 == 0) {
                if (result_col == columns - 1) {
                    result_row++;
                }
                else if (result_row == 0) {
                    result_col++;
                }
                else {
                    result_row--;
                    result_col++;
                }
            }
            else {
                if (result_row == rows - 1) {
                    result_col++;
                }
                else if (result_col == 0) {
                    result_row++;
                }
                else {
                    result_row++;
                    result_col--;
                }
            }
        }
        return result; 
    }
}          
