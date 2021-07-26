/*
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder(); 
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0; 
        
        while (i >= 0 || j >= 0) {
            int sum = carry; 
            if (i >= 0) {
                sum = sum + a.charAt(i) - '0';
            }
            if (j >= 0) {
                sum = sum + b.charAt(j) - '0';
            }
            sb.append(sum % 2);
            carry = sum / 2;
            
            i--;
            j--;
        }
        if (carry != 0) {
            sb.append(carry);
            return sb.reverse().toString(); 
        }
        return sb.reverse().toString(); 
    }
}
*/

//basically a copied solution, but I did this myself
class Solution {
    public String addBinary(String a, String b) {
        int a_length = a.length() - 1; 
        int b_length = b.length() - 1;
        StringBuilder sb = new StringBuilder(); 
        int carry = 0;
        
        while (a_length >= 0 || b_length >=0 ) {
            int sum = carry; 
            if (a_length >= 0) {
                sum = sum + a.charAt(a_length) - '0';
            }
            if (b_length >= 0) {
                sum = sum + b.charAt(b_length) - '0';
            }
            
            sb.append(sum % 2);
            carry = sum / 2;
            
            a_length--;
            b_length--;
        }
        if (carry != 0) {
            sb.append(carry);
            return sb.reverse().toString(); 
        }
        return sb.reverse().toString(); 
    }
}
