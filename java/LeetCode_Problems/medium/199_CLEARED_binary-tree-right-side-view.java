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
    List<Integer> list = new ArrayList<>(); 
    
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return list; 
        
        dfs(root, 0);
        return list; 
    }
    
    public void bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>(); 
        queue.clear(); 
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size(); 
            
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll(); 
                
                if (i == size - 1) {
                    list.add(current.val);
                }
                
                if (null != current.left) {
                    queue.add(current.left);
                }
                if (null != current.right) {
                    queue.add(current.right);
                }
            }
        }
    }
    
    public void improvement_bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>(); 
        queue.clear(); 
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size(); 
            
            for (int i = size - 1; i >= 0; i--) {
                TreeNode current = queue.poll(); 
                if (i == size - 1) list.add(current.val);
                
                if (null != current.right) queue.add(current.right);
                if (null != current.left) queue.add(current.left);
            }
        }
    }
    
    public void dfs(TreeNode root, int level) {
        if (list.size() == level) {
            list.add(root.val);
        }
        
        if (root.right != null) dfs(root.right, level + 1);
        if (root.left != null) dfs(root.left, level + 1);
    }
}