class Solution() {
	List<List<Character>> list = new ArrayList<>();

	public String zig_zag(String word, int numRows) {
		if (word.length() == 1 || word.length == 0) return word; 

		return solution1(word, numRows);
	}

	//This is a dfs solution. I think it works in theory but I need to test it out with a compiler later
	public String solution1(String word, int numRows) {
		/*
		Given the fact that I'll have to create a 2D array of chars, wouldn't it be better if I took the 
		number of island problem approach?

		Basically, instead of doing a bfs, (going across), I think it might be easier (to both write code and visualize) 
		if we went with a dfs approach
		sample: PAYPALISHIRING (word), 3 (numRows)
		P1	  A5    H9 		N13
		A2 P4 L6 S8 I10 I12 G14
		Y3 	  I7	R11
		*/


		int row_position = 0; 
		int col_position = 0; 
		int char_position = 0;
		int numCols = numRows - 1; 

		//going down
		while (row_position < numRows) {
			list.get(row_position).get(col_position) = word.charAt(char_position);

			if (row_position == numRows - 1) {
				//to determine the number of diagnol values, it'll be numRows - 1
				//we only want the values in between. We do not want the values that will be on the 0th row
				while (row_position > 0 && col_position < word.length()) {
					list.get(row_position - 1).get(col_position + 1) = word.charAt(char_position);
				}
			}
		}

		return new String(list);

	}
}