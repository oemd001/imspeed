class Solution {
    
    public int calculate(String s) {
        return solution(s);
    }
    
    public int solution(String s) {

        if (s == null || s.isEmpty()) return 0;
        int len = s.length();
        Stack<Integer> stack = new Stack<Integer>();
        int currentNumber = 0;
        char operation = '+';
        for (int i = 0; i < len; i++) {
            char currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
            }
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == len - 1) {
                if (operation == '-') {
                    stack.push(-currentNumber);
                }
                else if (operation == '+') {
                    stack.push(currentNumber);
                }
                else if (operation == '*') {
                    stack.push(stack.pop() * currentNumber);
                }
                else if (operation == '/') {
                    stack.push(stack.pop() / currentNumber);
                }
                operation = currentChar;
                currentNumber = 0;
            }
        }
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }
    
    
    public int test(String s) {
        //if (s.length() == 1 || s.length() == 2) return Integer.parseInt(s);
        s = s.replaceAll("\\s", "");
        Stack<Character> stack = new Stack<>(); 

        Map<Character, Integer> map = new HashMap<>(); 
        //when we are placing items on the stack, addition and subtraction goes first. 
        //when we are evaluating, we addition goes last
        map.put('*', 1);
        map.put('/', 1);
        map.put('+', 2);
        map.put('-', 2);
        StringBuilder ss = new StringBuilder(); 
        int count = 0; 
        for (int i = 0; i < s.length(); i++) {
            char b = s.charAt(i);
            if (!Character.isDigit(b)) {
                ss.append(' ');
                ss.append(b);
                count++;
            }
            else {
                ss.append(b);
            }
        }
        if (count == 0) return Integer.parseInt(s);
        //System.out.println(ss.toString());
        
        StringBuilder sb = new StringBuilder(); 
        
        //converting infix to postfix
        s = ss.toString(); 
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                sb.append(c);
            }
            else {
                if (stack.isEmpty()) {
                    stack.add(c);
                }
                else if (map.get(stack.peek()) == 1 && map.get(c) == 2) {
                    sb.append(" ");
                    sb.append(stack.peek());
                    stack.pop();
                    stack.add(c);
                }
                else if (map.get(stack.peek()) == 2 && map.get(c) == 1) {
                    stack.add(c);
                }
                else {
                    sb.append(" ");
                    sb.append(stack.peek());
                    stack.pop();
                    stack.add(c);
                }
            }
        }
        //1*2-3/4+5
        /*
        str: 1 2 3 
        stack: - * 
        */
        
        while (!stack.isEmpty()) {
            sb.append(" ");
            sb.append(stack.peek());
            stack.pop();
        }
        
        s = sb.toString();
        System.out.println(s);
        Stack<Integer> stack1 = new Stack<>(); 
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int n = 0; 
            if (c == ' ') continue;
            else if (!map.containsKey(c)) {
                while (Character.isDigit(c)) {
                    n = n * 10 + (int)(c - '0');
                    i++;
                    c = s.charAt(i);
                }
                stack1.push(n);
            }
            else {
                int val1 = stack1.pop(); 
                int val2 = stack1.pop(); 
                switch(c) {
                    case '+':
                    stack1.push(val2 + val1);
                        //System.out.println(stack1.peek());
                    break;
                       
                    case '-':
                    stack1.push(val2 - val1);
                    break;
                        
                    case '*':
                    stack1.push(val2 * val1);
                    break;
                        
                    case '/':
                    stack1.push(val2 / val1);
                    break;
                }
            }
        }
        return stack1.pop(); 
        
    }
    
}

/*
3 + 2 * 2 infix --> postfix
stack, no parenthesis
('+', '-', '*', '/')
3 2 2 * +
3+5 / 2



str: 5
(house our numbers)
stack: +
(house our specific variables)

*/
