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
        
       //return recursive(head, map);
        
        return iterative_optimized(head);
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
    
    public Node iterative_optimized(Node head) {
        if (head == null) return null;
        
        Node current = head; 
        
        while (current != null) {
            Node newNode = new Node(current.val);
            newNode.next = current.next; 
            current.next = newNode; 
            current = newNode.next; 
        }
        
        current= head; 
        
        while (current != null) {
            current.next.random = (current.random != null) ? current.random.next : null; 
            current = current.next.next; 
        }
        
        Node current_old_list = head; 
        Node current_new_list = head.next; 
        Node old_head = head.next; 
        
        Node ptr_old_list = head; // A->B->C
        Node ptr_new_list = head.next; // A'->B'->C'
        Node head_old = head.next;
        while (ptr_old_list != null) {
            ptr_old_list.next = ptr_old_list.next.next;
            ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;
            ptr_old_list = ptr_old_list.next;
            ptr_new_list = ptr_new_list.next;
        }
        return head_old;
    }
}