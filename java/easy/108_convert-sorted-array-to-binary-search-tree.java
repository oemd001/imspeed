/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        //We will need two balanced trees
        //right tree can be determined based on left tree. 
        //We can seperate the array in to two parts (and omit the middle one because that would be our root/head)
        //Also, if the length of nums is not odd, we can go ahead and omit the tree because it would never be balanced. (but we can try anyway)
        //Tree order appears to not matter
        
        //I want to find the length of the nums array first, and then traverse through the array to it's specific length when I need to. 
        //Additional arrays would increase both memory and time complexity
        //Also, I would want to find the median of the array because I want that value to be my head        
        //the first three elements of the tree will be balanced, so we can have a conditional that would check if nums.length is equal to there. If it's less than three, add it still. 
        //Will need bfs for this
        
        if (nums.length == 0) return null;
        return constructTreeFromArray(nums, 0, nums.length - 1);
    }
    
    public TreeNode constructTreeFromArray(int[] nums, int left, int right) {
        if (left > right) return null;
        int midpoint = left + (right - left) / 2;
        TreeNode node = new TreeNode(nums[midpoint]); //the midpoint is the root
        
        node.left = constructTreeFromArray(nums, left, midpoint - 1);
        node.right = constructTreeFromArray(nums, midpoint + 1, right);
        
        return node; 
    }
}

