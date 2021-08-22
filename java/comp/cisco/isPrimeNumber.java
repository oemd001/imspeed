import java.util.*;
import java.lang.*;
import java.io.*;

/*
 * 
 */
public class Solution
{
	public static String[]  isPrimeNumber(int[] elements)
	{ 
		String[]  answer = new String[elements.length];
		// Write your code here
		//primes, only divisible by 1 and itself
		//try every value?
		//test some edge cases: such as if the value of the digit is not 1 or something like that
		//since the value of elements[i] will never be less than 0, we can return "false" or "composite" when we see a value that's such
		
		//will need to update the answer array as we are returning that
		for (int i = 0; i < elements.length; i++) {
			int comp = elements[i];
			if (comp < 2) answer[i] = "Prime";
			else if (comp == 2 || comp == 3) answer[i] = "Prime";
			else if (comp % 2 == 0 || comp % 3 == 0)  answer[i] = "Composite";
			else {
				long n = (long)Math.sqrt(comp) + 1;
				for (int j = 3; j * j <= comp; j += 2) {
					if (comp % j == 0) answer[i] = "Composite";
				}
				if (answer[i] == "" || answer[i] == null) answer[i] = "Prime";
			}
		}
		return answer;
	}

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		//input for elements
		int elements_size = in.nextInt();
		int elements[] = new int[elements_size];
		for(int idx = 0; idx < elements_size; idx++)
		{
			elements[idx] = in.nextInt();
		}
		
		String[] result = isPrimeNumber(elements);
		for(int idx = 0; idx < result.length - 1; idx++)
		{
			System.out.print(result[idx] + " ");
		}
		System.out.print(result[result.length - 1]);
	}
}

