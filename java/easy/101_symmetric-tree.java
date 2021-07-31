/*
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
//iterative solution
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true; 
        }
        
        if (!countLeaves(root, root)) {
            return false; 
        }
        else {
            return true;
        }
        /*
        TreeNode current = root; 
        List<Integer> arr = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        
        while (current != null || !stack.isEmpty()) {
            while(current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            arr.add(current.val);
            current = current.right;
        }
        
        int size = arr.size();
        int[] a = new int[size];
        int[] b = new int[size];
        if ((size - 1) % 2 != 0) {
            return false; 
        }
        else {
            for (int i = 0; i < size/2; i++) {
                a[i] = arr.get(i);
            }
            int j = 0;
            for (int i = size - 1; i > size/2; i--) {
                b[j] = arr.get(i);
                j++;
                
            }
            for (int i = 0; i < size/2; i++) {
                if (a[i] != b[i]) {
                    return false; 
                }
            }
            return true; 
        }
        */
    }
    
    public boolean countLeaves(TreeNode t1, TreeNode t2) {
    if (t1 == null && t2 == null) return true; //these must be implemented–if one side is null and the other isn't
    if (t1 == null || t2 == null) return false; //it'll retunr either true or false; 
    return (t1.val == t2.val) && countLeaves(t1.right, t2.left) && countLeaves(t1.left, t2.right);
    }
}

//edge cases + notes
/*
1. lots of crap on both trees
2. same shape on both, but different numbers
3. same numbers on both, but different direction/orientation <--how tf do you do this one

notes (conditions)
1. tests for symmetry
2. tests for same values
*/
