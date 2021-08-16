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
     * Complete the 'repeatedString' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. LONG_INTEGER n
     */

    public static long repeatedString(String s, long n) {
        return helper(s, n);
    }
    
    private static long helper(String s, long n) {
        long numOfChar = 0;
        int numOfA = 0;
        int stringLength = s.length();

        if(stringLength > 0){
            for(int i=0;i<stringLength;i++){
            if(s.charAt(i)=='a'){
                numOfA++;
            } 
        } 

            long division = n / stringLength;
            long mod = n % stringLength;
            numOfChar = division * numOfA;

            for(int i=0;i<mod;i++){
                if(s.charAt(i)=='a'){
                    numOfChar++;
                }
            }
    }
            return numOfChar;
    }
    
    private static long slow(String s, long n) {
        if (s.length() == 1 && s.charAt(0) == 'a') return n;
        int count = 0; 
        String temp = s; 
        int j = 0; 
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'a') {
                count++;
            }
            if (n > s.length()) {
                s = s + temp.charAt(j);
                j++;
                if (j == temp.length()) j = 0; 
            }
        }
        return count; 
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        long n = Long.parseLong(bufferedReader.readLine().trim());

        long result = Result.repeatedString(s, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

