class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 1) return 0;
        
        int slow = prices[0];
        int value = -1; 
        
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] <= slow) {
                slow = prices[i];
            }
            if (prices[i] - slow > value) {
                value = prices[i] - slow;
            }
        }
        return value; 
        
    }
}

//edge cases
/*
if length is 1, return 0;
if everything is the same, return 0; 

//notes
will need floyd's turtle and hare algo
*/
