class Solution {
    public int[] twoSum(int[] nums, int target) {
        return optimal(nums, target); 
        /*
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] + nums[j] == target && i != j) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {0, 0};
        */
    }
    
    public int[] optimal (int[] nums, int target) {
        //complement, position
        Map<Integer, Integer> map = new HashMap<>(); 
        
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            if (map.containsKey(complement) && i != map.get(complement)) {
                return new int[] {i, map.get(complement)};
            }
        }
        return null; 
    }
}