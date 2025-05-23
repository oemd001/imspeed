class Solution {
    public int lengthOfLongestSubstring(String s) {
        //two pointer
        //one pointer is going to stay at the beginning
        //one pointer is going to move (reminding me of floyd's turtle and hare)
        //this is giving me contains duplicate vibes
        
        int a_pointer = 0; 
        int b_pointer = 0; 
        int max = 0; 
        
        HashSet<Character> hash_set = new HashSet<Character>();
        
        while (b_pointer < s.length()) {
            if (!hash_set.contains(s.charAt(b_pointer))) {
                hash_set.add(s.charAt(b_pointer));
                b_pointer++;
                max = Math.max(hash_set.size(), max);
            }
            else {
                hash_set.remove(s.charAt(a_pointer));
                a_pointer++;
            }
        }
        return max; 
    }
}
