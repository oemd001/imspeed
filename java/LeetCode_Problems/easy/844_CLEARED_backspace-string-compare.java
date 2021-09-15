class Solution {
    public boolean backspaceCompare(String s, String t) {
        return twoPointer(s, t);
    }
    
    private boolean buildString(String s, String t) {
        //this will need a stack, and we can check if both items are equal
        
        Stack<Character> s_stack = new Stack<>(); 
        Stack<Character> t_stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char s_char = s.charAt(i);
            
            if (s_char == '#' && s_stack.isEmpty()) {
                //do nothing
            }
            else if (s_char == '#') {
                s_stack.pop();
            }
            else {
                s_stack.push(s_char);
            }
        }
        for (int i = 0; i < t.length(); i++) {
            char t_char = t.charAt(i);
            
            if (t_char == '#' && !t_stack.isEmpty()) {
                t_stack.pop();
            }
            else if (t_char == '#' && t_stack.isEmpty()) {
                //do nothing
            }
            else {
                t_stack.push(t_char);
            }
        }
        if (s_stack.isEmpty() && t_stack.isEmpty()) return true; 
        
         // StringBuilder sb = new StringBuilder();
         // StringBuilder sv = new StringBuilder();
        
        if (t_stack.size() != s_stack.size()) return false;
        
        while (!s_stack.isEmpty()) {
            if (s_stack.peek() != t_stack.peek()) return false; 
             // sb.append(s_stack.peek());
             // sv.append(t_stack.peek());
            
            s_stack.pop();
            t_stack.pop();
        }
        
         // System.out.println(sb.toString());
         // System.out.println(sv.toString());
        
        return true; 
    }
    
    private boolean twoPointer(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1; 
        int s_skip = 0;
        int t_skip = 0; 
        
        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (s.charAt(i) == '#') {
                    s_skip++;
                    i--;
                }
                else if (s_skip > 0) {
                    s_skip--;
                    i--;
                }
                else {
                    break;
                }
            }
            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    t_skip++;
                    j--;
                }
                else if (t_skip > 0) {
                    t_skip--;
                    j--;
                }
                else {
                    break;
                }
            }
            if (i >= 0 && j >= 0 && s.charAt(i) != t.charAt(j)) return false; 
            if ((i >= 0) != (j >= 0)) return false; 
            i--;
            j--;
        }
        return true;
    }
}
