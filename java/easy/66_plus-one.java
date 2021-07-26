//Copied Solution: was supposed to be faster but this runs at the same memory capacity as well. 
//Supposed to run at 34.6 MB
class Solution {
    public int[] plusOne(int[] digits) {
        for(int i = digits.length-1; i>=0; i--){
            if(digits[i]!=9){
                digits[i] = digits[i]+1;
                return digits;
            }else{
                digits[i]=0;
            }      
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}

//My solution: 0 ms; 37.4 MB–better than 66.45%
/*
class Solution {
    public int[] plusOne(int[] digits) {
        digits[digits.length - 1] = digits[digits.length - 1] + 1;
        int[] result = new int[digits.length + 1];
        
        if (digits[0] < 9) {
            for (int i = digits.length - 1; i >= 0; i--) {
                if (digits[i] > 9 ) {
                    digits[i] = 0;
                    digits[i - 1] = digits[i - 1] + 1;
                }
            }
            return digits;
        }
        else {
            for (int i = 0; i < digits.length; i++) {
                result[i + 1] = digits[i];
            }
            for (int i = result.length - 1; i >= 0; i--) {
                if (result[i] > 9) {
                    result[i] = 0;
                    result[i - 1] = result[i - 1] + 1;
                }
            }
            if (result[0] == 0) {
                int[] newResult = new int[result.length - 1];
                for (int i = 0; i < newResult.length; i++) {
                    newResult[i] = result[i + 1];
                }
                return newResult;
            }
            return result;
        }
    }
}
*/
