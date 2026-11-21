/*********************************************** JAVA **************************************************/

// Optimal Solution - Implemented O(n) tree DFS with backtracking-based nearest ancestor tracking, dynamic parent reassignment, and postorder subtree-size aggregation.
/* "I build an adjacency list from the parent array and perform DFS from the root. During traversal, I maintain the nearest ancestor for each character using a fixed-size array. When visiting a node, 
    I save the previous matching ancestor and update the current character's latest occurrence. After processing its children, I restore the previous state and reassign the node to its nearest matching ancestor if one exists. 
    Since children are processed first, I can aggregate their subtree sizes into the updated parent." */

class Solution {
    // Adjacency list representing the original tree.
    // Key   = parent node
    // Value = list of its children.
    private Map<Integer, List<Integer>> adj = new HashMap<>();
    // Stores the size of the subtree rooted at each node.
    private int[] resultSize;
    // Tracks the nearest ancestor for each character (a-z)
    // while traversing the tree using DFS.
    // last[c] = node index of the nearest ancestor having character c.
    // -1 means no such ancestor exists.
    private int[] last;
    private void dfs(int currNode, String s, int[] parent) {
        // Get the character index of the current node.
        // Example: 'a' -> 0, 'b' -> 1, ..., 'z' -> 25.
        int c = s.charAt(currNode) - 'a';
        // Save the previous nearest ancestor having the same character.
        // This is needed to restore the state after exploring this subtree.
        int nearestAncestor = last[c];
        // Mark the current node as the latest node having this character.
        last[c] = currNode;
        // Traverse all children of the current node.
        for (int child : adj.getOrDefault(currNode, Collections.emptyList())) {
            dfs(child, s, parent);
        }
        // Backtrack: restore the previous nearest ancestor.
        // This ensures sibling branches do not affect each other.
        last[c] = nearestAncestor;
        // Root node (0) has no parent to update.
        if (currNode != 0) {
            // Initially, the current node keeps its original parent.
            int newParent = parent[currNode];
            // If an ancestor with the same character exists,
            // reassign the current node to that nearest matching ancestor.
            if (nearestAncestor != -1) {
                newParent = nearestAncestor;
            }
            // Update the parent relationship.
            parent[currNode] = newParent;
            // Add the current node's subtree size to its new parent.
            resultSize[newParent] += resultSize[currNode];
        }
    }
    public int[] findSubtreeSizes(int[] parent, String s) {
        // Number of nodes in the tree.
        int n = parent.length;
        // Build the adjacency list using the original parent array.
        for (int i = 1; i < n; i++) {
            int u = parent[i];
            int v = i;
            // Add node v as a child of node u.
            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        }
        // Initially, every node forms a subtree of size 1.
        resultSize = new int[n];
        Arrays.fill(resultSize, 1);
        // Initialize the nearest-ancestor tracking array.
        last = new int[26];
        Arrays.fill(last, -1);
        // Start DFS from the root node (0).
        dfs(0, s, parent);
        // Return the final subtree sizes.
        return resultSize;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n).
