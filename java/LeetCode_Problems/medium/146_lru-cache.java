class LRUCache {
    
    final Node head = new Node();
    final Node tail = new Node();
    Map<Integer, Node> node_map;
    int cache_capacity;
    
    public LRUCache(int capacity) { //least recently used
        //we need to use a linkedlist–for the ordering system. 
        //that will keep a structure: should have used that instead of arraylsit
        //we need to use doubly linked list
        //hashmap: used for gets and puts
        
        node_map = new HashMap(capacity);
        this.cache_capacity = capacity;
        head.next = tail; //this connects our tail and head nodes together
        tail.prev = head; 
        
        
    }
    
    public int get(int key) {
        int result = -1; 
        Node node = node_map.get(key);
        if (node != null) {
            result = node.val;
            remove(node); //this removes the node
            add(node); //and adds it to the front
        }
        return result; 
        
    }
    
    public void put(int key, int value) { //can either add a node or override a node
        Node node = node_map.get(key);
        if (node != null) {
            remove(node); //if the node is there and we're removing it, we're technically using it
            node.val = value; 
            add(node); //this basically puts it in the front
        }
        else {
            if (node_map.size() == cache_capacity) { //if we're adding a new node, are we at capacity?
                node_map.remove(tail.prev.key);
                remove(tail.prev);
            }
            
            Node new_node = new Node();
            new_node.key = key;
            new_node.val = value; 
            
            node_map.put(key, new_node);
            add(new_node); //this adds the new node to the linked list
        }
        
    }
    
    public void add(Node node) { //this adds to the front of the list
        Node head_next = head.next;
        node.next = head_next;
        head_next.prev = node;
        head.next = node;
        node.prev = head; 
    }
    
    public void remove(Node node) { 
        Node next_node = node.next;
        Node prev_node = node.prev; //this is the node we want to remove
        
        next_node.prev = prev_node; 
        prev_node.next = next_node; 
        
    }
    
    class Node {
        int key;
        int val;
        Node prev;
        Node next;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */



