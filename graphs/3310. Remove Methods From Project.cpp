/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds the remaining methods after optionally removing the suspicious group reachable from k, checking whether any external method invokes the suspicious group before removal.
/* "The key insight is checking external invocations — if any non-suspicious method calls into the suspicious group, removing it would break the system. 
    Scan all edges for !sus[src] && sus[dst] after BFS marks the reachable set. This is cleaner and correct compared to tracking in-degrees, which get corrupted during BFS traversal." */

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // build directed adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<>());
        // track in-degree to detect external calls into suspicious group
        int[] inDegree = new int[n];
        for (int[] edge : invocations) {
            adj.get(edge[0]).add(edge[1]);
            inDegree[edge[1]]++;
        }
        // BFS from k to find all suspicious methods reachable from k
        boolean[] sus = new boolean[n];
        Queue<Integer> que = new LinkedList<>();
        que.offer(k);
        sus[k] = true;
        while (!que.isEmpty()) {
            int curr = que.poll();
            for (int ngr : adj.get(curr)) {
                if (!sus[ngr]) {
                    que.offer(ngr);
                    sus[ngr] = true;
                }
            }
        }
        // check if any suspicious method is called from a non-suspicious method
        boolean cannotRemove = false;
        for (int[] edge : invocations) {
            // non-suspicious calls suspicious — can't safely remove the group
            if (!sus[edge[0]] && sus[edge[1]]) {
                cannotRemove = true;
                break;
            }
        }
        // return all methods if removal unsafe, otherwise return non-suspicious ones
        if (cannotRemove) {
            List<Integer> all = new ArrayList<>();
            for (int i = 0; i < n; i++) all.add(i);
            return all;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (!sus[i]) res.add(i);
        return res;
    }
}

// Time Complexity :- O(n + e) — BFS over all nodes and edges plus edge scan.
// Space Complexity :-O(n + e) — adjacency list and visited array.
