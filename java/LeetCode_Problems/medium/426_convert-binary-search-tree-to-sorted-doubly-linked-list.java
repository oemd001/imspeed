/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/

class Solution {
    Node first = null; 
    Node last = null; 
    public Node treeToDoublyList(Node root) {
        //potential that root would be null
        if (root == null) return null; 
        helper(root);
        last.right = first; 
        first.left = last; 
        return first; 
    }
    
    public void helper(Node root) {
        //we want this to continue running until the tree reaches the end
        if (root != null) {
            //this is an inorder traversal so we would want to handle the left side of the tree first
            helper(root.left);
            if (last != null) {
                //this is to link the next "right" node value for the last node value to the next root value
                last.right = root; 
                //this is to link the left value to last. 
                root.left = last; 
                //(in a sense, we are adding everything from the last Node to the first Node)
            }
            else {
                //if there is nothing in last, we want to set our first "first" node value
                //this should only run once
                first = root; 
            }
            //this will be run to ensure that last != null, as if this is not written, we'll get in to an infinite loop at else
            last = root; 
            //this is an inorder traversal, the right side of the tree will be handled last. 
            helper(root.right);
        }
    }
}

/*
dfs inorder traversal
Two node values. 
1 = 2 = 3 = 4 = 5 = 1
recursion 
O(N) runs through the tree once
O(H * W) depends height and width of the tree
*/
