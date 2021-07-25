//Used Floyd's Tortoise and Hare Algo
/*
class Solution {
    public int removeElement(int[] nums, int val) {
        //nums is the array. 
        
        if (nums.length == 0) {
            return 0;
        }
        
        if (nums.length == 1 && nums.length == val) {
            return 0;
        }
        int slow = -1;
        
        Arrays.sort(nums);
        
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                slow++;
                nums[slow] = nums[fast];
            }
        }
        return slow + 1; 
    }
}
*/

//Much faster Floyd's Tortoise and Hare Algo
class Solution {
    public int removeElement(int[] nums, int val) {
        int i = 0;
        for(int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }
}
