/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    ListNode ref; 
    int max = 0; 
    public int pairSum(ListNode head) {
        /*
        Twin nodes:
            0, 1, 2, 3
            0 and 1 are twin nodes
            1 and are twin nodes. 
            We want to add those two values together and find the maximum value of the twin nodes. 
        
        O(n) method:
            Loop through the linked list and add all values in to an array
            Then, add the twin values together
        
        O(1) method:
            First, iterate to the end of the linked list and reverse it. 
            Second, once you do that, you can simply do a "two pointer" method of adding the nodes together
            Finally, do a Math.max(max, node1.val + node2.val) and return max
        */
        return constant_space(head);
    }
    public int naive(ListNode head) {
        List<Integer> list = new ArrayList<>(); 
        
        while (head != null) {
            list.add(head.val);
            head = head.next; 
        }
        
        if (list.size() == 2) {
            return list.get(0) + list.get(1);
        }
        
        int max = 0; 
        
        for (int i = 0; i < list.size() / 2; i++) {
            max = Math.max(max, list.get(i) + list.get(list.size() - 1 - i));
        }
        
        return max; 
    }
    
    //O(n) --> recursive call stack
    public int recursive(ListNode head) {
        ref = head; 
        max = 0; 
        check(head);
        return max; 
    }
    
    public void check(ListNode head) {
        if (head == null) return; 
        check(head.next);
        max = Math.max(max, head.val + ref.val);
        ref = ref.next; 
    }
    
    public int constant_space(ListNode head) {
        ListNode t1 = head; 
        ListNode t2 = head; 
        
        while (t2 != null) {
            t1 = t1.next; 
            t2 = t2.next.next; 
        }
        
        ListNode reverse_node = reverse(t1);
        
        while (reverse_node != null) {
            int rev_val = reverse_node.val; 
            int head_val = head.val;
            max = Math.max(max, head.val + reverse_node.val);
            head = head.next; 
            reverse_node = reverse_node.next; 
        }
        
        return max; 
    }
    
    public ListNode reverse(ListNode head) {
        ListNode current_node = head; 
        ListNode next_node = null; 
        ListNode prev = null; 
        
        while (current_node != null) {
            next_node = current_node.next; 
            current_node.next = prev; 
            prev = current_node; 
            current_node = next_node; 
        }
        return prev; 
    }
}
