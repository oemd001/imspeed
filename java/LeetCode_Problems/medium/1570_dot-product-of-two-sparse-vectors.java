class SparseVector {
    
    ArrayList<int[]> pairs = new ArrayList<>(); 
    
    /*
    SparseVector(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                pairs.add(new int[] {i, nums[i]});
            }
        }
    }
    */
    
    private int[] array; 
    SparseVector(int[] nums) {
        array = nums;
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int result = 0; 
        
        for (int i = 0; i < array.length; i++) {
            result += array[i] * vec.array[i];
        }
        
        return result; 
    }
    
    public int sdotProduct(SparseVector vec) {
        int result = 0; 
        int i = 0; 
        int j = 0; 
        
        while (i < pairs.size() && j < vec.pairs.size()) {
            if (pairs.get(i)[0] == vec.pairs.get(j)[0] ) {
                result += pairs.get(i)[1] * vec.pairs.get(j)[1];
                i++;
                j++;
            }
            else if (pairs.get(i)[0] > vec.pairs.get(j)[0]) {
                j++;
            }
            else {
                i++;
            }
        }
        return result; 
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);

/*
multiplying everything across

pairs(index, value); 
[1,0,0,2,3]

pair(0, 1);
pair(3, 2);
pair(4, 3);

[0,3,0,4,0]
pair(1, 3);
pair(3, 4);

result = 8

*/
