int[][] sortMatrixByOccurrences(int[][] m) {
    Map<Integer, Integer> hash_map = new HashMap<>();
    
    int rows = m.length;
    int columns = m[0].length;
    
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < columns; j++) {
            int temp = m[i][j];
            hash_map.put(temp, hash_map.getOrDefault(temp, 0) + 1);
        }
    }
    
    //Map<Integer, Integer> sorted_map = sortByComparator(hash_map, false); need to redo that
    
    Map.Entry<Integer, Integer> entry = sorted_map.entrySet().iterator().next();
    int key = entry.getKey();
    int value = entry.getValue();
    
    
    return m;
    
}

