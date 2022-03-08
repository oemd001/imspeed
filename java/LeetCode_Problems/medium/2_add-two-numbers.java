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
        return solution2(l1, l2);
    }
    
    public ListNode solution1(ListNode l1, ListNode l2) {
        int l1_number = 0; 
        int l2_number = 0; 
        int counter = 0; 
        
        while (l1 != null) {
            l1_number = l1_number + (int)(l1.val * Math.pow(10, counter));
            counter++;
            l1 = l1.next; 
        }
        counter = 0; 
        
        while (l2 != null) {
            l2_number = l2_number + (int)(l2.val * Math.pow(10, counter));
            counter++;
            l2 = l2.next; 
        }
        
        counter = l2_number + l1_number; 
        String temp = Integer.toString(counter);
        ListNode node = new ListNode();
        ListNode output = node; 
        
        System.out.println(temp);
        
        for (int i = temp.length() - 1; i >= 0; i--) {
            int val = Integer.parseInt(Character.toString(temp.charAt(i)));
            node.next = new ListNode(val);
            node = node.next;
        }
        
        return output.next; 
        
    }
    
    public ListNode solution2(ListNode l1, ListNode l2) {
    ListNode dummyHead = new ListNode(0);
    ListNode p = l1, q = l2, curr = dummyHead;
    int carry = 0;
    while (p != null || q != null) {
        int x = (p != null) ? p.val : 0;
        int y = (q != null) ? q.val : 0;
        int sum = carry + x + y;
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