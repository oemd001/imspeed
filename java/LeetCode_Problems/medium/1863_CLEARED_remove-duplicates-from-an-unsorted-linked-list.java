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
    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        return discussion(head);
    }
    
    public ListNode discussion(ListNode head) {
        // Key: val  Value: its frequency
        Map<Integer, Integer> map = new HashMap<>();
        ListNode curr = head;
        while (curr != null) {
            map.put(curr.val, map.getOrDefault(curr.val, 0) + 1);
            curr = curr.next;
        }
        
        ListNode dummy = new ListNode(0);
        curr = dummy;
        while (head != null) {
            if (map.get(head.val) == 1) {
                curr.next = head;
                curr = curr.next;
            }
            
            head = head.next;
        }
        
        curr.next = null;
        return dummy.next;
    }
    
    //Do they want this in ascending order?
    public ListNode naive(ListNode head) {
        Map<Integer, Integer> map = new HashMap<>(); 
        
        ListNode root = head; 
        
        ListNode result = new ListNode(-1);
        ListNode current = result; 
        
        while (root != null) {
            if (map.containsKey(root.val)) {
                map.put(root.val, map.get(root.val) + 1);
            }
            else {
                map.put(root.val, 0);
            }
            root = root.next; 
        }
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 0) {
                current.next = new ListNode(entry.getKey());
                current = current.next; 
            }
        }
        
        return result.next; 
    }
}
