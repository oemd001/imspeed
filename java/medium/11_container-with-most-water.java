class Solution {
    public int maxArea(int[] height) {
        return fast(height);
    }
    
    private int fast(int[] height) {
        int left = 0; 
        int right = height.length - 1; 
        int result = 0; 
        
        while (left < right) {
            result = Math.max(result, Math.min(height[left], height[right]) * (right - left));
            
            if (height[left] < height[right]) left++;
            else right--;
        }
        return result; 
    }
    
    private int naive(int[] height) {
        //to find the area of the container, we will need the formula:
        //min * abs(left - right)
        int left = 0; 
        int right = 0; //start from height.length
        int result = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = 1; j < height.length; j++) {
                result = Math.max(result, (j - i) * Math.min(height[i], height[j]));
                /* This chunk of code below does the exact same thing above. 
                right = height[i];
                left = height[j];
                
                int val = Math.abs(i - (j));
                int temp = val * Math.min(left, right) ;
            
                
                if (temp > result) {
                    result = temp; 
                }
            */
            }
        }
        return result; 
    }
}

