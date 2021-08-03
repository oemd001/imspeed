class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        //This has to be an hashmap
        //List of volues, with the same key, that is considered to be an anagram
        //string array (strs) --> char, and sort the values alphabetically. 
        //if the compared values are NOT the same, add another key
        //time complexity  O(nmlogm): n is the largest size of words we can recieve and m is the largest length of the word we can receive
        List<List<String>> same = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        
        for (String current : strs) {
            char[] characters = current.toCharArray();
            Arrays.sort(characters);
            String sorted = new String(characters);
            if (!map.containsKey(sorted)) {
                map.put(sorted, new ArrayList<>());
            }
            
            map.get(sorted).add(current);
        }
        same.addAll(map.values());
        return same;
    }
}



/* <--worked out solution
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        //eat --> tea, ate
        //list of unique characters
        List<List<String>> groupedAnagram = new ArrayList<>();
        HashMap<String,List<String>> map = new HashMap<>();
        
        for(String current : strs) {
            char[] characters = current.toCharArray();
            Arrays.sort(characters);
            String sorted = new String(characters);
            
            if (!map.containsKey(sorted)) {
                map.put(sorted, new ArrayList<>());
            }
            
            map.get(sorted).add(current);
        }
        groupedAnagram.addAll(map.values());
        return groupedAnagram;
    }
}
*/
//constraints
/*
Edge Cases

Notes
Length of strs: 1 - 10;
How many items can I expect from the string?: 0 - 100;
*/
