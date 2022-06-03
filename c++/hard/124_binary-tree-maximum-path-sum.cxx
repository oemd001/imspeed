/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
    
    int max_value = INT_MIN; 
public:
    int maxPathSum(TreeNode* root) {
        if (root == nullptr) return 0; 
        
        dfs(root);
        
        return max_value; 
    }
    
    int dfs(TreeNode* root) {
        if (root == nullptr) return 0; 
        
        int left = std::max(0, dfs(root->left));
        int right = std::max(0, dfs(root->right));
        
        max_value = std::max(max_value, left + right + root->val);
        
        return std::max(left, right) + root->val;
    }
};

