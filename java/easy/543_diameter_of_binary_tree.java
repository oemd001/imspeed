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
import java.util.*;

class Solution {
    int ans = 0; 
    public int diameterOfBinaryTree(TreeNode root) {
        getDiameter(root);
        
        if (ans == 0) return 0;
        
        return ans - 1; 
        
    }
    private int getDiameter(TreeNode root) {
        if (root == null) return 0;
        
        int left = getDiameter(root.left);
        int right = getDiameter(root.right);
        
        //that snippet gives us the length of the left and right subtrees, plus the head node. 
        ans = Math.max(ans, (left + right + 1));
        
        return Math.max(left, right) + 1; 
    }
}
