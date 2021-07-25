class Solution {
    public int lengthOfLastWord(String s) {
        String first = Character.toString(s.charAt(0));
        if (first.equals(" ") && s.length() == 1) {
            return 0; 
        }
        
        int count = 0; 
        for (int i = s.length() - 1; i >= 0; i--) {
            String compare = Character.toString(s.charAt(i));
            if (!compare.equals(" ")) {
                count++;
            }
            else if (count > 0 && (compare.equals(" ") || i == 0)) {
                return count; 
            }
        }
        return count;
    }
}
/*
test cases: temp = 4
asdf asdfg
*/
