/*********************************************** JAVA - I did not solve this question; this one is too hard for me. Reference from the Kernel Queen YouTube channel. **************************************************/

// Optimal Solution - Answers edge weight assignment queries using binary lifting LCA to compute path lengths and precomputed powers of 2 for counting valid assignments.
/* "Binary lifting preprocesses 2^j-th ancestors in O(n log n) — each LCA query then runs in O(log n) by lifting the deeper node first, then lifting both simultaneously.
    The key invariant is the root's parent points to itself — this prevents out-of-bounds in the ancestor table. Precomputing powers of 2 avoids repeated modular exponentiation per query." */

class Solution {
    // precomputed powers of 2 modulo MOD
    static int[] binaryExponent;
    static final long MOD = 1_000_000_007L;
    static {
        binaryExponent = new int[100001];
        long prod = 1L;
        for (int i = 1; i < binaryExponent.length; i++) {
            binaryExponent[i] = (int) prod;
            prod = (prod * 2L) % MOD;
        }
    }
    int[][] ancestors;
    int[] ans, parent, depth;
    boolean[] visited;
    // build binary lifting table for LCA queries
    private void buildAncestors(int[] parent, int numNodes) {
        int logN = (int) (Math.log(numNodes) / Math.log(2)) + 1;
        ancestors = new int[logN][numNodes + 1];
        int m = ancestors[0].length;
        // level 0: direct parent
        for (int i = 0; i < m; i++)
            ancestors[0][i] = parent[i];
        // level j: 2^j-th ancestor
        for (int i = 1; i < logN; i++)
            for (int j = 1; j < m; j++)
                ancestors[i][j] = ancestors[i-1][ancestors[i-1][j]];
    }

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length;
        int q = queries.length;
        ans = new int[q];
        parent = new int[n + 2];
        visited = new boolean[n + 2];
        depth = new int[n + 2];
        // build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n + 1; i++)
            adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        // root's parent points to itself
        parent[1] = 1;
        dfs(1, adj, 0);
        buildAncestors(parent, n + 1);
        // answer each query using path length between nodes
        for (int i = 0; i < q; i++)
            ans[i] = binaryExponent[getPathLength(queries[i][0], queries[i][1])];
        return ans;
    }
    // path length = depth[u] + depth[v] - 2 * depth[lca]
    private int getPathLength(int u, int v) {
        int lca = depth[u] >= depth[v] ? LCA(u, v) : LCA(v, u);
        return depth[u] + depth[v] - 2 * depth[lca];
    }
    private int LCA(int deeper, int shallower) {
        int diff = depth[deeper] - depth[shallower];
        // lift deeper node to the same depth as the shallower
        for (int idx = 0; (1 << idx) <= diff; idx++)
            if (((1 << idx) & diff) > 0)
                deeper = ancestors[idx][deeper];
        if (deeper == shallower) return deeper;
        // lift both until they diverge — LCA is one step above
        for (int jump = ancestors.length - 1; jump >= 0; jump--)
            if (ancestors[jump][deeper] != ancestors[jump][shallower]) {
                deeper = ancestors[jump][deeper];
                shallower = ancestors[jump][shallower];
            }
        return ancestors[0][deeper];
    }
    private void dfs(int node, List<List<Integer>> adj, int depthValue) {
        visited[node] = true;
        depth[node] = depthValue;
        for (int v : adj.get(node)) {
            if (visited[v]) continue;
            parent[v] = node;
            dfs(v, adj, depthValue + 1);
        }
    }
}

// Time Complexity :- O((n + q) log n) .
// Space Complexity :- O(n log n).
