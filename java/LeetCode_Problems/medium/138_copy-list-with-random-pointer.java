/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    
    public Node copyRandomList(Node head) {
        //return iterative(head);
        Map<Node, Node> map = new HashMap<>();
        
        return recursive(head, map);
    }
    public Node iterative(Node head) {
        if (head == null) return null; 
        
        Map<Node, Node> map = new HashMap<>(); 
        Node current = head; 
        
        while (current != null) {
            map.put(current, new Node(current.val, null, null));
            
            current = current.next; 
        }
        
        current = head; 
        
        while (current != null) {
            map.get(current).next = map.get(current.next);
            map.get(current).random = map.get(current.random);
            
            current = current.next; 
        }
        
        return map.get(head);
    }
    
    public Node recursive(Node head, Map<Node, Node> map) {
        if (head == null) return null; 
        
        //exit condition
        if (map.containsKey(head)) {
            return map.get(head);
        }
        
        Node node = new Node(head.val, null, null);
        map.put(head, node);
        
        node.next = recursive(head.next, map);
        node.random = recursive(head.random, map);
        
        return node; 
    }
}