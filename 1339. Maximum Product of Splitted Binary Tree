/***************************************************** JAVA *************************************************/


Optimal solution - We first compute the total sum of the binary tree, then use DFS to evaluate every possible subtree split 
                    and maximize the product of the subtree sum and the remaining tree sum.


class Solution {
    // Total sum of the tree
    long SUM = 0;
    // Stores the maximum product found
    long maxP = 0;
    static final int MOD = 1000000007;
    // DFS to calculate subtree sums and maximize product
    public long find(TreeNode root) {
        if (root == null) return 0;
        // Sum of left and right subtrees
        long leftSum = find(root.left);
        long rightSum = find(root.right);
        // Current subtree sum
        long subTreeSum = root.val + leftSum + rightSum;
        // Remaining tree sum after cutting this subtree
        long remainingSum = SUM - subTreeSum;
        // Update maximum product
        maxP = Math.max(maxP, subTreeSum * remainingSum);
        // Return subtree sum to parent
        return subTreeSum;
    }
    // DFS to calculate total sum of the tree
    public long totalSum(TreeNode root) {
        if (root == null) return 0;
        return root.val + totalSum(root.left) + totalSum(root.right);
    }
    public int maxProduct(TreeNode root) {
        if (root == null) return 0;
        // Step 1: Compute total sum of tree
        SUM = totalSum(root);
        // Step 2: DFS to compute max product
        find(root);
        // Return result modulo
        return (int) (maxP % MOD);
    }
}

Time Complexity :- O(N) each node are begin visited twice.
Space Complexity :- O(H) height of the tree
