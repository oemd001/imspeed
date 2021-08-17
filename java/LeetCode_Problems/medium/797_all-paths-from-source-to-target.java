class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        return helper(graph);
    }
    //time complexity O(N^2 * 2^N)
    //https://www.youtube.com/watch?v=CYnvDzMprdc&ab_channel=MichaelMuinos
    private List<List<Integer>> helper(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<List<Integer>> queue = new LinkedList<>();
        int goalNode = graph.length - 1;
        
        queue.add(Arrays.asList(0)); //this kicks off the bfs
        
        while (!queue.isEmpty()) {
            List<Integer> path = queue.poll();
            int lastNode = path.get(path.size() - 1);
            
            if (lastNode == goalNode) {
                result.add(new ArrayList<>(path));
            }
            else {
                int[] neighbors = graph[lastNode];
                for (int neighbor : neighbors) {
                    List<Integer> list = new ArrayList<>(path);
                    list.add(neighbor);
                    queue.add(list);
                }
            }
        }
        return result;
    }
}
