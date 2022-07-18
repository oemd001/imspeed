class Solution {
    public int minFlipsMonoIncr(String s) {
        /*
        Naive solution
        For every 0 that we get after a 1, we need to flip a number
        Example: 
            0 0 0 0 1 0 
            We can get either 0 0 0 0 0 0 
            OR 0 0 0 0 1 1
            
        Algorithm: We need to keep track of the number of 1's we see. 
            Any '0' that counts after is the number of "number of flips" that might need to take place
            
        More complicated solution will require prefix sums
        */
        
        int num_flips = 0; 
        int num_ones = 0; 
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '1') {
                num_ones++; 
            }
            else if (num_ones > 0) {
                num_flips++; 
                num_ones--; 
            }
            
        }
        
        return num_flips; 
        
        
    }
}
