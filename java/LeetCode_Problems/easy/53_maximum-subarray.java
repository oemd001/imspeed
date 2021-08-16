//Kadane's Algorithm (thanks Nick White)

class Solution {
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        
        int max_sum = nums[0];
        int current_sum = max_sum; 
        
        for (int i = 1; i < nums.length; i++) {
            current_sum = Math.max(current_sum + nums[i], nums[i]);
            max_sum = Math.max(current_sum, max_sum);
        }
        return max_sum; 
    }
}
