class Solution {
    public void reverseWords(char[] s) {
        first(s);
    }
    
    public void first(char[] s) {
        if (s.length == 1) {
            return;
        }
        reverseString(s, 0, s.length - 1);
        reverse(s);
    }
    
    public void reverse(char[] s) {
        int start = 0; 
        int end = 0; 
        
        while (start < s.length) {
            while (end < s.length && s[end] != ' ') {
                end++;
            }
            reverseString(s, start, end - 1);
            
            start = end + 1; 
            end++; 
        }
    }
    
    public void reverseString(char[] s, int a_pointer, int b_pointer) {
        char temp = ' ';
        
        while (a_pointer < b_pointer) {
            temp = s[a_pointer];
            s[a_pointer] = s[b_pointer];
            s[b_pointer] = temp;
            
            a_pointer++;
            b_pointer--;
        }
    } 
}

