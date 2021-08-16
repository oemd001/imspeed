/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode invertTree(TreeNode root) {
        return helper(root);
    }
    private TreeNode helper(TreeNode root) {
        if (root == null) return null;
        
        TreeNode right = helper(root.right);
        TreeNode left = helper(root.left);
        
        root.left = right;
        root.right = left; 
        return root; 
    }
}
