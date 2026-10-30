/*********************************************** JAVA **************************************************/

// Optimal Solution - Solved a constrained shortest-path problem using Dijkstra's algorithm with state expansion, tracking consecutive label counts in O((V*K + E*K) log(V*K)) time.
/* “I use Dijkstra with an expanded state because reaching a node with different consecutive-label counts affects which transitions are valid next. 
    Therefore, I maintain the minimum distance for each (node, consecutiveCount) state rather than just each node. The priority queue processes states in increasing distance, 
    and transitions exceeding the allowed consecutive count are rejected.” */

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // No valid path if even one consecutive node is not allowed.
        if (n == 0 || k == 0) {
            return -1;
        }
        // Build directed adjacency list: {neighbor, weight}.
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            adj.computeIfAbsent(u, x -> new ArrayList<>()).add(new int[]{v, wt});
        }
        // dist[node][count] = minimum cost to reach node
        // with 'count' consecutive nodes having the same label.
        long[][] dist = new long[n][k + 1];
        for (long[] row : dist) {
            Arrays.fill(row, Long.MAX_VALUE);
        }
        // Priority queue stores {distance, node, consecutiveCount}.
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        // Start at node 0 with one consecutive occurrence.
        dist[0][1] = 0;
        pq.offer(new long[]{0, 0, 1});
        while (!pq.isEmpty()) {
            long[] current = pq.poll();
            long d = current[0];
            int node = (int) current[1];
            int count = (int) current[2];
            // Ignore outdated priority queue entries.
            if (d != dist[node][count]) {
                continue;
            }
            // First time reaching the destination is optimal.
            if (node == n - 1) {
                return d > Integer.MAX_VALUE ? -1 : (int) d;
            }
            // Explore neighboring nodes.
            for (int[] edge : adj.getOrDefault(node, Collections.emptyList())) {
                int next = edge[0];
                int weight = edge[1];
                // Update consecutive label count.
                int newCount = (labels.charAt(node) == labels.charAt(next)) ? count + 1: 1;
                // Reject paths exceeding the consecutive limit.
                if (newCount > k) {
                    continue;
                }
                long newDist = d + weight;
                // Relax the expanded state.
                if (newDist < dist[next][newCount]) {
                    dist[next][newCount] = newDist;
                    pq.offer(new long[]{newDist,next,newCount});
                }
            }
        }
        // Destination is unreachable.
        return -1;
    }
}

// Time Complexity :- O((V × K + E × K) log(V × K)).
// Space Complexity :- O(V × K + E).
