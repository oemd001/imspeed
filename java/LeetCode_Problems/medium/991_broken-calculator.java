class Solution {
    public int brokenCalc(int startValue, int target) {
        /*
        Notes: 
            Naive method would be creating a binary search tree
                (Two possible solutions for multiplication or subtraction)
                Creating a binary search tree would yield O(|V| * |E|) time
                    V = number of vertex and E = number of edges
            Greedy solution
                If we work backwards to find the solution
                    Multiplication amplifies subtraction
                    Division amiplifies addition
                    We should always use multiplication/division and use addition/subtraction to fine tune
        
        */
        return greedy_solution(startValue, target);
    }
    
    public int greedy_solution(int startValue, int target) {
        
        int operation_count = 0; 
        
        while (target > startValue) {
            operation_count++; 
            if (target % 2 == 0) {
                target = target / 2; 
            }
            else {
                target++; 
            }
        }
        
        /*
        We do this because we are essentially starting backwards, and from the 
        "overshoot" perspective. With that being said, it would make more sense for us
        to return the operation count + the difference in overshoot between startValue and target
        */
        return operation_count + (startValue - target);
    }
} 


