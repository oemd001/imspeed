class Solution {
    public int subarraySum(int[] nums, int k) {
        //return brute(nums, k);
        //return nospace(nums, k);
        return hash(nums, k);
    }
    
    
    //This gives you a TLE
    public int brute(int[] nums, int k) {
        int count = 0; 
        for (int start = 0; start < nums.length; start++) {
            for (int end = start + 1; end <= nums.length; end++) {
                int sum = 0; 
                //this begins by narrowing the values down and finding the sum of the values in that particular
                //window manually. 
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                if (sum == k) {
                    count++;
                }
            }
        }
        return count; 
    }
    
    //Really slow but at least no TLE
    public int nospace(int[] nums, int k) {
        int count = 0; 
        for (int start = 0; start < nums.length; start++) {
            int sum = 0; 
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count; 
    }
    
    //hashmap solution
    public int hash(int[] nums, int k) {
        int count = 0; 
        int sum = 0; 
        //this is to count the number of occurences that a particular solution popped up
        HashMap<Integer, Integer> hashmap = new HashMap<>(); 
        //this is an edge case. what if nums[position] is equal to k?
        hashmap.put(0, 1);
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (hashmap.containsKey(sum - k)) {
                //this will add the total number of occurances to the final solution
                count += hashmap.get(sum - k);
            }
            //what this does is increments a potential solution to the hashmap
            hashmap.put(sum, hashmap.getOrDefault(sum, 0) + 1);
        }
        
        return count;
    }
}


//[1, 1, 1] k = 2
/*
sum = 2
3 - 2 = 1
what if sum = k / nums[position] = k
sum - k = 0

map: {-1, 1}; {0, 1}; {1, 1}; {0, 1}

*/
