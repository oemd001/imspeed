class Solution {
    public void moveZeroes(int[] nums) {
        brute(nums);
    }
    private void brute(int[] nums) {
        int lastnonzero = 0;
        //this does not need to be sorted
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[lastnonzero++] = nums[i];
            }
        }
        //the code above:
        //[0,1,0,3,12] (1st pos)
        //[1,1,0,3,12] (2nd pos)
        //[1,3,0,3,12] (3rd pos)
        //[1,3,12,3,12] (fourth pos)
        
        for (int i = lastnonzero; i < nums.length; i++) {
            nums[i] = 0;
            //start from position + 1 from the lastnonzero area and add zeros at the end
        }
    }
    
    private void optimal_questionmark(int[] nums) {
       //collections.swap does not work with arrays. This will require a vector declaration, which is illegal in this problem 
    }
}