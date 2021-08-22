import java.util.*;
import java.lang.*;
import java.io.*;

/*
 * 
 */
public class Solution
{
	public static String  checkIPValidity(String addressIP)
	{
		String  answer = "INVALID";
		// Write your code here
		//maximum value an IP address can be is 255
		//maximum length of an IP address is 15, minimum length is 6
		if (addressIP.length() < 5 || addressIP.length() > 16) return answer;
		
		if (addressIP.endsWith(".")) return answer;
		if (addressIP.startsWith(".")) return answer;
		
		int count = 0; 
		
		for (int i = 0; i < addressIP.length(); i++) {
			char c = addressIP.charAt(i);
			if (c == '.') count++;
			if (count == 1 && c != '.') count = 0;
			if (count == 2) return answer;
		}
		
		String[] test = addressIP.split("\\.");
		
		for (String str : test) {
			int temp = Integer.parseInt(str);
			if (temp < -1 || temp > 255) {
				return answer;
			}
		}
		
		return "VALID";
	}

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		
		// input for addressIP
		String addressIP = in.nextLine();
		
		String result = checkIPValidity(addressIP);
		System.out.print(result);
		
	}
}
//I got 13/16
