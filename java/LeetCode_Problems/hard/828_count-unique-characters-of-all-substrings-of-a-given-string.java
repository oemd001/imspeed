class Solution {
    public int uniqueLetterString(String s) {
        /*
        Combinatronics?
        
        HashMap: generate a key/value 
            Key: Character
            Value: ArrayList of Integers (indexes)
            
        According to the problem, we want to find the length of all possible unique 
            substrings
        In the example below, our substring range would be from the first L to the last L
            And we must find the total possible amounts of substrings in between
        L A U G H C L O V E R
        
        */
        
        Map<Character, List<Integer>> map = new HashMap<>(); 
        int sum = 0; 
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.computeIfAbsent(c, key -> new ArrayList<>()).add(i); 
            //^ god tier array manipulation thing
        }
        
        /*
        i == 0, we do left = values.get(i) + 1
            This is because we are trying to find the length of the said words left of that char position
        else we do left = values.get(i) - values.get(i - 1)
            This denotes that there IS a repeat from the left side. Find the total amount of characters
            that can be a substring BEFORE hitting that repeated character
        
        i == values.size() - 1 we do right = s.length() - values.get(i)
            This implies that from the right side, there are no more repeating characters from the right side
            As a result, add up every possible substring length from the right side to the end
        else right = values.get(i + 1) - values(i)
            There is a repeat in character, find the substring length from the current character and substract
            it from the next repeating character
            
        sum = sum + left * right; 
            A simple trick to find all the substring length (sort of like combinatronics?)
        */
        for (List<Integer> values : map.values()) {
            for (int i = 0; i < values.size(); i++) {
                int left = 0; 
                int right = 0; 
                if (i == 0) {
                    left = values.get(i) + 1; 
                }
                else {
                    left = values.get(i) - values.get(i - 1);
                }
                
                if (i == values.size() - 1) {
                    right = s.length() - values.get(i); 
                }
                else {
                    right = values.get(i + 1) - values.get(i);
                }
                
                sum = sum + left * right; 
            }
        }
        return sum; 
    }
}