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
    public boolean isValidBST(TreeNode root) {
        if (root == null) return false; 
        //return naive(root);
        return helper(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }
    
    private boolean naive(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        
        int compare = root.val; //this is the value that will be compared against
        List<Integer> arr = new ArrayList<>();
        
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left; 
            }
            root = stack.pop();
            arr.add(root.val);
            root = root.right;
        }
        
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i) + " ");
        }
        
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i) <= arr.get(i - 1)) return false; 
        }
        return true; 
    }
    
    public boolean helper(TreeNode root, long max, long min) {
        if(root == null)
            return false;
        
        if(root.val < max && root.val > min) {
            boolean leftCheck = true;
            boolean rightCheck  = true;
            
            if(root.left != null) leftCheck = helper(root.left, root.val, min);
            
            if(root.right != null) rightCheck = helper(root.right, max, root.val);
            
            return leftCheck && rightCheck;
        }
        return false;
    }
}
