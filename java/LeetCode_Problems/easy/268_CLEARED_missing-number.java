class Solution {
    public int missingNumber(int[] nums) {
        //return itr(nums);
        //return hash(nums);
        //return bitwise(nums);
        return gauss(nums);
    }
    
    //this is O(n log n) because of the sort
    private int itr(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i]) return i;
        }
        if (nums[nums.length - 1] != nums.length) return nums.length;
        
        return nums.length + 1; 
    }
    
    //this is O(2n), which is O(n). In theory, this "should" be faster because O(n) is till O(n)
    private int hash(int[] nums) {
        Set<Integer> set = new HashSet<>();
        
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        
        for (int i = 0; i < nums.length + 1; i++) {
            if (!set.contains(i)) return i;
        }
        
        return -1; 
        
    }
    
    //this is O(n), this is an XOR operator
    private int bitwise(int[] nums) {
        int missing = nums.length; 
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing; 
    }
    
    //definetly the most understandable, one pass O(n)
    private int gauss(int[] nums) {
        int expected_sum = nums.length * (nums.length + 1) / 2;
        int actual_sum = 0; 
        for (int i = 0; i < nums.length; i++) {
            actual_sum = actual_sum + nums[i];
        }
        
        return expected_sum - actual_sum; 
    }
}
