class Solution {
    public int findKthLargest(int[] nums, int k) {
        return faster(nums, k);
    }
    
    public int naive(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
    
    public int faster(int[] nums, int k) {
        //when we see largest, or smallest, we think HEAP
        //we will throw everything on to the heap and when the heap size exceeds "k", we will yank
        //that's going to keep the smallest value on the root
        //we will have the last k values in our heap
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(); 
        
        for (int i : nums) {
            minHeap.add(i);
            if (minHeap.size() > k) {
                minHeap.remove(); 
            }
        }
        return minHeap.remove();
    }
}

/*
naive: iterate through everything

binary search works only for sorted arrays
**rotate search


*/
