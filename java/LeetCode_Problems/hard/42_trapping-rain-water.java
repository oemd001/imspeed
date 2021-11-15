class Solution {
    public int trap(int[] height) {
        return test(height);
    }
    
    public int test(int[] height) {
        if (height.length == 1 || height.length == 2) return 0;
        
        int left = 0; 
        int right = height.length - 1; 
        int left_max = 0; 
        int right_max = 0; 
        int result = 0; 
        
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] > left_max) {
                    left_max = height[left];
                }
                else {
                    result += (left_max - height[left]);
                }
                ++left; 
            }
            else {
                if (height[right] > right_max) {
                    right_max = height[right];
                }
                else {
                    result += (right_max - height[right]);
                }
                --right;
            }
        }
        return result; 
    }
}


/*
brute force solution: 
[0,1,0,2,1,0,1,3,2,1,2,1]
         ^.| ^
         
two pointer solution: 
[0,1,0,2,1,0,1,3,2,1,2,1]
 
[1,2]
[1,2]

*/

