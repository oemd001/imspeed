class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        return optimize(turnedOn);
    }
    private List<String> slow(int num) { //this method is the slow, bruteforce way
        //"num" contains the number of LEDs that can possibly be on at once
        //the maximum value that nums could be is 10
        //Given this particular problem, we see that the "hour" area only has 4 values: 2^0 2^1 ... 2^3
        //We also see that the minute area has six values, 2^0 ... 2^5
        //this would be the naive way, we can brute force our solution by basically adding all 12 hours, all 60 
minutes in a bit operation
        
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
}
