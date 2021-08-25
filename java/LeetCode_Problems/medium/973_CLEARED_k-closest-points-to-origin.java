class Solution {
    public int[][] kClosest(int[][] points, int k) {
        //x_2, y_2 = (0,0)
        //x_1, y_1
        
        //k is the number of points I need to return
        
        //k will be at least be 1, number of points inputted 
        // 1 <= k <= points.length;
        //-E4 to E4
        
        //brute force solution: have an array
        //it will house the entire results from the output
        
        //hashmap<points, output_of_distance formula>
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] n1, int[] n2){
                return (n1[1] * n1[1] + n1[0] * n1[0])-(n2[1] * n2[1] + n2[0] * n2[0]);
            }
        });
        
        for(int i = 0; i < points.length; i++){
            pq.offer(points[i]);
        }
        
        int[][] ans = new int[k][2];
        for(int i = 0; i < k; i++){
            ans[i] = pq.poll();
        }
        return ans;
        
    }
    //my solution–most likely does not work
    /*
    private int[][] helper(int[][] points, int k) {
        int x_2 = 0;
        int y_2 = 0; //this is the origin points
        
        int x_1 = 0;
        int y_2 = 0; //this is the "variable" points
        List<Integer> result_list = new ArrayList<>();
        
        int[][] returned_result = new int[1][k];
        
        Map<List<Integer>, Integer> hash_map = new HashMap<>(); //use a comparator to sort the array and use a comparator to sort the array
        
        for (int i = 0; i < points.length; i++) {
            List<Integer> list = new ArrayList<>();
            x_1 = points[i][0];
            y_2 = points[i][1];
            int result = Math.sqrt(Math.pow((x_1 - x_2), 2) + Math.pow(y_1 - y_2, 2));
            
            list.add(x_1, y_2);
            result_list.add(result);
            
            hash_map.add(list， result);
        }
        Collections.sort(result_list);
        
        for (int i = 0; i < k; i++) {
            List<Integer> temp = new ArrayList<>();
            temp.add(hash_map.getKey(result_list.get(i)))
            returned_result[i][0] = temp.get(0);
            returned_result[i][1] = temp.get(1);
        }
        
        return returned_result;
    }
    */
}

/*
\priority queue\, O(n log n)
LEARN THAT
as you add items, the things are still being sorted
2D arrays: Y, X
the second bracket will link to another array
*/
