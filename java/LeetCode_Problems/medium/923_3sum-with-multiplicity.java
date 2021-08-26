class Solution {
    public int threeSumMulti(int[] arr, int target) {
        return three_pointer(arr, target);
    }
    
    private int three_pointer(int[] arr, int target) {
        //will need a hashset, that will tell us if the number that we're looking at already exists
            //it doesn't need to be complicated, we just need to know if the value exists or not
        //constraint is that i < j < k
        //arr[i] + arr[j] + arr[k] must be equal to target number
        //we are to return it 1000000000 + 7 (mod that value)
        //the array isn't going to be sorted every time, so we will need to sort this first
            //or to verify the values, we can just sort it upon entry
        //[1,1,2,2,3,3,4,4,5,5]
        //we can use the two pointer technique?
        
        long count = 0;
        long mod = 1000000000 + 7; 
        Arrays.sort(arr);
        
       for (int i = 0; i < arr.length; i++) {
           int t = target - arr[i]; //this part just simplifies your life, from three pointers to two
           int end = arr.length - 1;
           int start = i + 1;
           
           while (start < end) {
               int comp = arr[end] + arr[start];
               if (comp < t) {
                   start++;
               }
               else if (comp > t) {
                   end--;
               }
               else if (arr[end] != arr[start]) { //this counts the multiplicity of arr[end] and arr[start]
                   //this also basically tells us that there's a match (arr[end] + arr[start] + arr[i] == 
target)
                   int left = 1; 
                   int right = 1;
                   //this tells us how many "duplicates" are present in the array tha makes this work
                   while (start + 1 < end && arr[start] == arr[start + 1]) {
                       left++;
                       start++;
                   }
                   //same thing here
                   while (end - 1 > start && arr[end] == arr[end - 1]) {
                       right++;
                       end--;
                   }
                   //using the information gathered above, we can find the multiplicity of the array values 
                   //to make 8, if there is 4x "6" and 2x "2", we j ust multiply 4x by 2x to get a total of 8
                   count += left * right;
                   count = count % mod;
                   start++;
                   end--;
           }
           else {
               //if arr[end] == arr[start], (unlikely, but a possible scenario), we'll need the formula below 
               //[0, 4, 4, 4] end: 3, start = 1
               //we subtract end from start (then plus 1) (gives us 2)
               //we then subtract end from start (gives us 3)
               //we'll get 3 after we divide all that by 2
               //this formula gives us the multiplicity of values that are of the same value. 
               //tl;dr: this formula basically gives us the range of the values and then based on how many there 
are,
               //it calculates the multiplicity (3, in this case) of the values. pretty genius if you ask me
               count = count + (end - start + 1) * (end - start) / 2;
               count = count % mod; 
               break;
           }
           }   
       }
        
        return (int) count; 
    }
}
