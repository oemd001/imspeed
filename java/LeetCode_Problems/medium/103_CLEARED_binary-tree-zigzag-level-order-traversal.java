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
    List<List<Integer>> list = new ArrayList<>(); 
    Boolean flag = true; 
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return list; 
        
        //dfs_recursion(root, 0, flag);
        dfs_iteration(root);
        
        return list; 
    }
    
    public void bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>(); 
        queue.clear(); 
        queue.add(root);
        Boolean flag = false; 
        
        while (!queue.isEmpty()) {
            int size = queue.size(); 
            List<Integer> temp = new ArrayList<>();
            
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll(); 
                temp.add(current.val);
                
                if (null != current.left) queue.add(current.left);
                if (null != current.right) queue.add(current.right);
            }
            if (flag) {
                Collections.reverse(temp);
            }
            flag = !flag;
            list.add(temp);
        }
    }
    
    public void dfs_recursion(TreeNode root, int level, Boolean flag) {
        if (level == list.size()) {
            list.add(new ArrayList<Integer>());
        }
        
        if (flag) {
            list.get(level).add(root.val);
        }
        else {
            list.get(level).add(0, root.val);
        }
        
        
        if (root.left != null) dfs_recursion(root.left, level + 1, !flag);
        if (root.right != null) dfs_recursion(root.right, level + 1, !flag);
    }
}