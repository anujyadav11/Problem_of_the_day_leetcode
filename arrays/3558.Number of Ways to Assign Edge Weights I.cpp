/*********************************************** JAVA **************************************************/

// Optimal Solution - Counts valid edge weight assignments as 2^(maxDepth-1) using DFS to find tree depth from root and fast modular exponentiation.
/*  "Fast exponentiation halves the exponent each recursion — O(log n) vs O(n) naive multiplication. The parent parameter prevents revisiting in undirected tree DFS 
      — always pass parent to avoid false cycles. power(2, maxDepth - 1) reflects that each non-root level has 2 independent weight choices." */

class Solution {
    static final long MOD = 1_000_000_007L;
    // fast modular exponentiation — O(log exponent)
    private long power(long base, long exponent) {
        if (exponent == 0) return 1;
        long half = power(base, exponent / 2);
        long result = (half * half) % MOD;
        if (exponent % 2 == 1)
            result = (result * base) % MOD;
        return result;
    }
    // find maximum depth from given node avoiding parent
    private int getMaxDepth(Map<Integer, List<Integer>> adj, int node, int parent) {
        int depth = 0;
        for (int neighbor : adj.getOrDefault(node, new ArrayList<>())) {
            if (neighbor == parent) continue;
            depth = Math.max(depth, getMaxDepth(adj, neighbor, node) + 1);
        }
        return depth;
    }
    public int assignEdgeWeights(int[][] edges) {
        // build undirected adjacency list
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int[] edge : edges) {
            adj.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            adj.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }
        // max depth from root node 1
        int maxDepth = getMaxDepth(adj, 1, 0);
        // number of valid assignments = 2^(maxDepth - 1)
        return (int) power(2, maxDepth - 1);
    }
}

// Time Complexity :- O(n + log n).
// Space Complexity :- O(n).
