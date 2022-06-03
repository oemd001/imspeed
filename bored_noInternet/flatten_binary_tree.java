
class Solution() {
	public void flatten(TreeNode root) {
		if (root == null) return null; 
		if (root.left != null && root.right != null) return root; 

		flatten_iter(root);
	}

	public void flatten_iter(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>(); 
		stack.add(root);

		while (!stack.isEmpty()) {
			TreeNode current = stack.peek(); 

			if (current.left != null) {
				stack.add(current.left);
			}
			if (current.right != null) {
				stack.add(current.right);
			}

			if (!stack.isEmpty()) {
				current.right = stack.peek(); 
			}

			current.left = null; 
		}
	}
}