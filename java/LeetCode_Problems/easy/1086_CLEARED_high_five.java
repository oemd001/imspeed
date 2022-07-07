class Solution {
    public int[][] highFive(int[][] items) {
        Map<Integer, Integer> map = new HashMap<>(); 
        Map<Integer, Integer> track = new HashMap<>(); 
        List<List<Integer>> list = new ArrayList<>(); 
        
        Arrays.sort(items, Comparator.comparingInt(o -> o[1]));
        //selectionSort(items);
        
        for (int i = items.length - 1; i >= 0; i--) {
            int tag = items[i][0];
            int score = items[i][1];
            
            if (!map.containsKey(tag)) {
                map.put(tag, score);
                track.put(tag, 0);
            }
            else {
                if (track.get(tag) < 4) {
                    map.put(tag, map.get(tag) + score);
                    track.put(tag, track.get(tag) + 1);
                }
            }
        }
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int tag = entry.getKey(); 
            int score = entry.getValue() / 5; 
            List<Integer> temp = new ArrayList<>(); 
            temp.add(tag);
            temp.add(score);
            list.add(temp);
        }
        
        Collections.sort(list, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(0).compareTo(o2.get(0));
            }
        });
        
        int[][] arr = list.stream().map(x -> x.stream().mapToInt(Integer::intValue).toArray()).toArray(int[][]::new);
        
        return arr; 
        
        
        
    }
    
    public int[][] min_heap(int[][] items) {
        //PRIRORITY QUEUE YOU DUMBASS
    }
}

