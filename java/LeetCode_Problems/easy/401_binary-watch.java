class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        int[] arr = {1, 2, 4, 8, 1, 2, 4, 8, 16, 32};
        List<String> list = new ArrayList<String>();
        optimize_backtrack(turnedOn, 0, 0, 0, arr, list);
        
        return list;
    }
    private List<String> slow(int num) { //this method is the slow, bruteforce way
        //"num" contains the number of LEDs that can possibly be on at once
        //the maximum value that nums could be is 10
        //Given this particular problem, we see that the "hour" area only has 4 values: 2^0 2^1 ... 2^3
        //We also see that the minute area has six values, 2^0 ... 2^5
        //this would be the naive way, we can brute force our solution by basically adding all 12 hours, all 60 minutes in a bit operation
        
        List<String> list = new ArrayList<String>();
        
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                int temp = Integer.bitCount(i) + Integer.bitCount(j);
                if (temp == num) {
                    if (j < 10) {
                        list.add(String.format("%d:0%d", i, j));
                    }
                    else {
                        list.add(String.format("%d:%d", i, j));
                    }
                }
            }
        }
        return list; 
    }
    
    private List<String> optimize(int num) {
        
        List<String> list = new ArrayList<String>();
        
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                int temp = Integer.bitCount(i) + Integer.bitCount(j);
                if (temp == num) {
                    StringBuilder sb = new StringBuilder(); 
                    sb.append(i).append(":");
                    if (j < 10) {
                        sb.append("0");
                    }
                    sb.append(j);
                    list.add(sb.toString());
                }
            }
        }
        return list; 
    }
    
    private void slow_backtrack(int turnedOn, int pos, int hour, int mins, int[] arr, List<String> list) {
        if (hour > 11 || mins > 59) { //if hours is greater than the expected value (11 for hours, 59 for minutes)
            return; 
        }
        
        if (turnedOn == 0) { //turnedOn is basically the number of times the LEDs can be represented by (different values, but with x amount of LEDs)
            String str = ""; //this basically is the conditions that allow for string addition, and eventually, returning the full list of possible outcomes
            if (mins <= 9) str = hour + ":0" + mins;
            else str = hour + ":" + mins;
            
            list.add(str);
            return; 
        }
        
        if (pos == arr.length) return; //if we reach the end of the allocated list, we want to exit the loop. Continuing will result in out of bounds
        
        //these loops represent the LEDs when it's ON
        if (pos <= 3) { //this part looks at 1 2 4 8. this is the "hours" part
            slow_backtrack(turnedOn - 1, pos + 1, hour + arr[pos], mins, arr, list);
        }
        else { //everything else beyond position "3" will look at the minute portion
            slow_backtrack(turnedOn - 1, pos + 1, hour, mins + arr[pos], arr, list);
        }
        
        //this recursive method represents the LED for when it's OFF
        slow_backtrack(turnedOn, pos + 1, hour, mins, arr, list);
    }
    
    private void optimize_backtrack(int turnedOn, int pos, int hour, int mins, int[] arr, List<String> list) {
        if (hour > 11 || mins > 59) {
            return; 
        }
        
        if (turnedOn == 0) {
            StringBuilder sb = new StringBuilder(); 
            if (mins <= 9) {
                sb.append(hour).append(":0");
                sb.append(mins);
            }
            else {
                sb.append(hour).append(":").append(mins);
            }
            list.add(sb.toString());
            return; 
        }
        
        if (pos == arr.length) return; 
        
        if (pos <= 3) {
            optimize_backtrack(turnedOn - 1, pos + 1, hour + arr[pos], mins, arr, list);
        }
        else {
            optimize_backtrack(turnedOn - 1, pos + 1, hour, mins + arr[pos], arr, list);
        }
        
        optimize_backtrack(turnedOn, pos + 1, hour, mins, arr, list);
         
    }
}