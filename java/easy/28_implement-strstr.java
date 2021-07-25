
//Boyer–Moore String Search Algo
//Knuth–Morris–Pratt algorithm 
/*
class Solution {
    public int strStr(String haystack, String needle) {
  for (int i = 0; ; i++) {
    for (int j = 0; ; j++) {
      if (j == needle.length()) return i;
      if (i + j == haystack.length()) return -1;
      if (needle.charAt(j) != haystack.charAt(i + j)) break;
    }
  }
}
}
*/

class Solution {
    public int strStr(String haystack, String needle) {
        if (haystack=="") return 0; 
        for(int i=0; i<(haystack.length()-needle.length()+1);i++) {
            if (haystack.substring(i,i+needle.length()).equals(needle)) return i;
        }
        System.gc();
        return -1;
    }
}


