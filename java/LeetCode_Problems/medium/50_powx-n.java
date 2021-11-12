class Solution {
    public double myPow(double x, int n) {
        //return sanCheck(x, n);
        return iterative_fastPower(x, n);
        //return brute(x, n); 
    }
    public double sanCheck(double x, int n) {
        return Math.pow(x, n);
    }
    
    //this gives a TLE
    public double brute(double x, int n) {
        if (x == 0) {
            if (n != 0) return 0; 
            else return 1; 
        }
        if (x == 1) {
            if (n == 0) return 0;
            else return 1; 
        }
        if (n == 0) {
            return 1; 
        } 
        
        double temp = x; 
            for (int i = 0; i < Math.abs(n) - 1; i++) {
                x = x * temp;
        }
        if (n < 0) {
            return 1/x;
        }
        return x; 
    }
    
    //divide and conquer solution
    public double iterative_fastPower(double x, int n) {
        long N = n; 
        if (N < 0) {
            x = 1 / x; 
            N = -N; 
        }
        
        double ans = 1; 
        double current_product = x; 
        for (long i = N; i > 0; i /= 2) {
            if ((i % 2) != 0) {
                ans = ans * current_product; 
            }
            current_product = current_product * current_product; 
        }
        return ans; 
    }
}

/*
naive way: just multiply the value by n times

*/
