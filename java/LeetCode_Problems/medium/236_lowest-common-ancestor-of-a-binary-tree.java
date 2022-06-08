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
        /*
        What happens in specific cases?
        dfs recursive: I found "p", what's next? We want to find q
        Try storing some other state
        
        I did not think of the naive solution first
        What if we store a list of ancestors first leading up to a node?
        Return the leftmost item from the list
        
        inductive reasoning: if there is no least common ancestor in the left sub branch, it'll be in the right one
        If you found p in the left and q in the right, you return the root

        Break this down in to cases
        What happens if we're in the same branch?

        If I don't find p or q in the left, then they have to be in the right
        If it's not in the right, it's in the left

        5 and 1. If right is non null and right is non null, I found p in right/left, q in the right/left
        
        inductive reasoning: if there is no least common ancestor in the left sub branch, it'll be in the right one
    If you found p in the left and q in the right, you return the root

    Break this down in to cases
    What happens if we're in the same branch?

    If I don't find p or q in the left, then they have to be in the right
    If it's not in the right, it's in the left

    5 and 1. If right is non null and right is non null, I found p in right/left, q in the right/left
        */
        return helper(root, p, q);
    }
    
    
    public TreeNode helper(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q || root == null) {
            return root; 
        }
        
        TreeNode left = helper(root.left, p, q);
        TreeNode right = helper(root.right, p, q);
        
        if (left == null) return right; 
        if (right == null) return left; 
        
        return root; 
    }
}