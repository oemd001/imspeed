/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/

class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        return me(p, q);
    }
    
    public Node test1(Node p, Node q) {
        Set<Node> nodeSet = new HashSet<>();
        Node t = p; 
        while (t != null) {
            nodeSet.add(t);
            t = t.parent; 
        }
        
        t = q; 
        while (t != null) {
            if (nodeSet.contains(t)) {
                return t; 
            }
            t = t.parent; 
        }
        return null; 
    }
    
    public Node me(Node p, Node q) {
        Set<Node> set = new HashSet<>();
        
        //we want to traverse up as much as possible
        while (p != null) {
            set.add(p);
            p = p.parent;
        }
        
        //check if the other node has the same value
        
        while (q != null) {
            if (set.contains(q)) {
                return q; 
            }
            else {
                q = q.parent; 
            }
        }
        return null;
    }
}

/*
We would place the "traceable ancestry" in a set. 
** ie: 6 and 4. We would place the lineage up (5 and 3) on to the set. 
** We would place 4, (5, 2 and 3) on to the set and attempt to find if there are any common factors here. 

Working on this initially: How do I traverse up/down?
** turns out, because this is a linked list, if we just reference the node's parent value, we would be able to 
traverse up. 
**p.parent goes up, p.left/p.right would go left or right



*/
