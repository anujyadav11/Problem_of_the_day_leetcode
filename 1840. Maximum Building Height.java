/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds maximum building height using forward-backward constraint propagation on sorted restriction points, computing geometric peaks between consecutive pairs.
/*  "The two-pass propagation ensures each point's height respects both left and right constraints. The peak formula (gap - |h1-h2|)/2 + max(h1,h2) comes from visualizing the tallest triangle fitting between two endpoints 
      — the peak is equidistant from both sides adjusted for height difference. Always add building n explicitly if not in restrictions." */

class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        Arrays.sort(restrictions, (a, b) -> Integer.compare(a[0], b[0]));
        int len = restrictions.length;
        // no restrictions — max height at last building = n-1
        if (len == 0) return n - 1;
        // check if last building n is already in restrictions
        boolean isLast = restrictions[len - 1][0] == n;
        int m = len + 1 + (isLast ? 0 : 1);
        int[][] h = new int[m][2];
        // first building always has height 0
        h[0][0] = 1;
        h[0][1] = 0;
        // forward pass — propagate max heights left to right
        for (int i = 0; i < len; i++) {
            int diff = restrictions[i][0] - h[i][0];
            h[i + 1][0] = restrictions[i][0];
            h[i + 1][1] = Math.min(h[i][1] + diff, restrictions[i][1]);
        }
        // add building n if not in restrictions
        if (!isLast) {
            int diff = n - h[len][0];
            h[len + 1][0] = n;
            h[len + 1][1] = Math.min(h[len][1] + diff, n - 1);
        }
        // backward pass — propagate constraints right to left
        for (int i = m - 2; i >= 0; i--) {
            int diff = h[i + 1][0] - h[i][0];
            h[i][1] = Math.min(h[i][1], h[i + 1][1] + diff);
        }
        // find max height achievable between each consecutive pair
        int res = 0;
        for (int i = 1; i < m; i++) {
            int left = h[i - 1][0];
            int right = h[i][0];
            int h1 = h[i - 1][1];
            int h2 = h[i][1];
            // peak between two buildings = (gap - |h1-h2|)/2 + max(h1,h2)
            int peak = (right - left - Math.abs(h1 - h2)) / 2 + Math.max(h1, h2);
            res = Math.max(res, peak);
        }
        return res;
    }
}

// Time Complexity :- O(m log m + m).
// Space Complexity :- O(m).
