/*********************************************** JAVA **************************************************/

// Optimal Solution - Maximises minimum path edge score using binary search on score threshold with Dijkstra verifying reachability within budget k using only qualifying edges.
/* "Binary search on answer + Dijkstra feasibility check is a powerful pattern for 'maximise minimum edge weight on a path' problems. The d > k early return in Dijkstra is key — since it's a min-heap, 
    if the current best distance exceeds budget, no better path exists. Only process online nodes to correctly model the constraint." */

class Solution {
    private boolean check(int mid, int n, long k, Map<Integer, List<int[]>> adj) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        // min heap on distance
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        dist[0] = 0;
        pq.offer(new long[]{0, 0});
        while (!pq.isEmpty()) {
            long[] top = pq.poll();
            long d = top[0];
            int node = (int) top[1];
            // exceeded budget — this path not viable
            if (d > k) return false;
            // reached destination within budget
            if (node == n - 1) return true;
            // stale entry — skip
            if (d > dist[node]) continue;
            for (int[] vec : adj.getOrDefault(node, Collections.emptyList())) {
                int adjNode = vec[0];
                int edgeCost = vec[1];
                // only use edges with cost >= mid (minimum score threshold)
                if (edgeCost < mid) continue;
                long newDist = d + edgeCost;
                if (newDist < dist[adjNode]) {
                    dist[adjNode] = newDist;
                    pq.offer(new long[]{newDist, adjNode});
                }
            }
        }
        return false;
    }

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        Map<Integer, List<int[]>> adj = new HashMap<>();
        int l = Integer.MAX_VALUE, r = 0;
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            // skip edges with offline nodes
            if (!online[u] || !online[v]) continue;
            adj.computeIfAbsent(u, x -> new ArrayList<>()).add(new int[]{v, w});
            l = Math.min(l, w);
            r = Math.max(r, w);
        }
        // binary search on minimum edge weight (score)
        int answer = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (check(mid, n, k, adj)) {
                answer = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return answer;
    }
}

// Time Complexity :- O((E + n) log n × log(maxW)) — Dijkstra O((E+n) log n) run O(log maxW) times.
// Space Complexity :- O(n + E) — adjacency map and distance array.
