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
        return test(head);
    }
    
    public Node test(Node head) {
        if (head == null) return null;
        
        Map<Node, Node> map = new HashMap<>();
        Node current = head; 
        
        while (current != null) {
            map.put(current, new Node(current.val, null, null)); //1st null is for next, 2nd null is for random
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
}

/*
O(n), time complexity O(n)
two "node" pointers

map<node, node>
three arguments val, next, random
val: value
next = linking to another node
random = linking another node

shallow copy: (sharing the same address and node on the same heap) <-- we don't want this
node a = new node(1);
a = b; 

deep copy: (creating a brand new node and placing it on the heap)
node a = new node(1);
node b = new node(1)

if all values are not unique; key, value pairing
key: listnode address within the heap
value: the head.val value of our value

*/
