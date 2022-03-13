/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        return solution3(headA, headB);
    }
    
    public ListNode solution(ListNode headA, ListNode headB) {
        
        Set<ListNode> set = new HashSet<>(); 
        
        while (headA != null || headB != null) {
            if (headA != null) {
                if (set.contains(headA)) {
                    return headA;
                }
                set.add(headA);
                headA = headA.next; 
                continue; 
            }
            if (headB != null) {
                if (set.contains(headB)) {
                    return headB; 
                }
                set.add(headB);
                headB = headB.next; 
                continue; 
            }
        }
        return null;
    }
    /*
    This solution works in the sense that we literally traverse both lists so that they eventually meet up
    at the same location. 
    
    Very sketch, but it works. If there are nothing in common, it'll end up at null, which is basically common too
    */
    public ListNode solution2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        
        ListNode a_pointer = headA; 
        ListNode b_pointer = headB; 
        
        while (a_pointer != b_pointer) {
            if (a_pointer == null) {
                a_pointer = headB; 
            }
            else {
                a_pointer = a_pointer.next; 
            }
            
            if (b_pointer == null) {
                b_pointer = headA; 
            }
            else {
                b_pointer = b_pointer.next; 
            }
            
        }
        
        return a_pointer; 
        
    }
    
}

