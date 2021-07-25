/*
class Solution {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x = x / 10;
            //checks if the input value is greater than Integer.MAX_VALUE   
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            //checks if the input value is less than Integer.MAX_VALUE
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8))  {
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
*/

class Solution {
    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int add = x % 10;
            x = x / 10;
            
            //constraints
            if (result > Integer.MAX_VALUE/10 || (result == Integer.MAX_VALUE/10 && add == 7)) {
                return 0;
            }
            if (result < Integer.MIN_VALUE/10 || (result == Integer.MIN_VALUE/10 && add == -8)) {
                return 0;
            }
            
            result = result * 10 + add ;
        }
        return result; 
    }
}

