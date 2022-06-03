class Solution() {
	HashMap<Node, Node> map = new HashMap<>(); 
	public void link(Node node) {
		if (node == null) return; 

		dfs_recursion(node);
	}

	public Node recursion(Node original_node) {
		if (map.containsKey(original_node)) {
			return map.get(original_node);
		}

		Node copy_node = new Node(original_node.val);
		map.put(original_node, copy_node);

		for (Node neighbor : original_node.neighbors) {
			original_node.neighbors.add(recursion(neighbor));
		}

		return copy_node; 
	}
}