class Solution {
    public int[] findBuildings(int[] heights) {
        return test(heights);
    }
    
    public int[] test(int[] heights) {
        
        int tallest = heights[heights.length - 1];
        //result[0] = heights.length - 1;
        
        ArrayList<Integer> arr = new ArrayList<>();
        
        arr.add(heights.length - 1);
        
        
        for (int i = heights.length - 2; i >= 0; i--) {
            if (heights[i] > tallest) {
                tallest = heights[i];
                arr.add(i);
            }
        }
        Collections.reverse(arr);
        
        //There has to be a faster way than manual conversion
        int[] result = new int[arr.size() ];
        for (int i = 0; i < arr.size(); i++) {
            result[i] = (int)arr.get(i);
        }
        return result; 
        
    }
}
