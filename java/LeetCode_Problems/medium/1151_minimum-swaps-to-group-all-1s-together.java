class Solution {
    public int minSwaps(int[] data) {
        return first(data);
    }
    
    private int first(int[] data) {
        //Two pointer almost "sliding window" approach
        //There are a few things that we need to do: 
        //first, we need to determine how many ones are in the array
        //this will ultimately determine the size of the "window" that we'll need to look at
        //for example, if there are only 3 ones, we'll only need three ones in the window
        //we'll have a fast pointer and a trailing pointer
        //the fast pointer will basically count the number of ones that exist in the array
        //however, once the fast pointer reaches the amount of ones that have been "counted"
        //we'll need to increment the trailing pointer as well
        
        int one_count = Arrays.stream(data).sum();
        int current_count = 0;
        int max_sum = 0; 
        
        int slow = -1;
        for (int i = 0; i < data.length; i++) {
            current_count = current_count + data[i];
            
            if (i >= one_count) {
                current_count = current_count - data[++slow]; //this subtracts whatever it sees, is a 1 no 
longer in the window? is there another 1 added to the window?
            }
            max_sum = Math.max(max_sum, current_count);
        }
        
        return one_count - max_sum;
    }
        
}
