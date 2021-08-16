class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        //added together, the values of num must be equal to 0
        //there can not be any duplicates [-1, -1, 2], [2, -1, -1] 
        //cannot contain duplciate triplets
        //look through the array, and find two of the same numbers
        //add them together and try to find a value that is equal the opposite of those values
        //i.e.: -1 + -1 (-2), try to find 2
        //when an unique value is found, add them to a hashmap
        
        //Similar method could be used with non "same" values
        //3 + -2 + -1 = 0
        //will need to find two values that will be equal to the those values
        //we might need to sort the array first, ascending order
        //after that, we might need to iterate through the entire loop individually to find the right item
        
        //special cases (try if possible)
        //if the first two numbers are the same in the hashmap, go to the next value.
        
        //we should make this to two sum first
        
        //HashMap<List<List<Integer>>> map = new HashMap<>();
        HashMap<List<Integer>, Integer> map = new HashMap<>();
        List<Integer> temp = new ArrayList<Integer>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length; j++) {
                
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    for (int k = 0; k < nums.length; k++) {
                        if (nums[i] + nums[j] + nums[k] == 0 && k != j && k != i && i != j ) {
                            temp.add(nums[k]);
                            Collections.sort(temp);
                            if (temp.size() == 3 && !map.containsKey(temp)) {
                                map.put(temp, i + j + k);
                                result.add(new ArrayList(temp));
                                temp.removeAll(temp);
                            }
                            else {
                                temp.removeAll(temp);
                            }
                        }
                    }
                    
                //}
            }
        }
        
        return result; 
        
        
        //[-2,0,1,1,2]
        //
    }
}

/*This technically does two sum first

if ((nums[i] + nums[j] == 0 || nums[i] == nums[j] || nums[i] - 1 == nums[j] || nums[j] - 1 == nums[i] || nums[i] + 1 == nums[j] || nums[j] + 1 == nums[i]) && (i != j)) {
}

*/

//edge cases
/*
nums.length could be from 0 to 1000
value could be from -10^5 to 10^5
*/
