class Solution() {

	public boolean binary_tree_validate(TreeNode root) {
		if (root == null) return true;

		return recursion_inorder(root, long.MAX_VALUE, long.MIN_VALUE);
	}

	public boolean recursion_inorder(TreeNode root, long max, long min) {
		if (root == null) return false; 

		if (root < max && root > min) {
			boolean right_side = true; 
			boolean left_side = true; 

			if (root.left != null) left_side = recursion_inorder(root.right, root.val, min);
			if (root.right != null) right_side = recursion_inorder(root.left, max, root.val); 

			return right_side && left_side; 
		}

		return false; 
	}
}