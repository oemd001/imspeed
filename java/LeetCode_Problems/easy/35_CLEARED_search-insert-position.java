//0 ms runtime; 40.7 MB Memory

class Solution {
    public int searchInsert(int[] nums, int target) {
        int startIndex = 0; 
        int lastNum = 0; 
        if (nums.length == 1 ) {
            lastNum = nums[0];
        }
        else {
            lastNum = nums[nums.length - 2];
        }
        
        if (lastNum > target) {
            for (int i = startIndex; i < nums.length - 1; i++) {
                if (target < nums[i] && target != nums[i]) {
                    return i; 
                }
                else if (target == nums[i]) {
                    return i; 
                }
            }
        }
        else {
            startIndex = nums.length - 1; 
            for (int i = startIndex; i >= 0; i--) { 
                if (target > nums[i] && target != nums[i]) {
                    return i + 1; 
                }
                else if (target == nums[i]) {
                    return i; 
                }
            }
        }
        return 0; 
    }
}



