/*********************************************** JAVA **************************************************/

Optimal Solution - Combines Floyd–Warshall and dynamic programming to compute the minimum cost of transforming one string into another via substring replacements.
                   Precompute cheapest substring transformations using all-pairs shortest paths, then apply DP over string indices to choose optimal transformations incrementally.

class Solution {
    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        // Map each unique string (original & changed) to a unique index
        HashMap<String, Integer> index = new HashMap<>();
        for (String o : original) {
            if (!index.containsKey(o)) {
                index.put(o, index.size());
            }
        }
        for (String c : changed) {
            if (!index.containsKey(c)) {
                index.put(c, index.size());
            }
        }
        // Distance matrix for all-pairs shortest transformation cost
        long[][] dis = new long[index.size()][index.size()];
        // Initialize distances
        for (int i = 0; i < dis.length; i++) {
            Arrays.fill(dis[i], Long.MAX_VALUE);
            dis[i][i] = 0;
        }
        // Fill direct transformation costs (keep minimum if multiple edges exist)
        for (int i = 0; i < cost.length; i++) {
            int u = index.get(original[i]);
            int v = index.get(changed[i]);
            dis[u][v] = Math.min(dis[u][v], (long) cost[i]);
        }
        // Floyd–Warshall to compute minimum cost between all string pairs
        for (int k = 0; k < dis.length; k++) {
            for (int i = 0; i < dis.length; i++) {
                if (dis[i][k] < Long.MAX_VALUE) {
                    for (int j = 0; j < dis.length; j++) {
                        if (dis[k][j] < Long.MAX_VALUE) {
                            dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
                        }
                    }
                }
            }
        }
        // Store all possible substring lengths from original strings
        HashSet<Integer> set = new HashSet<>();
        for (String o : original) {
            set.add(o.length());
        }
        // dp[i] = minimum cost to transform source[0..i-1] to target[0..i-1]
        long[] dp = new long[target.length() + 1];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0L;
        // Dynamic programming over string positions
        for (int i = 0; i < target.length(); i++) {
            // Skip unreachable states
            if (dp[i] == Long.MAX_VALUE) continue;
            // If characters already match, move forward with no cost
            if (target.charAt(i) == source.charAt(i)) {
                dp[i + 1] = Math.min(dp[i + 1], dp[i]);
            }
            // Try all possible substring transformations
            for (int t : set) {
                if (i + t >= dp.length) continue;
                int c1 = index.getOrDefault(source.substring(i, i + t), -1);
                int c2 = index.getOrDefault(target.substring(i, i + t), -1);
                // If both substrings exist and transformation is possible
                if (c1 >= 0 && c2 >= 0 && dis[c1][c2] < Long.MAX_VALUE) {
                    dp[i + t] = Math.min(dp[i + t], dp[i] + dis[c1][c2]);
                }
            }
        }
        // If final state is unreachable, return -1
        return dp[dp.length - 1] == Long.MAX_VALUE ? -1L : dp[dp.length - 1];
    }
}

Time Complexity :- O(K³ + N·L).
Space Complexity :- O(K² + N).
