class Solution {
    public String convert(String s, int numRows) {
        return myself(s, numRows);
    }
    
    private String helper(String s, int numRows) {
        //zig zagging, we need to discover a pattern: 
        /*
        numRows = 4
        P     I    N    row 1: 0 6 12       (0 +6 +6)
        A   L S  I G    row 2: 1 5 7 11 13  (1 +4 +2 +4 +2)
        Y A   H R       row 3: 2 4 8 10     (2 +2 +4 +2)
        P     I         row 4: 3 9          (3 +6)
        
        numRows = 3
        P   A   H   N   row 1: 0 4 8 12         (0 +4 +4 +4)
        A P L S I I G   row 2: 1 3 5 7 9 11 13  (1 +2 +3 +2 +3 +2 +3)
        Y   I   R       row 3: 2 6 10           (2 +4 +4)
        
        We can see that there is a common theme here: 2numRows - 2 (2*4 - 2 = 6 steps, 2*3 -2 = 4 steps)
        Another theme we'll need to identify: the letters in between
            It's the similar, we still complete a cycle, but we will need to backtrack x amount back
            Basically, what we do this is complete one cycle. After we complete one cycle, we backtrack back to account for the value in the middle
            e.g.numRows = 3: on row 2, we have 1 (A)
                we add 4. We get our "L". However, we backtrack by 2 to get our "P"
                This formula we can use to find our backtrack values is: n - 2i (where n is our number of steps and i is the interval where the backtracking begins)
        */
        
        if (numRows == 1) return s;
        
        int n = 2 * numRows - 2; 
        int length = s.length();
        char[] c = new char[length];
        int count = 0; 
        
        for (int i = 0; i < numRows; i++) { //this part gives us the number of rows we are going to utilize here
            int stp = n - 2 * i; 
            for (int j = i; j < length; j += n) {
                c[count] = s.charAt(j); //this part literally gives us row 1 (the vertical areas)
                count++;
                if (stp > 0 && stp < n && j + stp < length) { //this part gives us the middle characters. remember, the range must be within 0 to n
                    c[count] = s.charAt(j + stp);             //in addition, the stp > 0 and stp < n part just ensures that we are not interferring with the "column areas" when we want to add the middle values
                    count++;
                }
            }
        }
        return new String(c);
    }
    
    private String myself(String s, int numRows) {
        int n = 2 * numRows - 2; 
        int length = s.length(); 
        
        if (numRows == 1) return s; 
        
        int pos = 0;
        char[] c = new char[length];
        
        for (int i = 0; i < numRows; i++) {
            int mid = n - 2 * i;
            for (int j = i; j < length; j += n) {
                c[pos] = s.charAt(j);
                pos++;
                if (mid > 0 && mid < n && mid + j < length) {
                    c[pos] = s.charAt(j + mid);
                    pos++;
                } 
            }
        }
        return new String(c);
    }

}