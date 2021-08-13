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
//https://www.youtube.com/watch?v=jY-EUKXYT20&ab_channel=DineshVaryani (very helpful)
class Solution {
public ListNode reverseList(ListNode head) {
    
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
}