import java.util.*;

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
/*
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        int nums[]; 
        
        for (int i = 0; i < l1.size(); i++) { 
            nums.add(l1.get());
        }
        for (int i = 0; i < l2.size(); i++) {
            nums.add(l2.get());
        }
        
        Arrays.sort(nums, Collections.reverseOrder());
        
        LinkedList<Integer>result = new LinkedList<Integer>();
        for (int i = 0; i < nums.size(); i++) {
            result.add(i);
        }
        return result;
    }
}

*/

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //this is to hold the first head node
        ListNode temp_node = new ListNode(0); 
        
        //current_node is the thing that's actually being traversed
        ListNode current_node = temp_node; 
        
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current_node.next = l1; 
                l1 = l1.next; 
            }
            else {
                current_node.next = l2; 
                l2 = l2.next; 
            }
            current_node = current_node.next; 
            
        }
        if (l1 != null) {
            current_node.next = l1; 
            l1 = l1.next;
        }
        if (l2 != null) {
            current_node.next = l2;
            l2 = l2.next;
        }
        return temp_node.next;
    }
}
