class Solution {
    public int findPeakElement(int[] nums) {
        return binarysearch(nums);
    }
    
    public int binarysearch(int[] nums) {
        if (nums.length == 1) return 0;
        
        int left = 0; 
        int right = nums.length - 1; 
        int midpoint = 0; 
        
        while (left < right) {
            midpoint = left + (right - left) / 2;
            
            if (nums[midpoint] < nums[midpoint + 1]) {
                left = midpoint + 1; 
            }
            else {
                right = midpoint; 
            }
        }
        return left; 
    }
}

/*
one pass solution O(n)
[1,2,3,1]
 ^ | ^
 cons: it's really slow
 pros: naive
 
 binary search: 
 
 value after the midpoint: greater than the value before the midpoint and the midpoint
 
*/
