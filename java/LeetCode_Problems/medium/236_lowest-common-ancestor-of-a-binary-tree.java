/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return optimize(root, p, q);
    }
    
    public TreeNode helper(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null; 
        
        TreeNode left = helper(root.left, p, q);
        TreeNode right = helper(root.right, p, q);
        
        if (root == p && (left == q || right == q))  return root; 
        if (root == q && (left == p || right == p)) return root; 
        
        if (left == p && right == q) return root; 
        if (left == q && right == p) return root; 
        
        if (left != null) return left; 
        if (right != null) return right; 
        
        if (root == p) return p; 
        if (root == q) return q; 
        
        return null;
    }
    
    public TreeNode optimize(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root; 
        // If p & q are not found, just return null
		// If one of them is found, return that one to keep its record in the stack
        TreeNode left = optimize(root.left, p, q);
        TreeNode right = optimize(root.right, p, q); 
        // If both of them are found, return the current node
        if (left == null) return right; 
        if (right == null) return left; 
        
        return root;
    }
    
    
}
