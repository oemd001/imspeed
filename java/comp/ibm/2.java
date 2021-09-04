import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.nio.file.*;


public class Solution {
    private static final Scanner scan = new Scanner(System.in);
    
    public static void main(String args[]) throws Exception {
        // read the string filename
        String filename;
        filename = scan.nextLine();
        //this should include bytes that are greater than 5000 bytes
        //and the number of times the number of bytes go over 5000 bytes
        //technically speaking, we just want the "last" item of each line
        //everything else isn't really relavent because we just want the byte count
            //so we can just start from the back, backtrack to the back
            //use a stringbuilder to add the numbers (we can assume that the filesize starts from the back)
            //and then end the convert the stringbuilder to string, then to integer, and then 
            //verify whether the value of that number is over 5000 bytes.
            //also, this entire "verify" how larget it is thing will end once it reaches a whitespace "//." or ".//"
        //Don't need a stack, reversing the stringbuilder appears to do the trick
        
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        long size = 0;
        long count = 0; 
        
        while (line != null) {
            int i = line.length() - 1;
            StringBuilder sb = new StringBuilder();
            //String s = new StringBuilder().append(line.charAt(i)).toString();
            while (line.charAt(i) != ' ') {
                sb.append(line.charAt(i));
                i--;
            }
            sb.reverse();
            String str = sb.toString();
            int temp = Integer.valueOf(str);
            
            if (temp > 5000) {
                size = size + temp;
                count++;
            }
            
            //System.out.println(line.length());
            //System.out.println(sb.toString());
            line = reader.readLine(); //this basically reads the next one
        }
        //System.out.println(count);
        //System.out.println(size); //I thought we needed to return this via console. Adding it to the file is right actually
        Files.write(Paths.get("bytes_" + filename), (count + "\n" + size + "\n").getBytes());
    }
}
