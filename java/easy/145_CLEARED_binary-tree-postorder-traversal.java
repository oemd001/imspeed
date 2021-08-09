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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        
        helper(root, list);
        
        return list; 
    }
    private void helper(TreeNode root, List<Integer> list) {
        if (root != null) {
            if (root.left != null) helper(root.left, list);
            if (root.right != null) helper(root.right, list);
            list.add(root.val);
        }
    }
}

//postorder: l, r, n
