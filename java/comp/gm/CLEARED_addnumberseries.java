import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * The Main class implements an application that reads lines from the standard input
 * and prints them to the standard output.
 */
public class Main {
  /**
   * Iterate through each line of input.
   */
  public static void main(String[] args) throws IOException {
    InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
    BufferedReader in = new BufferedReader(reader);
    String line;
    //10 --> 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10
    //catch is that we don't add the numbers are divisible by 5 or 7
    //10 --> 1 + 2 + 3 + 4 + 6 + 8 + 9
    //This method is considered to be a brute force method, not incredibly fast
    /*
    while ((line = in.readLine()) != null) { 
      int result = 0; 
      for (int i = 0; i <= Integer.parseInt(line); i++) {
        if (i % 5 != 0 && i % 7 != 0) result = result + i; 
      }
      System.out.println((int)result);
    }
    */
    while ((line = in.readLine()) != null) {
      System.out.print(recursive(Integer.parseInt(line)));
    }
  }
  //This code is a lot better, this is a recursive method that should be a bit faster than the first one above. 
  private static int recursive(int val) {
    if (val <= 0) {
      return 0;
    }
    if (val % 5 == 0 || val % 7 == 0) return recursive(val - 1);
    else return val + recursive(val - 1);
  }
}

