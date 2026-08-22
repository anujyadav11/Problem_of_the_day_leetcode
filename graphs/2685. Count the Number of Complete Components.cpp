/*********************************************** JAVA **************************************************/

// Optimal Solution - Counts complete connected components using DSU to group nodes, then verifying each component has exactly n*(n-1)/2 edges via edge count map.
/* "The complete graph formula n*(n-1)/2 is the key check — any component with fewer edges is missing connections. DSU naturally groups nodes and tracks component sizes. 
    Iterating only root nodes (find(vertex) == vertex) ensures each component is checked exactly once — a clean DSU pattern worth remembering." */

class DSU {
    int[] parent;
    int[] size;
    DSU(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;
        Arrays.fill(size, 1);
    }
    int find(int node) {
        // path compression — flatten tree for O(α) lookups
        if (parent[node] == node) return node;
        return parent[node] = find(parent[node]);
    }
    void union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);
        if (root1 == root2) return;
        // union by size — attach smaller tree under larger
        if (size[root1] > size[root2]) {
            parent[root2] = root1;
            size[root1] += size[root2];
        } else {
            parent[root1] = root2;
            size[root2] += size[root1];
        }
    }
}
class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        DSU dsu = new DSU(n);
        // first pass — union all connected nodes
        for (int[] edge : edges)
            dsu.union(edge[0], edge[1]);
        // count edges per component root
        Map<Integer, Integer> edgeCount = new HashMap<>();
        for (int[] edge : edges) {
            int root = dsu.find(edge[0]);
            edgeCount.put(root, edgeCount.getOrDefault(root, 0) + 1);
        }
        int completeCount = 0;
        for (int vertex = 0; vertex < n; vertex++) {
            // only process root nodes — each component has one root
            if (dsu.find(vertex) == vertex) {
                int nodeCount = dsu.size[vertex];
                // complete component needs exactly n*(n-1)/2 edges
                int expectedEdges = (nodeCount * (nodeCount - 1)) / 2;
                if (edgeCount.getOrDefault(vertex, 0) == expectedEdges)
                    completeCount++;
            }
        }
        return completeCount;
    }
}

// Time Complexity :- O(n + e × α(n)).
// Space Complexity :- O(n + e). 
