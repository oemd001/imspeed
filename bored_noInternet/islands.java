class Solution() {

	int final_count = 0; 

	public int num_tree(List<List<String>> tree_map) {
		//if the supposed map is null, return null
		if (tree_map.size() == null && tree_map.get(0).size() == null) return null; 

		//if the tree is singularly dimensioned, return the number of 1's that we see
		if (tree_map.get(0).size() == 1) {
			int temp_counter = 0; 
			for (int i = 0; i < tree_map.size(); i++) {
				if (tree_map.get(i) == "1") {
					temp_counter++; 
				}
			}
			return temp_counter; 
		}

		for (int i = 0; i < tree_map.size(); i++) {
			for (int j = 0; j < tree_map.get(i).size(); j++) {
				final_count += recursion(tree_map, i, j)''
			}
		}

		return final_count; 
	}


	public int recursion(List<List<String>> tree_map, int row, int col) {
		if ((row >= tree_map.size() || row < 0) && (col >= tree_map.get(row).size() || col < 0)) return; 

		tree_map.get(row).get(col) = "0";

		recursion(tree_map, i + 1, j);
		recursion(tree_map, i - 1, j);
		recursion(tree_map, i, j + 1);
		recursion(tree_map, i, j - 1);

		return 1; 
	}
}