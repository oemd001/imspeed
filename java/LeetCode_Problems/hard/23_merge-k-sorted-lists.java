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
    
    public ListNode mergeKLists(ListNode[] lists) {
        return priorityQueue_solution(lists);
    }
    
    //divide and conquer solutionset (slow asf) --> space complexity @ O(1)  runtime @ O(n * k)
    public ListNode divide_and_conquer(ListNode[] lists) {
        /*
        a, b, c
        ab, c
        abc
        
        a, b, c, d
        ab, c, d
        abc, d
        abcd
        */
        if (lists.length == 0) return null; 
        if (lists.length == 1) return lists[0];
        if (lists.length == 1 && lists[0] == null) return null; 
        
        ListNode combined = merge_helper_iterative(lists[0], lists[1]); 
        
        for (int i = 2; i < lists.length; i++) {
            combined = merge_helper_iterative(combined, lists[i]);
        }
        
        return combined; 
    }
    
    public ListNode merge_helper_iterative(ListNode list1, ListNode list2) {
        ListNode dummynode = new ListNode(-1);
        ListNode current = dummynode; 
        
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                current.next = list1; 
                list1 = list1.next; 
            }
            else {
                current.next = list2; 
                list2 = list2.next; 
            }
            current = current.next; 
        }
        
        if (list1 == null) current.next = list2; 
        if (list2 == null) current.next = list1; 
        
        return dummynode.next; 
        
    }
    
    public ListNode merge_helper_recursive(ListNode list1, ListNode list2) {
        if (list1 == null) return list2; 
        if (list2 == null) return list1; 
        
        if (list1.val < list2.val) {
            list1.next = merge_helper_recursive(list1.next, list2);
            return list1; 
            
        }
        else {
            list2.next = merge_helper_recursive(list1, list2.next);
            return list2; 
        }
    }
    
    //priorityQueue solutionSet. space complexity @ O(n) runtime @ O(n) 
    public ListNode priorityQueue_solution(ListNode[] lists) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(); 
        
        for (int i = 0; i < lists.length; i++) {
            while (lists[i] != null) {
                queue.add(lists[i].val);
                lists[i] = lists[i].next; 
            }
        }
        
        ListNode result = new ListNode(-1);
        ListNode current = result; 
        
        while (!queue.isEmpty()) {
            current.next = new ListNode(queue.poll());
            current = current.next; 
        }
        
        return result.next; 
    }
}

