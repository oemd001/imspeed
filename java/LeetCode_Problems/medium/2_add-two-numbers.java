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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return second(l1, l2);
    }
    //this first method works, but not for the edge case (10^100)
    private ListNode first(ListNode l1, ListNode l2) {
        StringBuilder sb = new StringBuilder();
        while (l1 != null) {
            int temp = l1.val;
            sb.append(temp);
            l1 = l1.next;
        }
        sb.reverse(); 
        int l1_val = Integer.valueOf(sb.toString());
        StringBuilder sv = new StringBuilder(); 
        
        while (l2 != null) {
            int temp = l2.val; 
            sv.append(temp);
            l2 = l2.next; 
        }
        sv.reverse();
        int l2_val = Integer.valueOf(sv.toString());
        int result = l2_val + l1_val;
        System.out.println(l2_val);
        
        ListNode head = new ListNode(result % 10);
        ListNode head2 = head; 
        result = result / 10;
        
        while (result != 0) {
            head.next = new ListNode(result % 10);
            head = head.next;
            result = result / 10; 
        }
        
        return head2; 
    }
    
    //this is a more optimized solution, this will work for the edge case (10^100)
    private ListNode second(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0; //if p != null, x = p.val. else, x = 0
            int y = (q != null) ? q.val : 0; //if q != null, y = p.val, else, y = 0
            int sum = carry + x + y; //this basically "hand carries" the value that is over 10
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}

/*

[2,4,9]
[5,6,4,9]

*/
