//amount of time this will take: O(N)

class Solution {
    public boolean isPalindrome(int x) {
        int temp = 0;
        int comp = x;
        int result = 0;
        
        if (x < 0) {
            return false; 
        }
        
        while (x != 0) {
            temp = x % 10; 
            x = x / 10;
            result = result * 10 + temp; 
        }
        
        if (comp == result) {
            return true; 
        }
        else {
            return false; 
        }
    }
}
