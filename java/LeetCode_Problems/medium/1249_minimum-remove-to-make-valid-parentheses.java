class Solution {
    public String minRemoveToMakeValid(String s) {
        return me(s);
    }
    
    public String me(String s) {
        //this removes any extra closing parenthesis 
        //))(( <-- That does not work. It's technically balanced but we won't be able to use that as it's not closed
        //String sb = "";
        StringBuilder sb = new StringBuilder();
        int open = 0; //<-- this keeps track of the balance of the string
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                open++;
            }
            else if (c == ')') {
                //what if open is already 0? We can't have an negative value for open
                if (open == 0) continue; //carry on, ignore the extraneous value
                open--;
            }
            //sb += c;
            sb.append(c);
        }
        
        //the loop above takes care of open parens. What about closed parens?
        //String result = "";
        StringBuilder result = new StringBuilder(); 
        for (int i = sb.length() - 1; i >= 0; i--) {
            char c = sb.charAt(i);
            if (c == '(' && open-- > 0) {
                continue; 
            }
            //result += c;
            result.append(c);
        }
        return result.reverse().toString(); 
        //manipulate string with StringBuilder, not string concat
        /*
        String return_val = "";
        for (int i = result.length() - 1; i >= 0; i--) {
            return_val += result.charAt(i);
        }
        return return_val; 
        */
    }
    
    public String test1(String s) {
        StringBuilder sb = new StringBuilder(); 
        int open = 0; //keep track of the number of open parens
        for (char c : s.toCharArray()) { //this looks at every character
            if (c == '(') {
                open++;
            }
            else if(c == ')') {
                //excludes closing parens that come before opening parens
                if (open == 0) {
                    continue; //this just continues on to the loop
                }
                open--; //<-- this keeps balance
            }
            
            sb.append(c);
        }
        
        //handling excess opening braces
        StringBuilder result = new StringBuilder(); 
        for (int i = sb.length() - 1; i >= 0; i--) {
            if (sb.charAt(i) == '(' && open-- > 0) {
                continue; //if there is an exceess open paren, just continue/ignore it
            }
            result.append(sb.charAt(i));
        }
        
        return result.reverse().toString(); 
    }
}

/*
can't have closing paranthesis in the beginning, you'll need to remove it in the beginning
)(, that'll need to go

()( (We don't want to remove the first one, it's balanced)
*/
