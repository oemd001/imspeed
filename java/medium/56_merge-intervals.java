class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 1) return intervals; 
        
        Arrays.sort(intervals, (arr1, arr2) -> Integer.compare(arr1[0], arr2[0])); //heck you can sort 2D arrays with this?? memorize this mate
        
        List<int[]> result = new ArrayList(); //because we don't know how many items are are sorting, we need to make this dynamically changing. hence, the "arraylist"
        
        int[] current_interval = intervals[0];
        result.add(current_interval);
        
        for (int[] temp : intervals) { //this basically iterates through the entire array
            int current_begin = current_interval[0]; //this sets the "begin" value [i][0]
            int current_end = current_interval[1]; //this sets the "end" value [i][1]
            int next_begin = temp[0]; //this sets the next "begin" value [i + 1][0]
            int next_end = temp[1]; //this sets the next "end" value [i + 1][1]
            
            if (current_end >= next_begin) { //this checks if the the current "end" value is greater than the next "begin" value
                current_interval[1] = Math.max(current_end, next_end); //if so, set the current interval to the "next end" interval
                //it's important to note that this changes it in the "output" array. [1,3] --> [1,6] (see example 1)
            }
            else {
                current_interval = temp; //if the next value is not greater than the current value
                //value [i][0] is not greater than [i + 1][0], just add the next value
                //however, if we get to the problem of [1,10][11,23][13,14], we can simply run the first
                //if statement and the entire process would start again. 
                result.add(current_interval); //apparently you can change the output array but modifying the current_interval variable
            }
        }
        
        
        return result.toArray(new int[result.size()][]);
    }
}




/*
class Solution {
    public int[][] merge(int[][] intervals) {
        //[start][end]
        //so we need to check whether the "end" value is less than the value of start + 1
        //if the value of [end] is greater than or equal to start, then we need to combine 
        //start and end + 1 in to one array. 
        
        //is it safe to assume that for every x value, there will be an x value
        
        //one variable that holds on to the start value, one variable that holds on to the end value
        //start value is held to basically prepare for hyptohetical merge
        //end value is held to basically compare start + 1 value
        //end + 1 value is held to also prepare for hypothetical merge. 
        int j = 0;
        int count = 0; 
        int temp = 0; 
        int start = intervals[j][0]; 
        int end = intervals[j][1];  
        int[][] result = new int[intervals.length][2];
        
        for (int i = 1; i < intervals.length; i++) {
            temp = i;
            start = intervals[j][0]; 
            end = intervals[j][1];  
            if (end >= intervals[i][0] && !(end >= intervals[i][1])) {
                result[count][0] = start;
                result[count][1] = intervals[i][1]; 
                count++;
            }
            else if(end >= intervals[i][0] && end >= intervals[i][1]) {
                while (end > intervals[i][1]) {
                    if (end <= intervals[i][0]) result[count][0] = intervals[i][0];
                    if (end <= intervals[i][1]) result[count][0] = intervals[i][1];
                    i++;
                    temp = i;
                }
            }
            else {
                result[count][0] = intervals[i][0];
                result[count][1] = intervals[i][1]; 
                count++;
            }
            i = temp; 
            j++;
        }
        return result;    
    }
}
/*
[1,3]
[2,6]
[8,10]
[15,18]

algo is probably floyd's tortoise and hare
range of intervals in x axis will be from 1 to 10^4
intervals[i] will be 2
0 to 10^4

*/
