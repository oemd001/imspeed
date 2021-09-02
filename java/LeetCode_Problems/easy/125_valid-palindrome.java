import java.util.regex.Matcher; 
import java.util.regex.Pattern;

class Solution {
    public boolean isPalindrome(String s) {
        return toString(s);
    }
    
    private boolean toString(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
      while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
        i++;
      }
      while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
        j--;
      }

      if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)))
        return false;
    }

    return true;
  }
}
