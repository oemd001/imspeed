class Solution {
    public int numTeams(int[] rating) {
        //look @ solution3
        return solution3(rating);
    }
    public int solution1(int[] rating) {
        /*
        A few things that are worth mentioning: 
            (i, j, k) -> that's the order we're expected to keep track of
            Two conditions
                the rating of i, j and k MUST be in ascending order
                OR
                the rating of i, j and k MUST be in descending order
            So, for the case of ascending/descending order
                rating of i must be greater than j, j must be greater than k
                OR
                rating of i must be less than that of j and that of k
        
        Naive method
            We can continuously to loop through the ratings array until we find a match
                Example: we find i, and it's respective rating, and from there, we continue to loop
                until we find a suitable j and k value
                We keep on doing that until i reaches rating.length
        
        Optimized method
            One pass
            We will use a hashmap
            What we can do is this: 
                Create a "running" hashmap
                example: [2,5,3,4,1]
                
                2 (2,5) (2,3,4) (2,1)
                5 (5)   (5,3,1) (5,4,1)
                3 (3)   (3,4) (3,1)
                4 (4)   (4,1)
                1 (1)   (1)
                
            After we do that, we count the number of times the "sub list" is equal to 3
            And after that, we esentially return the number of times the count iterates if that makes sens
        */
        
        if (rating.length < 3) return 0; 
        List<List<Integer>> list = new ArrayList<>(); 
        Set<String> set = new HashSet<>(); 
        
        for (int i = 0; i < rating.length; i++) {
            List<Integer> temp = new ArrayList<>(); 
            int num = rating[i];
            temp.add(num);
            list.add(temp);
        }
        
        for (int i = 0; i < rating.length; i++) {
            int num = rating[i];
            
            
            for (int j = 0; j < list.size(); j++) {
                for (int k = 0; k < list.get(j).size(); k++) {
                    if (list.get(j).size() < 3 && list.get(j).get(list.get(j).size() - 1) < num && i < j && i < k && k < k) 
                        list.get(j).add(num);
                    else if (list.get(j).size() < 3 && list.get(j).get(list.get(j).size() - 1) > num && i < j && i < k && k < k)
                        list.get(j).add(num);
                    
                    
                    System.out.println(list.get(j).get(list.get(j).size() - 1));
                    System.out.println(list.get(j).size());
                }
            }
        }
        
        for (int i = 0; i < list.size(); i++) {
            String temp = "";
            for (int j = 0; j < list.get(i).size(); j++) {
                temp += Integer.toString(list.get(i).get(j));
            }
            if (temp.length() == 3) set.add(temp);
        }
        
        return set.size(); 
    }
    
    public int solution2(int[] rating) {
        int count = 0; 
        int big[] = new int[rating.length];
        int small[] = new int[rating.length];
        
        for (int i = 0; i < rating.length - 1; i++) {
            for (int j = i + 1; j < rating.length; j++) {
                if (rating[i] < rating[j]) {
                    count = count + small[i];
                    small[j]++; 
                }
                else {
                    count = count + big[i];
                    big[j]++; 
                }
            }
        } 
        return count; 
    }
    
    public int solution3(int[] rating) {
        /*
        What I discovered: 
        
            This solution is O(N^2) but it's defintely an improvement from O(N^3)
            Intuition: we are looking at this array from the left side and the right side
                HOWEVER, we do not consider the middle number because that number is like a "pivot"
                    Note: triplets in either ascending or descending order all work
                With that being said, we simply loop through the array (starting at index 1) and then 
                we determine whether the value(s) in question are either greater or less than
                    From the left or right side, hence the following formula:
                    rightGreater * leftLesser + rightLesser * leftGreater
                    
                How this formula works: 
                    We are tasked to find three values (either in ascending order or descending order)
                    rightGreater * leftLesser -> means that we need at least 1 value less than the pivot value and 1 value greater than the pivot
                        If there are 2 values that are greater and 1 value that is lesser, we have a total of two possible combinations
                        (hence, the multiplication)
                    Same thing on the other side
                    rightLesser * leftGreater -> basically the same thing, but the other way around
                    
                Finally, when we finally find the values that we are looking for, we add it to a "count" variable and eventually
                    return that value; 
            
        */
        int count = 0; 
        
       for (int i = 1; i < rating.length - 1; i++) {
           int leftGreater = 0; 
           int leftLesser = 0; 
           
           int rightGreater = 0; 
           int rightLesser = 0; 
           
           //Finding items that are lesser on the right side
           for (int j = i - 1; j >= 0; j--) {
               if (rating[j] > rating[i]) leftGreater++; 
               else leftLesser++; 
           }
           
           //Finding items that are greater on the left side
           for (int j = i + 1; j < rating.length; j++) {
               if (rating[j] > rating[i]) rightGreater++; 
               else rightLesser++; 
           }
           
           count += (rightGreater * leftLesser) + (rightLesser * leftGreater);
       }
        return count; 
    }
}
