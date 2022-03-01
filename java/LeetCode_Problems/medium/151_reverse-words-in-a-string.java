class Solution {
    public String reverseWords(String s) {
        return first(s);
    }
    
    public String first(String s) {
        if (s.length() == 1) {
            return s; 
        }
        
        StringBuilder sb = new StringBuilder();
        String result = "";
        
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetterOrDigit(s.charAt(i))) {
                sb.append(s.charAt(i)); 
            }
            if (!Character.isLetterOrDigit(s.charAt(i)) || i == s.length() - 1) {
                if (sb.length() > 0) {
                    sb.append(" ");
                    result = sb.toString() + result;
                    sb.delete(0, sb.length());
                }
            }
        }
        return result.substring(0, result.length() - 1); 
    }
    
    
}

