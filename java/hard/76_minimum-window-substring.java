class Solution {
    public String minWindow(String s, String t) {
        return helper(s, t);
    }
    
    private String helper(String s, String t) {
        //this will also need sliding window algorithm
        //We will need a map: this will allow us to see how many characters we've seen with String t
        //i and j pointer (fast and slow pointer)
        //count = number of unique characters from string t 
        //left and right variable: keeping track of the minimum substring pointers
        //left = 0, right = s.length();
        
        if (s == null || t == null || s.isEmpty() || t.isEmpty()) return "";
        
        Map<Character, Integer> map = new HashMap<>();
        
        //this condition puts the characters in string t on to a map.
        //How this is stored is "unique character" as key and "amounts of time present" as value
        //A – 1; B – 1; C – 1
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        int i = 0, j = 0, count = map.size();
        int left = 0, right = s.length() - 1, min = s.length();
        
        boolean found = false; 
        
        while (j < s.length()) {
            //j is the fast pointer. This iterates through the entire loop. If A B or C is found (see example 1), then
            //we will decrease the count variable by 1. 
            //We keep doing this until count reaches (or is less than) 0. 
            char endChar = s.charAt(j++);
            if (map.containsKey(endChar)) {
                map.put(endChar, map.get(endChar) - 1);
                if (map.get(endChar) == 0) count--;
                
                if (count > 0) continue;
                
                //for this part, this reverses everything above until count is greater than 0
                //the "slower" i pointer will iterate through enverything until count is greater than 0
                while (count == 0) {
                    char startChar = s.charAt(i++);
                    if (map.containsKey(startChar)) {
                        map.put(startChar, map.get(startChar) + 1);
                        if (map.get(startChar) > 0) count++;
                    }
                    
                    //this will be true if one solution is found. 
                    if ((j - i) < min) {
                        left = i;
                        right = j;
                        min = j - i;
                        
                        found = true; 
                    }
                }
                
            }  
        }
        //if found is true, return the substring (left - 1 to right). If found is false, return empty string. 
        return !found ? "" : s.substring(left - 1, right);
    }
}
