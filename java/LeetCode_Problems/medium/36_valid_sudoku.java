class Solution {
    public boolean isValidSudoku(char[][] board) {
        //return fast_method(board);
        return three_hashsets(board);
    }
    
    private boolean fast_method(char[][] board) {
        //this problem set will need a HashSet
        //we are looking for unique values found in columns, rows
        //this is a very good method to run about this: 
            //seperating current_val by row value
            //seperating current_val by column value
            //seperating current_val by the 3x3 constraint. 
        //instead of using three different HashSets, we only used one: which is actually very good because this 
reduces the
        //number of different datastructures declared in our particular solution. 
        
        HashSet<String> seen = new HashSet();
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char current_val = board[i][j];
                if (current_val != '.') {
                    //if you can add (seen.add) a value to the HashMap, it's "true" by default.
                    //because HashSets only allow one instance of each value (aka unique values)
                    //if an "unique value" exists already, it'll return "false", and we can just exit the loop
                    if (!seen.add(current_val + " found in row " + i)) return false; 
                    if (!seen.add(current_val + " found in column " + j)) return false; 
                    if (!seen.add(current_val + " found in sub box " + i/3 + "-" + j/3)) return false; 
                    seen.add(current_val + " found in row " + i);
                    seen.add(current_val + " found in column " + j);
                    seen.add(current_val + " found in sub box " + i/3 + "-" + j/3);
                }
            }
        }
        return true;
    }
    
    private boolean three_hashsets(char[][] board) {
        int N = 9;

        // Use a binary number to record previous occurrence
        int[] rows = new int[N];
        int[] cols = new int[N];
        int[] boxes = new int[N];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                // Check if the position is filled with number
                if (board[r][c] == '.') {
                    continue;
                }
                int val = board[r][c] - '0';
                int pos = 1 << (val - 1);

                // Check the row
                if ((rows[r] & pos) > 0) {
                    return false;
                }
                rows[r] |= pos;

                // Check the column
                if ((cols[c] & pos) > 0) {
                    return false;
                }
                cols[c] |= pos;

                // Check the box
                int idx = (r / 3) * 3 + c / 3;
                if ((boxes[idx] & pos) > 0) {
                    return false;
                }
                boxes[idx] |= pos;
            }
        }
        return true;
    }
}
