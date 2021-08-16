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
//recursion: O(n), memory: O(n)
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        
        
        int left = maxDepth(root.left); 
        int right = maxDepth(root.right);
        return max(left, right) + 1;
    }
    private int maxDepth_iterative(TreeNode root) {
    if(root == null) {
        return 0;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    int count = 0;
    while(!queue.isEmpty()) {
        int size = queue.size();
        while(size > 0) {
            TreeNode node = queue.poll();
            size--;
            if(node.left != null) {
                queue.offer(node.left);
            }
            if(node.right != null) {
                queue.offer(node.right);
            }
        }
        count++;
    }
    return count;
}

}

//edge cases
/*
Root could be equal to 0
Will be slow O(n) time, with n being the number of tree nodes that exist
If there is only one value on the branch, return 1
Checks if there is only one branch straight down
notes
Recursive might be the best way to go
DFS might help a lot

*/


/*
it's also worth mentioning that if you want to recursively visit all the nodes:
maxDepth(root.left);
maxDepth(root.right);
System.out.print(root.val + " ");

would work. This is pretty mind bogglig to be honest but I got it!

*/
