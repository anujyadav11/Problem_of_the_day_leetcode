/*********************************************** JAVA **************************************************/

// Optimal Solution - Uses postorder DFS to calculate each subtree’s sum and node count and count nodes whose value equals their subtree average.
/* “I use postorder DFS because to calculate a node’s subtree average, I first need the sum and number of nodes in its left and right subtrees. My recursive function returns a pair containing the subtree sum and count. 
    I combine those values with the current node, calculate the integer average, and increment a global result if the current node equals that average.” */

class Solution {
    // Stores the number of nodes whose value equals
    // the average of their entire subtree
    int res;
    public Pair<Integer, Integer> solve(TreeNode root) {
        // Empty subtree has sum = 0 and count = 0
        if (root == null) {
            return new Pair<>(0, 0);
        }
        // Get sum and count from left subtree
        Pair<Integer, Integer> left = solve(root.left);
        // Get sum and count from right subtree
        Pair<Integer, Integer> right = solve(root.right);
        int leftSum = left.getKey();
        int leftCount = left.getValue();
        int rightSum = right.getKey();
        int rightCount = right.getValue();
        // Calculate sum of current subtree
        int SUM = leftSum + rightSum + root.val;
        // Calculate number of nodes in current subtree
        int Count = leftCount + rightCount + 1;
        // Integer division is intended by the problem
        int avg = SUM / Count;
        // Check whether current node equals subtree average
        if (avg == root.val)
            res++;
        // Return subtree sum and subtree node count
        return new Pair<>(SUM, Count);
    }
    public int averageOfSubtree(TreeNode root) {
        // Reset result
        res = 0;
        // Process entire tree
        solve(root);
        return res;
    }
}

// Time Complexity :- O(N).
// Space Complexity :- O(H).
