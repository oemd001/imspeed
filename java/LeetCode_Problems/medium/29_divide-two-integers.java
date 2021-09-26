class Solution {
    private static int HALF_INT_MIN = -1073741824;
    public int divide(int dividend, int divisor) {
        //Integer.MAX_VALUE is 2147483647
        //Integer.MIN_VALUE is -2147483648
        return binary_divison(dividend, divisor);
    }
    
    private int binary_divison(int dividend, int divisor) {
        //edge cases, if value is greater than the 32 bit values
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE; 
        }
        if (dividend == Integer.MIN_VALUE && divisor == 1) {
            return Integer.MIN_VALUE; 
        }
        
        int negatives = 2; 
        if (dividend > 0) {
            negatives--;
            dividend = -dividend; 
        }
        if (divisor > 0) {
            negatives--;
            divisor = -divisor; 
        }
        
        int maxBit = 0; 
        while (divisor >= HALF_INT_MIN && divisor + divisor >= dividend) {
            maxBit += 1; 
            divisor += divisor;
        }
        
        int quotient = 0; 
        for (int bit = maxBit; bit >= 0; bit--) {
            if (divisor >= dividend) {
                quotient -= (1 << bit);
                dividend -= divisor; 
            }
            divisor = (divisor + 1) >> 1;
        }
        
        if (negatives != 1) {
            quotient = -quotient;
        }
        return quotient; 
    }
        
    
    private int used_divison(int dividend, int divisor) { 
        if (dividend <= Integer.MIN_VALUE) {
            double temp = dividend;
            double temp2 = divisor; 
            double result = temp/temp2;
            return (int)result; 
        }
        return dividend/divisor;
        }
    }
