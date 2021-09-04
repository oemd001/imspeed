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

public class Solution {


    // Complete the triplets function below.
    static long triplets(long t, List<Integer> d) {
        //list.get(a) + list.get(b) + list.get(c) <= t
        //return the number of times this is true
        Collections.sort(d);
        long count = 0;
        for (int i = 0; i < d.size(); i++) {
            count = count + twoSum(d, i + 1, t - d.get(i));
        }
        return count; 
    }
    static long twoSum(List<Integer> d, int startIndex, long target) {
        long count = 0; 
        int left = startIndex;
        int right = d.size() - 1; 
        while (left < right) {
            if (d.get(left) + d.get(right) <= target) {
                count = count + (right - left);
                left++;
            }
            else {
                right--;
            }
        }
        return count; 
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        long t = Long.parseLong(bufferedReader.readLine().trim());

        int dCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> dTemp = new ArrayList<>();

        IntStream.range(0, dCount).forEach(i -> {
            try {
                dTemp.add(bufferedReader.readLine().replaceAll("\\s+$", ""));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> d = dTemp.stream()
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        long res = triplets(t, d);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

