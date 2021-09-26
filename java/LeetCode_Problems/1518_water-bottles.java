class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        return first_try(numBottles, numExchange);
    }
    
    private int first_try(int numBottles, int numExchange) {
        if (numBottles < numExchange)    
            return numBottles;
    
        int total = numBottles;
        int n = numBottles;
    
        while(n/numExchange!=0) {
            int a = n/numExchange;
            total+=a;
            int left = n%numExchange;
            n = a+left;
        }
        return total;
    }
}
