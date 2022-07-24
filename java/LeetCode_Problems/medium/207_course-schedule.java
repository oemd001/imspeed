class Solution {
    
    int total = 0; 
    public boolean canFinish(int numCourses, int[][] prereqs) {
        /*
        This is topoloical sort again
            Finding previous dependencies
        [1, 0], numCourses
            numCourses --> the max number of courses that I need to take to graduate
        [1, 0] => I need to take course 0 before I take course 1. 
        numCourses = 2 -> I must take at least two courses to graduate
            output -> true because when you've taken 0, you'll be able to take 1.
        
        Thought process: I need to do a topological sort
            1. Create a HashMap that gives us the relationships between each class
            2. Determine whether there is a cycle in the program (to DAG or not to DAG)
        Example: 
            [[1, 0], [0, 1]] -> That's a cycle
                In order to take course 1, I should have course 0 already. 
                BUT in order to take course 0, I should have course 1. 
                Can't do both mate
        Example: 
            [[1, 0], [3, 1]] -> That's NOT a cycle. 
                This just shows that class 1 is dependent on 0, class 3 is dependent on 1 
                From a graph perspective, this looks like das: 
                    0 -> 1 -> 3
        What needs to be done: 
            1. Create a HashMap that gives us the relationships between each classes
            2. Link each classes to ensure that there will not be a cycle
                - dfs
            3. Make sure that each classes can be taken < numCourses to graduate
        */
        if (prereqs.length == 1) return true; 
        
        Map<Integer, List<Integer>> map = new HashMap<>(); 
        Map<Integer, Boolean> seen = new HashMap<>(); 
        
        //creating the initial relations (key area)
        for (int i = 0; i < prereqs.length; i++) {
            for (int j = 0; j < prereqs[i].length; j++) {
                map.putIfAbsent(prereqs[i][j], new ArrayList<>());
            }
        }
        
        //populating initial relations (with list of values in arraylist)
        for (int i = 0; i < prereqs.length; i++) {
            int interest = prereqs[i][0];
            int prereq_class = prereqs[i][1];
            
            map.get(interest).add(prereq_class);
        }
        
        //checking cycle detection
        for (Integer val : map.keySet()) {
            boolean flag = dfs(val, map, seen); 
            if (!flag) return false; 
        }
        
        if (total > numCourses) return false; 
        
        return true;
    }
    
    //this part is cycle detection
    public boolean dfs(Integer val, Map<Integer, List<Integer>> map, Map<Integer, Boolean> seen) {
        if (seen.containsKey(val)) {
            return seen.get(val);
        }
        
        seen.put(val, false);
        
        for (Integer next_val : map.get(val)) {
            boolean flag = dfs(next_val, map, seen);
            if (!flag) return false; 
        }
        
        seen.put(val, true);
        total++; 
        return true; 
    }
}
