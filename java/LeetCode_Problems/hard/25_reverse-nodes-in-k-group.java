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
    
    public ListNode reverseKGroup(ListNode head, int k) {
        return constant_memory_itr(head, k);
        
    }
    
    public ListNode constant_memory_itr(ListNode head, int k) {
        
        ListNode current = head;
        ListNode ktail = null;
        
        ListNode new_head = null;
        
        while (current != null) {
            int count = 0; 
            
            current = head;
            
            while (count < k && current != null) {
                current = current.next; 
                count++; 
            }
            
            if (count == k) {
                ListNode revHead = classic_reverse(head, k);
                
                if (new_head == null) {
                    new_head = revHead; 
                }
                
                if (ktail != null) {
                    ktail.next = revHead; 
                }
                
                ktail = head; 
                head = current; 
            }
            
            if (ktail != null) {
                ktail.next = head; 
            }
            
        }
        return new_head == null ? head : new_head; 
    }
    
    public ListNode recursion_prohibited(ListNode head, int k) {
        int count = 0; 
        ListNode current = head; 
        
        while (count < k && current != null) {
            current = current.next; 
            count++; 
        }
        
        if (count == k) {
            ListNode reverseHead = classic_reverse(head, k);
            
            head.next = reverseKGroup(current, k);
            
            return reverseHead;
        }
        
        return head; 
    }
    
    public ListNode classic_reverse(ListNode head, int k) {
        
        ListNode previous = null; 
        ListNode current = head; 
        ListNode next = null; 
        
        while (k > 0) {
            next = current.next; 
            current.next = previous; 
            previous = current; 
            current = next; 
            k--;
        }
        
        return previous; 
    }
}

