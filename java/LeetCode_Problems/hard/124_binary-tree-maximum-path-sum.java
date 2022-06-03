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
    int max_value = Integer.MIN_VALUE; 
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0; 
        /*
        -1
     /.  \
     -2.   -3
        */
        
        dfs(root);
        return max_value; 
    }
    
    public int dfs(TreeNode root) {
        if (root == null) return 0; 
        

	//the main reason why it's Math.max(0, root.left/right) is because
	// we are trying to find the greatest increase in value
	//if the increase in value is negative, don't bother adding that to the final solution
	//if it's NOT zero, add it to the final solution (max_value)

        int left = Math.max(0, dfs(root.left));
        int right = Math.max(0, dfs(root.right));
        
        max_value = Math.max(max_value, left + right + root.val);
        
        return Math.max(left, right) + root.val;
    }
}


