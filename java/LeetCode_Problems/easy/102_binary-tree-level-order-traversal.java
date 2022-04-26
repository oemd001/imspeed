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
    public List<List<Integer>> levelOrder(TreeNode root) {
        return bfs(root);
    }

    public void dfs_recursion(TreeNode root, int level) {
        //This part essentially creates a new level for the ArrayList stack
        if (list.size() == level) {
            list.add(new ArrayList<Integer>());
        }
        
        //This part basically adds another value to the already existing stack
        list.get(level).add(root.val);
        
        //perform everything on the left first and then to the right
        if (root.left != null) helper(root.left, level++);
        if (root.right != null) helper(root.right, level++);
    }
    
    public List<List<Integer>> bfs(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>(); 
        
        if (root == null) return list; 
        
        Queue<TreeNode> queue = new LinkedList<>(); 
        queue.clear();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size(); 
            List<Integer> temp = new ArrayList<>(); 
            
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll(); 
                temp.add(current.val);
                
                if (null != current.left) queue.add(current.left);
                if (null != current.right) queue.add(current.right);
                
            }
            list.add(temp);
        }
        
        
        return list; 
    }
}

