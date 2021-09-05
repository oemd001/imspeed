class Solution {
    public int compress(char[] chars) {
        
        int i = 0;
        int n = chars.length;
        
        int idx = 0;
        
        while(i<n){
            
            int j = i+1;
            while(j<n && chars[i]==chars[j]){
                
                j++;
            }
            
            chars[idx++] = chars[i];
            String count = Integer.toString(j-i);
            
            if(j-i>1){
                
                for(char c: count.toCharArray()){
                    
                    chars[idx++] = c;
                }
            }
            
            i = j;
        }
        
        return idx;
    }
}
