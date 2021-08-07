class Solution {
    int preorderIndex;
    Map<Integer, Integer> inorderIndexMap;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preorderIndex = 0;
        // build a hashmap to store value -> its index relations
        inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return arrayToTree(preorder, 0, preorder.length - 1);
    }

    private TreeNode arrayToTree(int[] preorder, int left, int right) {
        // if there are no elements to construct the tree
        if (left > right) return null;

        // select the preorder_index element as the root and increment it
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);

        // build left and right subtree
        // excluding inorderIndexMap[rootValue] element because it's the root
        root.left = arrayToTree(preorder, left, inorderIndexMap.get(rootValue) - 1);
        root.right = arrayToTree(preorder, inorderIndexMap.get(rootValue) + 1, right);
        return root;
    }
}

/*
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(0, 0, inorder.length - 1, preorder, inorder);
        //first 0: where is the root node
        //second argument (0) and third arguemnt (inorder.length - 1) are the boundaries of the inorder array
        //fourth and fifth argument basically have the preorder and inorder arrays
    }
    public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        //if the starting position is greater than the length of the preorder array, return null
        //if the initial position of the Inorder array is greater than the total length of the inorder array, return null
        if (preStart > preorder.length - 1 || inStart > inEnd) return null;
        
        TreeNode root = new TreeNode(preorder[preStart]);
        
        //to add the left and right elements, we will need "inorder" because everything 
        //right of the root (3, in this case) will be on the right of the tree
        //everything left of the root, will be on the left
        
        int inIndex = 0;
        
        for (int i = inStart; i < inEnd; i++) {
            if (root.val == inorder[i]) {
                inIndex = i;
            }
        }
        
        root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
        root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
        
        return root; 
    }
}
*/