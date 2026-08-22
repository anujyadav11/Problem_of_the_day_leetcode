/*********************************************** JAVA **************************************************/

Optimal Solution - Uses Floyd–Warshall to compute minimum character transformation costs and sums them to convert the source string into the target.
                   Since characters are limited to 26, Floyd–Warshall gives an optimal all-pairs conversion cost in constant time.

class Solution {
    public long minimumCost(
        String source,
        String target,
        char[] original,
        char[] changed,
        int[] cost
    ) {
        // dis[i][j] = minimum cost to convert character i to character j
        int[][] dis = new int[26][26];
        // Initialize distances
        for (int i = 0; i < 26; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE);
            dis[i][i] = 0; // cost to convert a character to itself is 0
        }
        // Fill direct conversion costs
        for (int i = 0; i < cost.length; i++) {
            int start = original[i] - 'a';
            int end = changed[i] - 'a';
            // Keep minimum cost if multiple conversions exist
            dis[start][end] = Math.min(dis[start][end], cost[i]);
        }
        // Floyd–Warshall: find all-pairs shortest conversion costs
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                if (dis[i][k] < Integer.MAX_VALUE) {
                    for (int j = 0; j < 26; j++) {
                        if (dis[k][j] < Integer.MAX_VALUE) {
                            dis[i][j] = Math.min(
                                dis[i][j],
                                dis[i][k] + dis[k][j]
                            );
                        }
                    }
                }
            }
        }
        long ans = 0L;
        // Compute total cost for converting source -> target
        for (int i = 0; i < source.length(); i++) {
            int c1 = source.charAt(i) - 'a';
            int c2 = target.charAt(i) - 'a';
            // If conversion is impossible
            if (dis[c1][c2] == Integer.MAX_VALUE) {
                return -1L;
            }
            ans += (long) dis[c1][c2];
        }
        return ans;
    }
}

Time Complexity :- O(n). because we loop 26 letters.
Space Complexity :- O(1).
