class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        return me(nums, k);
    }
    
    public boolean example(int[] nums, int k) {
        if (nums.length == 1) return false; 
        
        Map<Integer, Integer> map = new HashMap<>(); 
        //int sum = 0; 
        int runningSum = 0; 
        map.put(0, -1);
        
        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            //check if sum mod k exists in the map
            if (map.containsKey(runningSum % k)) {
                //len basically denotes that there are at least two elements in the continuous array
                //int len = i - map.get(runningSum % k);
                if (i - map.get(runningSum % k) > 1) return true; 
            }
            else {
                map.put(runningSum % k, i);
            }
        }
        return false; 
    }
    
    public boolean me(int[] nums, int k) {
        if (nums.length == 1) return false; 
        
        Map<Integer, Integer> map = new HashMap<>(); 
        map.put(0, -1);
        
        int sum = 0; 
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            //we want to know if sum % k exists in the map
            
            if (map.containsKey(sum % k)) {
                
                if (i - map.get(sum % k) > 1) return true; 
                
            }
            else {
                map.put(sum % k, i);
            }
        }
        return false; 
    }
}


/*
brute solution
[23,2,4,6,7]

optimal solution: 
hashmap O(n) O(n)
23 % 6 = 5
(23 + 2) % 6 = 3
(23 + 2 + 4) % 6
(23 + 6) % 6
(5 + 0) = 5
return true

[2,4,6,7]

*/
