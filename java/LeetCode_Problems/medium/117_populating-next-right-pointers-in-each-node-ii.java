/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if (root == null) return root; 
        
        iterative(root);
        
        return root; 
    }
    
    public void iterative(Node root) {
        Node head = null; 
        Node prev = null; 
        
        while (root != null) {
            while (root != null) {
                if (root.left != null) {
                    if (head == null) {
                        head = root.left; 
                        prev = root.left; 
                    }
                    else {
                        prev.next = root.left; 
                        prev = prev.next; 
                    }
                }
                
                if (root.right != null) {
                    if (head == null) {
                        head = root.right;
                        prev = root.right; 
                    }
                    else {
                        prev.next = root.right; 
                        prev = prev.next; 
                    }
                }
                root = root.next; 
            }
            root = head; 
            prev = null; 
            head = null; 
        }
    }
    
    //TODO: Recursive Solution
}

