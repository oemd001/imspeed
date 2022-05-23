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
    List<String> list = new LinkedList<>(); 
    String root_value = "";
    
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return list; 
        if (root.left == null && root.right == null) {
            list.add(Integer.toString(root.val));
            return list; 
        }
        
        String temp = Integer.toString(root.val);
        root_value = temp; 
        
        recursive(root, "");
        
        return list; 
    }
    
    //my interpretation (good enough for an interview. Hope there is no compiler)
    public void recursion(TreeNode root, String temp) {
        if (root == null) return; 
        
        if (root.left != null) recursion(root.left, temp);
        
        temp = temp + "->" + root.val;
        
        if (root.right != null) recursion(root.right, temp);
        
        if (root.left == null && root.right == null) {
            root.val = 101; 
            
            list.add(temp);
            temp = root_value;
        }
        
    }
    
    //my interpretation + working solution
    public void recursive(TreeNode root, String temp) {
        if (root != null) {
            temp = temp + Integer.toString(root.val);
            
            if (root.left == null && root.right == null) {
                list.add(temp);
            }
            else {
                temp = temp + "->";
                recursive(root.left, temp);
                recursive(root.right, temp);
            }
        }
    }
}

