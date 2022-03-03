class Solution {
    public void setZeroes(int[][] matrix) {
        myAttempt(matrix);
    }
    
    public void attempt1(int[][] matrix) {
        List<List<Integer>> list = new ArrayList<>(); 
        List<Integer> temp = new ArrayList<>(); 
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    temp.add(i);
                    temp.add(j);
                    list.add(new ArrayList(temp));
                    temp.removeAll(temp);
                }
            }
        }
        for (int i = 0; i < list.size(); i++) {
            turn0(list.get(i).get(0), list.get(i).get(1), matrix);
        }
    }
    public void turn0(int x, int y, int[][] matrix) {
        //replace y
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][y] = 0;
        }
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[x][i] = 0; 
        }
        
        
    }
    
    public void optimized(int[][] matrix) {
        Boolean isCol = false; 
        int R = matrix.length;
        int C = matrix[0].length; 
        
        for (int i = 0; i < R; i++) {
            if (matrix[i][0] == 0) {
                isCol = true; 
            }
            for (int j = 1; j < C; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0; 
                    matrix[i][0] = 0; 
                }
            }   
        }
        
        //skip index 0 as that is reserved specifically for isCol
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        
        //corner case
        if (matrix[0][0] == 0) {
            for (int j = 0; j < C; j++) {
                matrix[0][j] = 0;
            }
        }
        
        
        if (isCol) {
            for (int i = 0; i < R; i++) {
                matrix[i][0] = 0; 
            }
        }
    }
    
    public void myAttempt(int[][] matrix) {
        Boolean flag = false; 
        
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                flag = true; 
            }
            
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0; 
                }
            }
        }
        
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0; 
                }
            }
        }
        
        if (matrix[0][0] == 0) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0; 
            }
        }
        
        if (flag) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0; 
            }
        }
    }
    
}

