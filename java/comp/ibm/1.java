import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {

    /*
     * Complete the 'compressedString' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING message as parameter.
     */

    public static String compressedString(String message) {
    // Write your code here
    //compressed string:
        //a occurs one time
        //b occurs one time
        //OR
        //a occurs two times consecutively
        //s occurs one time
        //s occurs two times consecutively
        //so basically, what I'm getting at is that we're remembering the last thing we've saw on the string
        //HashSet won't work because it'll remember the first a we saw if there's another "aba" in the mix
        
        int group_count = 1; 
        char lastchar = message.charAt(0);
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 1; i < message.length(); i++) {
            if (message.charAt(i) == lastchar) {
                group_count++;
            }
            else {
                if (group_count > 1) {
                    sb.append("" + lastchar + group_count); //sorry! I accidentally put my computer to sleep!
                }
                else {
                    sb.append(lastchar);
                }
                group_count = 1; 
                lastchar = message.charAt(i);
            }
        }
        if (group_count > 1) {
            sb.append("" + lastchar + group_count);
        }
        else {
            sb.append(lastchar);
        }
        return sb.toString();
        
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String message = bufferedReader.readLine();

        String result = Result.compressedString(message);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

