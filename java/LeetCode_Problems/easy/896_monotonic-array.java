class Solution {
    public boolean isMonotonic(int[] nums) {
        return naive(nums);
    }
    public boolean naive(int[] nums) {
        boolean increasing = true; 
        boolean decreasing = true; 
        
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) decreasing = false; 
            if (nums[i] > nums[i + 1]) increasing = false; 
        }
        
        return increasing || decreasing; 
    }
}

