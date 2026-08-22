/*********************************************** JAVA **************************************************/

// Optimal Solution - Constructs binary tree from descriptions using HashMap for node reuse and HashSet to identify the root as the only non-child node.
/* "putIfAbsent is cleaner than containsKey + put — single operation, no double lookup. Root detection via child set is elegant — in any valid tree exactly one node has no parent. 
    The second loop scans parents not all nodes — but since every non-root appears as both parent and child, any valid parent not in child set must be the root." */

class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {
        // set tracks all child nodes — root is the only non-child
        Set<Integer> childSet = new HashSet<>();
        // map stores created nodes by value
        Map<Integer, TreeNode> map = new HashMap<>();
        for (int[] desc : descriptions) {
            // create parent node if not exists
            map.putIfAbsent(desc[0], new TreeNode(desc[0]));
            // create child node if not exists
            map.putIfAbsent(desc[1], new TreeNode(desc[1]));
            if (desc[2] == 1)
                // isLeft = 1 — attach as left child
                map.get(desc[0]).left = map.get(desc[1]);
            else
                // isLeft = 0 — attach as right child
                map.get(desc[0]).right = map.get(desc[1]);
            // mark child as non-root
            childSet.add(desc[1]);
        }
        // root is the only node that never appears as a child
        for (int[] desc : descriptions)
            if (!childSet.contains(desc[0]))
                return map.get(desc[0]);
        return null;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n).
