import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
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
    
    //so for a number to be considered to be a happy number, we need to add the "squares" of each digit
    //so that basically means: 22 would be 2^2 + 2^2, and that would equal 8, and then 64, and then 6^2 + 4^2 and so on
    //I think the best way to do this is to have a hashset, or some sort of mapping structure that basically continues
    //to add the values (substring, charAt()) basically making sure that there is no repetition
    //22->8->64->52->29->85->89->145->42->20->4->16->37->58->89, we can see that 89 is repeated twice
    //as a result, that probably wouldn't be a happy number because we know that 89 would go in to it's own loop of 145 etc etc etc
    while ((line = in.readLine()) != null) {
      System.out.println(helper(Integer.parseInt(line)));
    }
  }
  
  public static int helper(int number) {
    Set<Integer> set = new HashSet<Integer>();
    while (set.add(number)) {
      int result = 0; 
      while (number > 0) {
        result = result + (int)Math.pow(number % 10, 2);
        number = number / 10; 
      }
      number = result; 
    }
    if (number == 1) return 1;
    
    return 0; 
  }
}

