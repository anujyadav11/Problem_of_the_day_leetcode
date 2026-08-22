/*********************************************** JAVA **************************************************/

Optimal Solution - Recursive solution that checks balance by comparing subtree heights at every node.
                   This works but is inefficient—height is recomputed repeatedly; it can be optimized to O(n) by returning height and balance together in one DFS.

class Solution {
    public boolean isBalanced(TreeNode root) {
        // An empty tree is always balanced
        if (root == null)
            return true;
        // Compute height of left subtree
        int left = height(root.left);
        // Compute height of right subtree
        int right = height(root.right);
        // If height difference is more than 1, tree is not balanced
        if (Math.abs(left - right) > 1)
            return false;
        // Recursively check balance for left and right subtrees
        return isBalanced(root.left) && isBalanced(root.right);
    }
    int height(TreeNode root) {
        // Height of an empty tree is 0
        if (root == null)
            return 0;
        // Height is max of left and right subtree heights + 1
        int left = height(root.left);
        int right = height(root.right);
        return Math.max(left, right) + 1;
    }
}

Time Complexity :- O(n^2).
Space Complexity :- O(h). height of the tree.
