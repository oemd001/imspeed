class Solution {
    public int maxProfit(int[] prices) {
        //O(n)
        //this would be considered to be greey
        //what we do is this: instead of finding the optimal day to buy or sell, 
        //we just compare the value to the value + 1
        //what we do next is add the value adjacent (the difference) and we just add everything together
        
        return helper(prices, prices);
    }
    
    private int helper(int[] prices, int[] test) { //this solution only looks for the profit on the next day, not for edge cases like [5,7,100]
        //this would only sell on day two, which would not be correct. 
        if (prices == null || prices.length == 0) return 0;
        
        int max_profit = 0; 
        
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < prices[i + 1]) {
                max_profit = max_profit + prices[i + 1] - prices[i];
            }
        }
        return max_profit; 
    }
}
