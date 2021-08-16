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
     * Complete the 'minimumBribes' function below.
     *
     * The function accepts INTEGER_ARRAY q as parameter.
     */

    public static void minimumBribes(List<Integer> q) {
        int swaps = 0; 
        
        for (int i = q.size() - 1; i >= 0; i--) {
            if (q.get(i) != i + 1) {
                if (i - 1 >= 0 && q.get(i - 1) == i + 1) {
                    int temp = q.get(i - 1);
                    q.set(i - 1, q.get(i));
                    q.set(i, temp);
                    swaps++;
                }
                else if (i - 2 >= 0 && q.get(i - 2) == i + 1) {
                    q.set(i - 2, q.get(i - 1));
                    q.set(i - 1, q.get(i));
                    q.set(i, q.get(i - 2));
                    swaps = swaps + 2;
                }
                else {
                    System.out.println("Too chaotic");
                    return; 
                }
            }
        }
        System.out.println(swaps);
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> q = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

                Result.minimumBribes(q);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}

