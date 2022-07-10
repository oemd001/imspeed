class Solution {
    public boolean judgeCircle(String moves) {
        int up = 0; 
        int right = 0; 
        
        for (int i = 0; i < moves.length(); i++) {
            char c = moves.charAt(i);
            
            if (c == 'U') up++; 
            else if (c == 'D') up--; 
            else if (c == 'R') right++; 
            else if (c == 'L') right--; 
        }
        
        
        return right == 0 && up == 0; 
    }
}
