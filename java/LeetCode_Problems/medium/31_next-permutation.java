class Solution {
    public void nextPermutation(int[] nums) {
        if (nums.length == 1 || nums == null || nums.length == 0) return; 
        
        int i = nums.length - 2; 
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1; 
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1, nums.length - 1);
    }
    
    public void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i++, j--);
        }
    }
    public void swap(int[] nums, int i, int j) { 
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }
}


/*
[1, 2, 5, 6, 4, 3]
[1, 2, 4, 6, 5, 3]
[1, 2, 4, 3, 5, 6]

1. swap the values
    • variables where we point and replace
    • swap function
2. reverse the order
    • recursion?
    • reverse function
    
*/
