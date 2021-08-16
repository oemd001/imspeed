class Solution {
    public int[] twoSum(int[] nums, int target) {
        if (nums.length == 2) {
            return new int[] {0, 1};
        }
        
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] + nums[i] == target) {
                    return new int[] {j, i};
                }
            }
        }
        return new int[0];
    }
}

/*
class Solution {
    
    public int[] twoSum(int[] nums, int target) {
         if(nums.length == 2) return new int[]{0,1};
        
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[j] + nums[j - i] == target) return new int[] {j, j - i};
            }
        }
        return new int[] {0, 0};
    }
}
*/
