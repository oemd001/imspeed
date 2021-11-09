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
    int result = 0; 
    public int rangeSumBST(TreeNode root, int low, int high) {
        //return iterative(root, low, high);
        recursive(root, low, high);
        return result; 
    }
    
    public int iterative(TreeNode root, int low, int high) {
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
    
    //recursive is MUCH faster
    public void recursive(TreeNode root, int low, int high) {
        if (root != null) {
            if (low <= root.val && high >= root.val) {
                result += root.val; 
            }
            if (low < root.val) {
                recursive(root.left, low, high);
            }
            if (root.val < high) {
                recursive(root.right, low, high);
            }
        }
    }
}

/*
Any values that fall in between low and high, add it to result
return result
*/