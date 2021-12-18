class Solution {
    public String[] reorderLogFiles(String[] logs) {
        return comparator_solution(logs);
    }
    
    public String[] comparator_solution(String[] logs) {
        //anonymous class time
        Comparator<String> compare = (log1, log2) -> {
            //this splits the values, so we get the identifier (0) and the first value afterwards (1)
            String[] first_string = log1.split(" ", 2);
            String[] second_string = log2.split(" ", 2);
            
            boolean first_string_number = Character.isDigit(first_string[1].charAt(0));
            boolean second_string_number = Character.isDigit(second_string[1].charAt(0));
            
            //comparator logic
            if (!first_string_number && !second_string_number) {
                //position 0 is the ID
                //position 1 is literally everything else
                int compare_value = first_string[1].compareTo(second_string[1]);
                if (compare_value != 0) {
                    return compare_value; 
                }
                else {
                    return first_string[0].compareTo(second_string[0]);
                }
            }
            
            if (!first_string_number && second_string_number) {
                return -1; 
            }
            else if (first_string_number && !second_string_number) {
                return 1; 
            }
            else {
                return 0; 
            }
        };
        Arrays.sort(logs, compare);
        return logs; 
    }
}

/*
Order sorting algorithm: 
1. letter logs come before digit logs
2. letter logs are sorted by their content. If the content is the same: 
    we will need to sort based on the idenfiier
3. digit logs maintain their relative ordering

edge cases: 
    if there is a mixed letter/digit log, we base it off of the first digit/word. 
    everything else isn't relavent if we are determining if it's a letter log or anything like that

comparator reminders: 
    -1 -> o1 < o2
    0 -> o1 == o2
    1 --> o1 > o2
*/
