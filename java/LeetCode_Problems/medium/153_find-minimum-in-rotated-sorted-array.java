
//binary search method. O(1) best case scenario, O(n) worst case scenario
class Solution {
    public int findMin(int[] nums) {
        //Will need binary search 
        if (nums.length == 0) return -1;
        if (nums.length == 1) return nums[0];
        int result = Integer.MAX_VALUE;
        
        int left = 0; 
        int right = nums.length - 1;
        while (left < right) {
            int midpoint = left + (right - left) / 2;
            if (midpoint > 0 && nums[midpoint] < nums[midpoint - 1]) {
                return nums[midpoint];
            }
            else if (nums[left] <= nums[midpoint] && nums[midpoint] > nums[right]) {
                left = midpoint + 1;
            }
            else {
                right = midpoint - 1;
            }
        }
                          
        return nums[left];
    }
}

/* Runs in linear time O(n)
class Solution {
    public int findMin(int[] nums) {
        int temp = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (temp >= nums[i]) {
                temp = nums[i];
            }
        }
        return temp;
    }
}
*/
