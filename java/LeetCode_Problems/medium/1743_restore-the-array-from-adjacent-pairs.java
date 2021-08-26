class Solution {
    public int[] restoreArray(int[][] adjacentPairs) {
        return helper(adjacentPairs); 
    }
     public int[] helper(int[][] adjacentPairs) {
        // Build an adjacency list graph.
        Map<Integer, Queue<Integer>> iToPairs = new HashMap<>();
        for (int[] pair : adjacentPairs) {
            iToPairs.computeIfAbsent(pair[0], k -> new ArrayDeque<>()).add(pair[1]);
            iToPairs.computeIfAbsent(pair[1], k -> new ArrayDeque<>()).add(pair[0]);
        }
        
        // Find an item that has only one neighbour.
        int start = -1;
        for (int i : iToPairs.keySet()) {
            if (iToPairs.get(i).size() == 1) {
                start = i;
                break;
            }
        }
        
        // Traverse the graph in a linked-list fashion.
        int n = iToPairs.size();
        int writeIdx = 0;
        int[] restored = new int[n];
        restored[writeIdx++] = start;
        while (writeIdx < n) {
            int next = iToPairs.get(start).remove();
			iToPairs.remove(start);
            iToPairs.get(next).remove(start); // Remove the loop back to the current start.
            restored[writeIdx++] = next;
            start = next;
        }
        
        return restored;
    }
    public void wrongSolution(int[][] adjacentPairs) {
        List<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < adjacentPairs.length; i++) {
            int a = adjacentPairs[i][0];
            int b = adjacentPairs[i][1];
            if (!list.contains(b)) {
                list.add(b);
            }
            if (!list.contains(a)) {
                list.add(a);
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        
        //Arrays.sort(result);
    }
}