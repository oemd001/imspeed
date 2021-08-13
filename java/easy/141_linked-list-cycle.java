/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        return containsDuplicate(head);
    }
    private boolean containsDuplicate(ListNode head) {
        //this is actually much faster and uses less memory but the latter doesn't really matter right now. 
        //0 ms runtime and 43.2 MB memory used
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null && slow != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (fast == slow) return true; 
        }
        
        return false; 
    }
    
    private boolean hashsetCycle(ListNode head) {
        //runtime 9 ms and 44.8 memory used. 
        HashSet<ListNode> set = new HashSet<ListNode>(); /
        //I'm guessing that this is like a HashMap, it stores the value as well as the memory location. Not entirely sure but this is pretty freaking cool. 
        //HashSet uses O(n) time because this will iterate through the entire ListNode only once. 
        while (head != null) {
            if (set.contains(head)) return true; 
            
            set.add(head);
            
            head = head.next;
        }
        return false; 
    }
}
