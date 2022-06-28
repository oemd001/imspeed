class Solution {
    public int uniqueLetterString(String s) {
        /*
        Example: ABCA
            In the example for A, the list we'll get will be A -> (0, 3)
            Everything from the left side of A will be 1 (A can count for itself)
            Right side?
                It will be max length of 3 because we DO NOT want to count for the first character
                (As there is a repeat in character). As a result, the final length of the longest character 
                will be 3 from the right side. 
            For A, the total amount of characters will be 3 
                (this is assuming when the index is @ the 0th index: 0)
        
        
        */
        
        Map<Character, List<Integer>> map = new HashMap<>(); 
        int sum = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.computeIfAbsent(c, key -> new ArrayList<>()).add(i); 
            //^ what that does is if the key does not exist, create a new ArrayList of indicies
            //If the key does exist, add in the index to the ArrayList corresponding to the character
        }
        
        for (List<Integer> values : map.values()) {
            for (int i = 0; i < values.size(); i++) {
                int left = 0; 
                int right = 0; 
                
                //Finding the max number of characters on the left side
                if (i == 0) {
                    left = values.get(i) + 1; 
                }
                else {
                    left = values.get(i) - values.get(i - 1);
                }
                
                //Finding the max number of characters on the right side
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

