class Solution {
    public int search(int[] nums, int target) {
        //target would be equal to the number it's trying to find
        //nums would be the list of numbers
        
        //nums.length will never be null, but it's minimum value can be 1. Maximum length could be 5000
        //the values of nums[i] could range from -10^4 to 10^4
        //the values of target could also range from the aforementioned value
        
        //first method: use indexOf, and return solution. (I think that's cheating though)
        //However, that's not very fast and that's slow. 
        
        //second method that comes to mind is using a for loop to iterate through the entire nums array
        //However, that's O(n) time
        
        //third method that comes to mind is using binary search to look through the array. 
        //I think that's O(log n) time
        
        if (nums.length == 0) return -1;
        if (nums.length == 1 && target != nums[0]) return -1;
        if (nums.length == 1 && target != nums[0]) return -1; 
        if (nums[0] == target) return 0;
        
        int left = 0; 
        int right = nums.length - 1;
        
        while (left < right) { //this loop finds the smallest element in the array
            int midpoint = left + (right - left) / 2;
            if (nums[midpoint] > nums[right]) { //is the middle element greater than the last element in the array? if it is, that's weird because that never happens, unless it's a rotated array. 
                left = midpoint + 1;
            }
            else {
                right = midpoint;  
            }
            
        }
        
        int start = left; 
        left = 0; 
        right = nums.length - 1;
        
        if (target >= nums[start] && target <= nums[right]) {
                left = start; 
        }
        else {
            right = start; 
        }
            
        while (left <= right) {
            int midpoint = left + (right - left) / 2;
            if (nums[midpoint] == target) return midpoint; 
            else if (nums[midpoint] < target) {
                left = midpoint + 1;
            }
            else {
                right = midpoint - 1;
            }
            
        }
        return -1;
    }
}

/*
[4,5,6,7,0,1,2]
[5,6,7,0,1,2,4]
[0,1,2,4,5,6,7]
0
*/
