class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        //return first(nums, target);
        Arrays.sort(nums);
        int sum = 0; 
        for (int i = 0; i < nums.length - 2; i++) {
            sum = sum + twoSumSmaller(nums, i + 1, target - nums[i]);
        } 
        return sum;
    }
    private int twoSumSmaller(int[] nums, int startIndex, int target) {
        int sum = 0;
        int left = startIndex;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                sum += right - left;
                left++;
            } else {
                right--;
            }
        }
        return sum;
    }
    
    private int first(int[] nums, int target) { //I think this is the brute force method
        //right off the bat, we're going to need some sort of map
        //sorting this ahead of time might make this a lot easier
        //if this adds to target, add that to the hashmap
            //we don't need ".containskey" or something like that, if .add returns a false, we know there is a 
match already
            //and then add that to the arraylist (list)
            //and then, eventually, count++
        //unlike the last time, everything needs to be less than the target value, k
        
        if (nums.length == 1 || nums.length == 0) return 0;
        
        int count = 0; 
        Map<List<Integer>, Integer> map = new HashMap<>(); 
        List<Integer> list = new ArrayList<Integer>(); 
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length; j++) {
                list.add(nums[i]);
                list.add(nums[j]);
                for (int k = 0; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] < target && k != j && k != i && i != j) {
                        list.add(nums[k]);
                        Collections.sort(list);
                        if (list.size() == 3 && !map.containsKey(list)) {
                            map.put(list, i + j + k);
                            count++;
                            list.removeAll(list);
                        }
                        else {
                            list.removeAll(list);
                        }
                    }
                }
            }
        }
        return count; 
    } 
}
