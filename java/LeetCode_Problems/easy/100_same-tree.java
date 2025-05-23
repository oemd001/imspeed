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
//recursive method, arguably much much better
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        //if both trees are null
        if (p == null && q == null) {
            return true; 
        }
        //if only one tree is null
        if (q == null || p == null) {
            return false; 
        }
        //if the values when run recusively is different
        if (p.val != q.val) {
            return false; 
        }
        return isSameTree(p.right, q.right) && isSameTree(p.left, q.left);
    }
}
//1 ms, 36.5 MB implementation. Not great imho. 
//This implementation is actually the iterative method
/*
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if ((p == null && q != null) || (p != null && q == null)) {
            return false; 
        }
        TreeNode ptemp = p;
        TreeNode qtemp = q;
        int pcount = 0; 
        int qcount = 0; 
        
        TreeNode p_current = p; 
        TreeNode q_current = q;
        Stack<TreeNode> qstack = new Stack<>();
        Stack<TreeNode> pstack = new Stack<>();
        List<Integer> pcomp = new ArrayList<>();
        List<Integer> qcomp = new ArrayList<>();
        
        while (ptemp != null) {
            ptemp = ptemp.left;
            pcount++;
        }
        
        while (qtemp != null) {
            qtemp = qtemp.left;
            qcount++;
        }
        
        while (p_current != null || !pstack.isEmpty()) {
            while (p_current != null) {
                pstack.push(p_current);
                p_current = p_current.left;
                
            }
            p_current = pstack.pop(); 
            pcomp.add(p_current.val);
            p_current = p_current.right; 
        }
        while (q_current != null || !qstack.isEmpty()) {
            while (q_current != null) {
                qstack.push(q_current);
                q_current = q_current.left;
            }
            q_current = qstack.pop(); 
            qcomp.add(q_current.val);
            q_current = q_current.right; 
        }
        if (pcomp.size() == qcomp.size() && pcount == qcount) {
            for (int i = 0; i < pcomp.size(); i++) {
                double temp = pcomp.get(i) + qcomp.get(i);
                if (temp / 2 != pcomp.get(i) && temp / 2 != qcomp.get(i)) {
                    return false; 
                }
            }
            return true; 
        }
        return false; 
    }
}
*/



