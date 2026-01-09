/************************************************** JAVA **************************************************/

Optimal Solution - We use postorder traversal to compute subtree depths, and whenever both left and right depths are equal at the deepest level seen so far, 
                    we mark the current node as the root of the smallest subtree containing all the deepest nodes.


class Solution {
    // Stores the maximum depth found so far
    int maxDepth = 1;
    // Result node, which is the smallest subtree containing all deepest nodes
    TreeNode res = null;
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        // Start postorder traversal from depth 0
        postOrder(root, 0);
        return res;
    }
    // Postorder traversal that returns the maximum depth of the subtree
    int postOrder(TreeNode root, int depth) {
        // If node is null, return current depth
        if (root == null) return depth;
        // Recursively get depth of left and right subtrees
        int left = postOrder(root.left, depth + 1);
        int right = postOrder(root.right, depth + 1);
        // If both subtrees have the same depth,
        // this node could be a candidate answer
        if (left == right) {
            maxDepth = Math.max(maxDepth, left);
            // Update result only when this depth matches maxDepth
            if (maxDepth == left) {
                res = root;
            }
        }
        // Return the deeper subtree depth
        return Math.max(left, right);
    }
}

Time Complexity :- O(N).
Space Complexity :- O(N). 
