
//O(1) space
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int N = nums.length;
        int[] answer = new int[N];
        
        answer[0] = 1;
        
        for (int i = 1; i < N; i++) {
            answer[i] = nums[ i - 1] * answer[i - 1];
        }
        
        int R = 1; 
        
        for (int i = N - 1; i >= 0; i--) {
            answer[i] = answer[i] * R;
            R = R * nums[i];
        }
        
 
        return answer;
    }
}



/* O(N) solution
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int N = nums.length;
        int[] left_products = new int[N];
        int[] right_products = new int[N];
        
        int[] answer = new int[N];
        
        left_products[0] = 1;
        right_products[N - 1] = 1;
        
        for (int i = 1; i < N; i++) {
            left_products[i] = nums[ i - 1] * left_products[i - 1];
        }        
        
        for (int i = N - 2; i >= 0; i--) {
            right_products[i] = nums[ i + 1] * right_products[i + 1];
        }
        
        for (int i = 0; i < N; i++) {
            answer[i] = left_products[i] * right_products[i];
        }
        return answer;
    }
}
*/



/* too slow
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] answer = new int[nums.length];
        
        for (int i = 0; i < nums.length; i++) {
            answer[i] = 1;
            for (int j = 0; j < nums.length; j++) {
                if (i != j) {
                    answer[i] = answer[i] * nums[j];
                }
                else {
                    continue;
                }
            }
            
        }
        return answer;
    }
}
*/


//edge cases
/*
No division?
Max value: Integer.MAX_VALUE - 1
Min value: Integer.MIN_VALUE + 1
Can only return the array itself?

notes
If we want O(n) time, we need to pass through this array once. Floyd's algo?


*/
