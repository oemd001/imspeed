class Solution {
    public int compress(char[] chars) {
        /*
        if there is only one character, leave it as is
        (no compression is necessary)
        
        If there is more than one character: this is the naming convention
        <character value> + <number of characters
        */
        
        //return sanity_check(chars);
        return solution2(chars);
    }
    
    public int sanity_check(char[] chars) {
        Map<Character, Integer> map = new HashMap<>(); 
        
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            }
            else {
                map.put(c, 1);
            }
        }
        
        String str = "";
        
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                str += entry.getKey();
            }
            else {
                str += entry.getKey();
                str += entry.getValue(); 
            }
        }
        
        return str.length(); 
    }
    
    public int solution(char[] chars) {
        /*
        Slow pointer fast pointer solution: 
            Slow pointer will be responsible for modifying allowing the user to modify the respective parts of the array
            Fast pointer will be responsible for counting the number of occurences of a given node
        */
        if (chars.length == 1) return 1; 
        
        int slow_pointer = 0; 
        int fast_pointer = 0; 
        char temp = chars[0];
        int count = 0; 
        int total_value = 0; 
        
        while (fast_pointer < chars.length) {
            if (temp != chars[fast_pointer]) {
                temp = chars[fast_pointer];
                slow_pointer++; 
                String conv = Integer.toString(count);
                int temp_counter = 0;
                
                
                if (count == 1) {
                    count = 0; 
                    total_value += 1; 
                    continue; 
                }
                
                while (temp_counter < conv.length()) {
                    chars[slow_pointer] = conv.charAt(temp_counter);
                    temp_counter++; 
                    slow_pointer++; 
                }
                
                total_value += 1 + conv.length(); 
                slow_pointer = fast_pointer; 
                count = 0; 
                
            }
            else {
                ++count; 
                ++fast_pointer; 
            }
        }
        
        if (count > 1) {
            slow_pointer++; 
            String conv = Integer.toString(count);
            
            int temp_counter = 0; 
            
            while (temp_counter < conv.length()) {
                chars[slow_pointer] = conv.charAt(temp_counter);
                temp_counter++; 
                slow_pointer++; 
            }
            
            total_value += 1 + conv.length(); 
        }
        else {
            total_value += 1; 
        }
        return total_value; 
    }
    
    public int solution2(char[] chars) {
        if (chars.length <= 1) return chars.length; 
        
        int i = 0; 
        int j = 1; 
        
        while (i < chars.length) {
            int count = 1; 
            char c = chars[i];
            
            while (i < chars.length - 1 && chars[i] == chars[i + 1]) {
                count++; 
                i++; 
            }
            
            chars[j - 1] = c; 
            if (count > 1) {
                String num = String.valueOf(count);
                
                for (int k = 0; k < num.length(); k++) {
                    chars[j] = num.charAt(k);
                    j++; 
                }
            }
            i++; 
            j++; 
        }
        
        return j - 1; 
    }
    
}