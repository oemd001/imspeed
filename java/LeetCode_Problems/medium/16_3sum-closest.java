class Solution {
    public int threeSumClosest(int[] nums, int target) {
        /*
        Approach 1: 
        [5, 5, 5, 5] target = 1
        [-1,2,1,-4]
        
        [-4, -1, 1, 2] target = 1
        -4 + 2 = 2
        -1 + 2 = 1
        -1 + 1 = 0 ==> -1, 1, 2
        
        
            minimum distance between target and the two sum approach to find nearest value
            Once I find the closest value, I basically go through the array and find a value that's
                equal, or has the most minimum distance between target and the array index
                
        [0,0,0] target ==> 1
            return 0; 
        */
        //return two_pointer(nums, target); 
        return binary_search(nums, target); //that's actually more optimized
        
    }
    
    private int two_pointer(int[] nums, int target) {
        if (nums.length == 3) {
            int temp = 0; 
            for (int i = 0; i < nums.length; i++) {
                temp += nums[i];
            }
            return temp;             
        }
        
        Arrays.sort(nums); //ascending order
        
        int left = 0; 
        int right = nums.length - 1; 
        int min = Integer.MAX_VALUE; 
        int leftmost = 0; 
        int rightmost = 0;
        int final_value = 0; 
        
        while (left < right) {
            int temp_value = nums[left] + nums[right];
            leftmost = nums[left];
            rightmost = nums[right];
            if (temp_value > target) {
                right--; 
            }
            else {
                left++; 
            }
        }
        
        target = target - (leftmost + rightmost);
        
        for (int i = 0; i < nums.length; i++) {
            if (min > Math.abs(target - nums[i])) {
                final_value = nums[i];
                min = Math.min(min, Math.abs(target - nums[i]));
            }
        }
        
        return leftmost + rightmost + final_value;
    }
    
    public int binary_search(int[] nums, int target) {
        int diff = Integer.MAX_VALUE;
        int sz = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < sz && diff != 0; ++i) {
            for (int j = i + 1; j < sz - 1; ++j) {
                int complement = target - nums[i] - nums[j];
                var idx = Arrays.binarySearch(nums, j + 1, sz - 1, complement);
                int hi = idx >= 0 ? idx : ~idx, lo = hi - 1;
                if (hi < sz && Math.abs(complement - nums[hi]) < Math.abs(diff))
                    diff = complement - nums[hi];
                if (lo > j && Math.abs(complement - nums[lo]) < Math.abs(diff))
                    diff = complement - nums[lo];
            }
        }
        return target - diff;
    }
} 