class Solution {
    public int consecutiveNumbersSum(int n) {
        /*
        Consecutive values: 1, 2, 3, 4, 5
        Problem is akin to complete the square formula. 
        Formula to find the solution is: N = x * k + (k * [k + 1])/2
            Once you do that, solve for x
        Math based problem
        */
        return optimized(n);
    }
    public int first_solution(int n ) {
        int count = 0; 
        int upper_limit = (int)(Math.sqrt(2 * n + 0.25) - 0.5);
        
        for (int k = 1; k <= upper_limit; k++) {
            if ((n - k * (k + 1) / 2) % k == 0) count++; 
        }
        return count; 
    }
    
    public int optimized(int n) {
        int count = 0; 
        int upper_limit = (int)(Math.sqrt(2 * n + 0.25) - 0.5);
        
        for (int k = 1; k <= upper_limit; k++) {
            n = n - k; 
            if (n % k == 0) count++; 
        }
        return count; 
    }
}

