class Solution {
    public int findJudge(int n, int[][] trust) {
        return helper(n, trust);
        
    }
    
    private int helper(int n, int[][] trust) {
        //this is a map of the amount of people that trust you
        int[] count = new int[n + 1];
        
        for (int[] t : trust) {
            count[t[0]]--;
            count[t[1]]++;
        }
        
        for (int i = 1; i <= n; i++) {
            if (count[i] == n - 1) {
                return i;
            }
        }
        return -1; 
    }
}
