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
    public ListNode reverseList(ListNode head) {
        //return iterative(head);
        return recursive(head);
    }
    public ListNode iterative(ListNode head) {
        ListNode current = head; 
        ListNode previous = null; 
        ListNode next = null; 
        
        while (current != null) {
            next = current.next; 
            current.next = previous; 
            previous = current; 
            current = next; 
        }
        
        return previous; 
    }
    
    public ListNode recursive(ListNode head) {
        if (head == null || head.next == null) {
            return head; 
        }
        
        ListNode previous = recursive(head.next);
        head.next.next = head; 
        head.next = null;
        
        return previous; 
    }
}