/*********************************************** JAVA **************************************************/

Optimal Solution - Perform DFS while building binary numbers along root-to-leaf paths and sum all resulting values.
                   “At each node, I treat the path as a binary number by left-shifting the accumulated value and adding the current bit. When reaching a leaf, I return the computed number.”

class Solution {
    public int sumRootToLeaf(TreeNode root) {
        // Start DFS traversal with initial value 0
        return solve(root, 0);
    }
    public int solve(TreeNode root, int val) {
        // Base case: if node is null, contribute 0 to sum
        if (root == null)
            return 0;
        // Update current binary number:
        // Multiply previous value by 2 (left shift)
        // and add current node's value (0 or 1)
        val = (2 * val) + root.val;
        // If it's a leaf node, return the computed binary number
        if (root.left == null && root.right == null)
            return val;
        // Otherwise, return sum of left and right subtree paths
        return solve(root.left, val) + solve(root.right, val);
    }
}

Time Complexity :- O(N).
Space Complexity :- O(H). worst case if it is a screwed tree.
