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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        return solution1(root);
    }
    
    public List<List<Integer>> solution1(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>(); 
        
        if (root == null) return list; 
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.clear(); 
        queue.add(root);
        boolean flag = false; 
        
        //odd numbers = left to right
        //even numbers = right to left
        
        while (!queue.isEmpty()) {
            int size = queue.size(); 
            List<Integer> temp_list = new ArrayList<>(); 
            
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll(); 
                temp_list.add(current.val);
                
                if (null != current.left) queue.add(current.left);
                if (null != current.right) queue.add(current.right);
            }
            if (flag) {
                Collections.reverse(temp_list);
                list.add(temp_list);
                flag = !flag;
            }
            else {
                list.add(temp_list);
                flag = !flag;
            }
        }
        
        return list; 
    }
}

