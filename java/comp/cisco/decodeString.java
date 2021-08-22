import java.util.*;
import java.lang.*;
import java.io.*;

/*
 * 
 */
public class Solution
{
	public static String  expandedString(String inputStr)
	{
		String  answer = "hello";
		// Write your code here
		
		Stack<String> stack = new Stack<>();
		String current = "";
		String number = "";
		
		for (int i = 0; i < inputStr.length(); i++) {
			char temp = inputStr.charAt(i);
			if (Character.isDigit(temp)) {
				number = number + temp;
				continue;
			}
			
			if (Character.isAlphabetic(temp)) {
				current = current + temp;
			}
			if (temp == '(') {
				stack.push(current);
				current = "";
			}
			if (temp == '}') {
				int count = Integer.valueOf(number);
				number = "";
				String old = stack.pop();
				StringBuilder build = new StringBuilder();
				while (count > 0) {
					build.append(current);
					count--;
				}
				current = old + build.toString();
			}
		}
		StringBuilder build = new StringBuilder();
		while (!stack.isEmpty()) {
			build.append(stack.pop());
		}
		build.append(current);
		answer = build.toString();
		
		return answer;
	}

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		
		// input for inputStr
		String inputStr = in.nextLine();
		
		String result = expandedString(inputStr);
		System.out.print(result);
		
	}
}

