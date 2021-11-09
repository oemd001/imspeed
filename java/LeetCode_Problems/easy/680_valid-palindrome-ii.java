class Solution {
    public boolean validPalindrome(String s) {
        return test(s);
    }
    
    public boolean test(String s) {
        int start = 0; 
        int end = s.length() - 1; 
        
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                //compare the two different substring values
                return helper(s, start + 1, end) || helper(s, start, end - 1);
            }
            
            start++;
            end--;
        }
        return true; 
    }
    
    public boolean helper(String s, int i, int j) {
        //because we truncated the values down, we are starting from the area of disagreement and viewing the values from there
        int start = i; 
        int end = j; 
        
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false; 
            }
            
            start++;
            end--;
        }
        return true; 
    }
}

