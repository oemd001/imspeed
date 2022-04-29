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
    int compare = 0; 
    Boolean flag = true;
    
    public Node connect(Node root) {
        if (root == null) return root;
        //iteration(root);
        //iterative_constant(root);
        recursive_constant(root);
        return root; 
    }
    
    public void iteration(Node root) {
        Queue<Node> queue = new LinkedList<>(); 
        queue.clear(); 
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size(); 
            
            for (int i = 0; i < size; i++) {
                Node current = queue.poll(); 
                
                if (i < size - 1) {
                    current.next = queue.peek(); 
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
    
    public void iterative_constant(Node root) {
        Node leftmost = root; 
        
        while (leftmost.left != null) {
            Node head = leftmost;  
            
            while (head != null) {
                head.left.next = head.right; 
                
                if (head.next != null) {
                    head.right.next = head.next.left; 
                }
                
                head = head.next; 
            }
            leftmost = leftmost.left; 
        }
        
    }
    
    
    public void recursive_constant(Node root) {
        if (root == null) {
            return; 
        }
        if (root.left != null) {
            root.left.next = root.right; 
        }
        if (root.right != null) {
            if (root.next != null) {
                root.right.next = root.next.left; 
            }
            else {
                root.right.next = null; 
            }
        }
        recursive_constant(root.left);
        recursive_constant(root.right);
    }
    
    
    
}