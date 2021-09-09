import java.util.regex.Matcher; 
import java.util.regex.Pattern;

class Solution {
    public boolean isPalindrome(String s) {
        return toString(s);
    }
    
    private boolean toString(String s) {
        if (s.length() == 1) return true;
        s = s.replaceAll("[^a-zA-Z0-9]", "");
        s = s.toLowerCase();
        //System.out.print(s);
        
        int j = s.length() - 1; 
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char b = s.charAt(j);
            if (c != b) {
                return false; 
            }
            j--;
        }
        return true; 
    }
}
