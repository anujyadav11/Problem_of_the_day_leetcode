/*********************************************** JAVA **************************************************/

// Optimal Solution - Maximize spanning tree stability using binary search with Union-Find validation under edge upgrade constraints.

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
class Solution {
    public int maxStability(int n, int[][] edges, int k) {
        int edgeCount = edges.length;
        // DSU parent array
        int[] parent = new int[n];
        // Initialize DSU
        for (int i = 0; i < n; i++)
            parent[i] = i;
        // Store optional edges
        List<int[]> optionalEdges = new ArrayList<>();
        int minWeight = Integer.MAX_VALUE;
        int minMustWeight = Integer.MAX_VALUE;
        int maxWeight = Integer.MIN_VALUE;
        int mustEdgeCount = 0;
        // Process edges
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            int must = edge[3];
            minWeight = Math.min(minWeight, w);
            maxWeight = Math.max(maxWeight, w);
            // Mandatory edges
            if (must == 1) {
                int parentU = find(parent, u);
                int parentV = find(parent, v);
                // If mandatory edges form cycle → impossible
                if (parentU == parentV)
                    return -1;
                // Union
                parent[parentU] = parentV;
                minMustWeight = Math.min(minMustWeight, w);
                mustEdgeCount++;
            } else {
                // Optional edges
                optionalEdges.add(edge);
            }
        }
        // If mandatory edges already form MST
        if (mustEdgeCount == n - 1)
            return minMustWeight;
        // Sort optional edges by decreasing weight
        Collections.sort(optionalEdges, (a, b) -> b[2] - a[2]);
        int left = minWeight;
        int right = (minMustWeight == Integer.MAX_VALUE) ? 2 * maxWeight : minMustWeight;
        int answer = -1;
        int remainingEdgesNeeded = n - 1 - mustEdgeCount;
        // Binary search on stability value
        while (left <= right) {
            int mid = (left + right) / 2;
            if (check(optionalEdges, mid, remainingEdgesNeeded, parent.clone(), k)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return answer;
    }
    private boolean check(List<int[]> edges, int targetValue, int requiredEdges, int[] parent, int k) {
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            // If even doubling weight can't reach required stability
            if (w * 2 < targetValue)
                return false;
            int parentU = find(parent, u);
            int parentV = find(parent, v);
            if (parentU == parentV)
                continue;
            // If weight is less than required value
            if (w < targetValue) {
                // Use one modification
                if (k > 0) {
                    k--;
                } else {
                    return false;
                }
            }
            // Union
            parent[parentU] = parentV;
            requiredEdges--;
            if (requiredEdges == 0)
                return true;
        }
        return false;
    }
    // DSU find with path compression
    private int find(int[] parent, int node) {
        if (parent[node] == node)
            return node;
        return parent[node] = find(parent, parent[node]);
    }
}

// Time Complexity :- O(E log E + E log W). 
// Space Complexity :- O(E + N).
