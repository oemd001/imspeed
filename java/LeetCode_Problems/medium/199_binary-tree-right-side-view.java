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
    List<Integer> arr = new ArrayList<>(); 
    public List<Integer> rightSideView(TreeNode root) {
        //return bfs(root);
        
        if (root == null) return arr;
        dfs(root, 0);
        return arr;
    }
    
    public List<Integer> bfs(TreeNode root) {
        List<Integer> list = new ArrayList<>(); 
        
        if (root == null) return list; 
        
        Queue<TreeNode> queue = new LinkedList<>(); 
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int length = queue.size(); 
            
            for (int i = 0; i < length; i++) {
                
                TreeNode current = queue.remove(); 
                
                if (i == length - 1) {
                    list.add(current.val);
                }
                
                 if (current.left != null) {
                    queue.add(current.left);
                }
                
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
        }
        return list; 
    }
    
    public void dfs(TreeNode root, int level) {
        if (level == arr.size()) {
            arr.add(root.val);
        }
        if (root.right != null) {
            dfs(root.right, level + 1);
        }
        if (root.left != null) {
            dfs(root.left, level + 1);
        }
    }
    
}
/*
dfs
bfs

*/
