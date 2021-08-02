class Solution {
    public boolean isAnagram(String s, String t) {
        int n = s.length() - 1;
        int nn = t.length() - 1;
        
        if (n != nn) return false; 
        
        char ss[] = s.toCharArray();
        char tt[] = t.toCharArray();
        
        Arrays.sort(ss);
        Arrays.sort(tt);
        
        for (int i = 0; i <= n; i++) {
            if (ss[i] != tt[i]) {
                return false; 
            }
        }
        return true; 
        
    }
}

//edge cases
/*
longer words will take longer to search in general. 
can contain one word
if the inputted values are not equal, return false
//notes
if the first letter does not exist, exit the loop
double for loop might take a while
*/
