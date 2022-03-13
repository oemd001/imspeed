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
    public ListNode removeElements(ListNode head, int val) {
        return optimal(head, val);
    }
    
    public ListNode optimal(ListNode head, int val) {
        ListNode temp_head = new ListNode(-1);
        ListNode current = temp_head; 
        
        while (head != null) {
            if (val == head.val) {
                head = head.next; 
            }
            else {
                current.next = new ListNode(head.val);
                current = current.next; 
                head = head.next; 
                
            }
        }
            
        return temp_head.next; 
    }
}

