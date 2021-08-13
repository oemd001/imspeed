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
        ListNode leftHead = null;
        ListNode rightHead = null;
        while(head != null) {
            rightHead = head.next;
            head.next = leftHead;
            leftHead = head;
            head = rightHead;
    
        }
        return leftHead;
    }
}
