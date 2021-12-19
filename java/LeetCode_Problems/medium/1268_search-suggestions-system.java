class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        return product_list_improved(products, searchWord);
    }
    
    
    public List<List<String>> product_list_improved(String[] products, String searchWord) {
       Arrays.sort(products);
        
        List<List<String>> res = new ArrayList<>();
        
        String s = "";
        for(int i=0; i<searchWord.length(); i++){
            s += searchWord.charAt(i);
            
            List<String> temp = new ArrayList<>();
            for(int j=0; j<products.length; j++){
                String str = products[j];
                
                if(str.indexOf(s) == 0)
                    temp.add(str);
                
                if(temp.size() == 3)
                    break;
            }
            res.add(temp);
        }
        return res;
    }
    //Runs through the iteration cycle multiple times, not the most efficient
    public List<List<String>> product_list(String[] products, String searchWord) {
        Arrays.sort(products);
        List<String> list = new ArrayList<>(); 
        List<List<String>> result = new ArrayList<List<String>>(); 
        List<String> addNew = new ArrayList<>();
        
        for (int i = 0; i < products.length; i++) {
            if (products[i].charAt(0) == searchWord.charAt(0)) {
                list.add(products[i]);
            }
        }
        int count = 0; 
        char c;
        String s = "";
        for (int i = 0; i < searchWord.length(); i++) {
            c = searchWord.charAt(i);
            s = s + Character.toString(c);
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).contains(s) && count < 3) {
                    addNew.add(list.get(j));
                    count++;
                }
            }
            result.add(new ArrayList(addNew));
            addNew.removeAll(addNew);
            count = 0; 
        }
        
        return result; 
    }
}


/*
This is an 2D arraylist, similar to that of a 2D array
The word must have a common prefix: the "m" in mouse.
The character must also be present in "products"
    "u" is not present in "monitor" so we omit that

"havana"'s prefix is h, so tatiana would not work
    loop in the sense that if the first letter is different than product, skip
    sort by first letter?

"havana" should loop 6 times because there are six characters in havana
There can be duplicates if there are repeat characters
**final result in List<List<String>> must be alphabetical

first: sort alphabetically
second: ignore the words that do not have the same prefix (I think you can combine first and second)
third: do a .contains(character)
fourth: add it to the list
fifth: once we hit three words, move to the next letter
sixth: return the list

*/
