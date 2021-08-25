class Solution {
    public String encode(int num) {
        return helper(num);
    }
    
    private String helper(int num) {
        if (num == 0) return "";
        
        return Integer.toBinaryString(num+1).substring(1); //this is just binary, so so nothing much here to see
    }
}

//START AT 4:05, NEED TO FINISH WITHIN 20
//finished @ 4:21
