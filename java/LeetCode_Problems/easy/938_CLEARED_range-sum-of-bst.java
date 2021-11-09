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
    public int rangeSumBST(TreeNode root, int low, int high) {
        return me(root, low, high);
    }
    
    public int me(TreeNode root, int low, int high) {
        //int result = low + high;
        int result = 0; 
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root; //dummy node
        
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            TreeNode value = stack.peek();
            if (value.val >= low && value.val <= high) {
                result += value.val;
            }
            current = stack.pop();
            current = current.right;
        }
        
        return result;
    }
}

/*
Any values that fall in between low and high, add it to result
return result
*/
