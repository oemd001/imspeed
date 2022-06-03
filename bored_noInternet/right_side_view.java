class Solution() {
	List<String> list = new ArrayList<>(); //can also be LinkedList<>(); 


	//O(N) memory I think as well. 
	public List<String> right_side(TreeNode root) {
		if (root == null) return list; 
		if (root.right == null && root.left == null) {
			list.add(root.val);
			return list; 
		}

		//doesn't make sense to use dfs for this
		//bfs(root);

		//if I really want lol
		dfs(root, 0);

		return list; 
	}

	public void bfs(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>(); 
		queue.clear(); 
		queue.add(root);

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = size - 1; i >= 0; i--) {
				TreeNode current = queue.poll(); 

				//in all honesty, I don't think I need the bottom two if statements
				if (null != current.right) {
					queue.add(current.right);
				}
				if (null != current.left) {
					queue.add(current.left);
				}
			}
		}
	}

	//Recursion, space is O(N). Theoretically, it should be O(1) space because I did not use any additional data structures. 
	//But recursion calls upon the memory stack, so that's great
	public void dfs(TreeNode root, int size) {
		if (list.size() == size) list.add(root.val);

		if (root.right != null) dfs(root.right, size + 1);
		if (root.left != null) dfs(root.left, size + 1);
	}


}