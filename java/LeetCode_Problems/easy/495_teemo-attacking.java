class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        return first(timeSeries, duration);
    }
    
    private int first(int[] timeSeries, int duration) {
        // so this reminds me of overlapping values
        // for example, for the first part, [1, 4]
        // there are no overlaps, therefore we needn't worry about the repeating values
        // however, for the second example, there are overlaps. As a result, we will need to worry about 
repeating digits
        // what we can do is we can initialize a hashmap
        // the hashmap will store all the "unique values" that have not been used. which we can initialize a var 
and var++
        // if the values have been used, we can simply ignore it and keep on adding the values until it sees 
another unique value
        
        if (timeSeries.length == 0) return 0;
        
        int total = 0; 
        
        for (int i = 0; i < timeSeries.length - 1; i++) {
            total = total + Math.min(timeSeries[i + 1] - timeSeries[i], duration);
        }        
        return total + duration; 
    }
}
