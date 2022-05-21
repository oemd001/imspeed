/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    HashMap<Node, Node> map = new HashMap<>(); 
    public Node cloneGraph(Node node) {
        if (node == null) return null; 
        
        return recursive(node);
    }
    
    public Node recursive(Node node) {
        //(2)
        //if we discover that there is a dupe, break because
        //we don't want to cycle
        if (map.containsKey(node)) {
            return map.get(node);
        }
        
        //(--)
        //Simple, creates the new node (deep copy) within the hashmap
        Node newCopy = new Node(node.val);
        map.put(node, newCopy);
        
        //(1)
        //we are iterating through each of the neighbor list 
        //and appending that to the newCopy node. 
        for (Node neighbor : node.neighbors) {
            newCopy.neighbors.add(recursive(neighbor));
        }
        return newCopy; 
    }
}

