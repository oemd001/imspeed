import java.util.HashMap;

//Switch statement: fastest method
//3 ms, 38.9 MB
class Solution {
    public int romanToInt(String s) {//429-->CDXXIX
        int ans = 0, num = 0;
        for (int i = s.length()-1; i >= 0; i--) {
            switch(s.charAt(i)) {
                case 'I': num = 1; break;
                case 'V': num = 5; break;
                case 'X': num = 10; break;
                case 'L': num = 50; break;
                case 'C': num = 100; break;
                case 'D': num = 500; break;
                case 'M': num = 1000; break;
            }
            if (4 * num < ans) 
                ans = ans-num;
            else 
                ans = ans+num;
        }
        return ans;
        
        
    }
}

//Regular map solution: 3 ms; 39.1 MB (memory too large)
/*
class Solution {
    public int romanToInt(String s) {
        int[] map = new int[256];
        map['I'] = 1; 
        map['V'] = 5; 
        map['X'] = 10; 
        map['L'] = 50; 
        map['C'] = 100; 
        map['D'] = 500; 
        map['M'] = 1000;
        int ret = 0, pre = 1;
        for (int i = s.length()-1; i >= 0; --i) {
            int cur = map[s.charAt(i)];
            if (cur < pre) ret -= cur;
            else {
                pre = cur;
                ret += cur;
            }
        }
        return ret;
    }
}
*/

//Hashmap solution: 5 ms, 38.9 MB (not fast enough)
/*
class Solution {
    public int romanToInt(String s) {
        Map <Character, Integer> romanInteger = new HashMap<>(); 
        romanInteger.put('I', 1);
        romanInteger.put('V', 5);
        romanInteger.put('X', 10);
        romanInteger.put('L', 50);
        romanInteger.put('C', 100);
        romanInteger.put('D', 500);
        romanInteger.put('M', 1000);
        
        int n = s.length() - 1;
        int result = romanInteger.get(s.charAt(n)); 
        for (int i = n - 1 ; i >= 0; i--) {
            if (romanInteger.get(s.charAt(i)) >= romanInteger.get(s.charAt(i + 1))) {
                result = result + romanInteger.get(s.charAt(i));
            }
            else {
                result = result - romanInteger.get(s.charAt(i)); 
            }
        }
        return result; 
    }
}
*/
