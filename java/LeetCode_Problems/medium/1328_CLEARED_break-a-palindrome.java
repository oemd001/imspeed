class Solution {
    public String breakPalindrome(String palindrome) {
        return solution2(palindrome);
    }
    
    // this solution works for goldman sachs
    public String solution1(String palindrome) {
        /*
        Three edge cases: 
            aba: you cannot change that string to a "not a palindrome"
                explanation: the only value you can change is "b" and that will still be a palindrome
                
            aaaa: you cannot change that string to "not a palindrome" because 
                a is already the lexigraphically the smallest value
                
            a: if a is one value, then it's already lexigraphically the smallest and already a palindrome. Thus, 
                impossible to change
        
        */
        if (palindrome.length() == 1) return "";
        
        List<Character> list = new ArrayList<>(); 
        
        for (int i = 0; i < palindrome.length(); i++) {
            char c = palindrome.charAt(i);
            list.add(c);
        }
        
        int count = 0; 
        
        //this checks if all values are the same. If count == list.size(), return "IMPOSSIBLE"
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 'a') count++; 
        }
        count = 0; 
        
        if (count == list.size()) return "";
        
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 'a') count++; 
        }
        
        if (count * 2 == list.size() - 1) return "";
        
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != 'a') {
                list.set(i, 'a');
                String str = "";
                
                for (int j = 0; j < list.size(); j++) {
                    str += list.get(j);
                }
                
                int left = 0; 
                int right = list.size() - 1; 
                
                while (left < right) {
                    if (str.charAt(left) != str.charAt(right)) return str; 
                    left++; 
                    right--; 
                }
            }
        }
        
        return "";
        
        
        
        
        
    }
    
    public String solution2(String palindrome) {
        int length = palindrome.length();
        if (length == 1) { 
            return "";
        }
        // Strings are immutable in Java, convert it into a char array
        char[] palindromeArray = palindrome.toCharArray();
        
        for (int i = 0; i < length / 2; i++) {
            if (palindromeArray[i] != 'a') {
                palindromeArray[i] = 'a';
                return String.valueOf(palindromeArray);
            }
        }
        
        palindromeArray[length - 1] = 'b';
        return String.valueOf(palindromeArray);
    }
    
}
