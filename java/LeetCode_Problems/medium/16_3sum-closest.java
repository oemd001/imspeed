class Solution {
    public int threeSumClosest(int[] nums, int target) {
        //optimum solution is O(n^2)
        Arrays.sort(nums);
        
        //looping through, and adding two numbers at the same time. We are comparing three numbers. 
        //if the sum - target is the least amount of value, then we will return that value, or update/return that sum. 
        
        int result = nums[0] + nums[1] + nums[nums.length - 1];
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 2; i++) {
            int a_pointer = i + 1; 
            int b_pointer = nums.length -1; 
            
            while (a_pointer < b_pointer) {
                int current_sum = nums[i] + nums[a_pointer] + nums[b_pointer];
                if (current_sum > target) {
                    b_pointer--;
                    //this means that the b_pointer is too large, we will want to decrement the value
                }
                else {
                    a_pointer++;
                    //if it's too small, we want to increase the value of the a_pointer–increase the value. 
                }
                if (Math.abs(current_sum - target) < Math.abs(result - target)) {
                    result = current_sum; 
                }
            }
        }
        return result; 
        
    }
}
