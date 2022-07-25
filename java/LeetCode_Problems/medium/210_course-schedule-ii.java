class Solution {
    
    
     public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> list = new ArrayList<>(numCourses);
        for(int i = 0; i < numCourses; i++) list.add(new ArrayList<Integer>());
        for(int[] p: prerequisites) list.get(p[0]).add(p[1]);
        
        List<Integer> arr = new ArrayList<>();
        // 0: not visited | 1: visited | 2: visiting
        int[] visited = new int[numCourses];
        for(int i = 0; i < numCourses; i++) {
            if(dfs(list, visited, i, arr)) return new int[0];
        }
        
        int[] res = new int[arr.size()];
        for(int i = 0; !arr.isEmpty(); i++) res[i] = arr.remove(0);
        return res;
    }
    
    private boolean dfs(List<List<Integer>> list, int[] visited, int cur, List<Integer> arr) {
        if(visited[cur] == 2) return true;
        if(visited[cur] == 1) return false;
        visited[cur] = 2;
        for(int next: list.get(cur)) {
            if(dfs(list, visited, next, arr)) return true;
        }
        visited[cur] = 1;
        arr.add(cur);
        return false;
    }
}