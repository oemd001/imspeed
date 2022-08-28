class Solution {
    
    Map<Character, String> map = new HashMap<>(); 
    
    
    public List<String> letterCombinations(String digits) {
        /*
        Some things to consider: 
            1. Every number has their own unique set of characters. (2 --> abc, 3 --> def)
                We might need a hashmap for this
            2. The total length of the resulting values will always be equal to the number of digits pressed
        With those two things to consider, what can we do to return a result?
            1. Map each number to it's set of characters. 
            2. Create two loops that would append each corresponding character to the input character. 
                No: this is too slow. I need to think of another solution
            (We can technically do this dynamically, but I'll attempt this after the naive solution)
        */
        if (digits.length() == 0) return new ArrayList<String>(); 
        List<String> list = new ArrayList<>(); 
        
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi"); 
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        
        
        bt(0, new StringBuilder(), digits, list);
        
        return list; 
    }
    
    public void bt(int index, StringBuilder path, String digits, List<String> list) {
        if (path.length() == digits.length()) {
            list.add(path.toString());
            return; 
        }
        
        String possibleLetters = map.get(digits.charAt(index));
        for (char letter : possibleLetters.toCharArray()) {
            path.append(letter);
            bt(index + 1, path, digits, list);
            path.deleteCharAt(path.length() - 1);
        }
            
    }
}