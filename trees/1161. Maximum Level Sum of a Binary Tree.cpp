/************************************************** JAVA ************************************************/

Optimal Solution - We perform a level-order traversal using a queue, compute the sum of node values at each level, and track the level with the maximum sum.


class Solution {
    public int maxLevelSum(TreeNode root) {
        // Stores the maximum level sum found so far
        int maxSum = Integer.MIN_VALUE;
        // Stores the level having the maximum sum
        int resultLevel = 0;
        // Current level number (1-based indexing)
        int currLevel = 1;
        // Queue for level-order (BFS) traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // Perform BFS traversal level by level
        while (!queue.isEmpty()) {
            // Number of nodes at current level
            int size = queue.size();
            // Sum of values at current level
            int sum = 0;
            // Process all nodes at the current level
            while (size-- > 0) {
                TreeNode node = queue.poll();
                sum += node.val;
                // Add left child if it exists
                if (node.left != null)
                    queue.offer(node.left);
                // Add right child if it exists
                if (node.right != null)
                    queue.offer(node.right);
            }
            // Update maximum sum and level if current sum is greater
            if (sum > maxSum) {
                maxSum = sum;
                resultLevel = currLevel;
            }
            // Move to next level
            currLevel++;
        }
        // Return the level with the maximum sum
        return resultLevel;
    }
}

Time Complexity :- O(N).
Space Complexity :- O(W). - w is the maximum width of the tree
