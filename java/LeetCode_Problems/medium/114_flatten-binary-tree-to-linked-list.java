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
    public void flatten(TreeNode root) {
        if (root == null) return; 
        
        //iterative_constant(root);
        recursive(root);
    }
    
    public void recursive(TreeNode root) {
        if (root == null) return; 
        
        //checks if the node is a leaf. If so, there is nothing else we need to do 
        //(for comparisons)
        if (root.left == null && root.right == null) return; 
        
        recursive(root.left);
        
        TreeNode temp = root.right; 
        
        //This does the pre-order replacement and sets every left node to null
        //(we are not interested in the left node because we only want right nodes)
        //psuedo linked list
        if (root.left != null) {
            TreeNode link = root.left; 
            
            while (link.right != null) {
                link = link.right; 
            }
            link.right = temp; 
            root.right = root.left; 
            root.left = null;
        }
        
        recursive(temp);
    }
    
    public void iterative_constant(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>(); 
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop(); 
            
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
            if (!stack.isEmpty()) {
                current.right = stack.peek(); 
            }
            
            current.left = null; 
            
        }
    }
}

