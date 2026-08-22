/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds maximum distance between differently colored houses by anchoring on both endpoints and scanning for the farthest house with a differing color.
/* "The O(n²) brute force checks all pairs — the key insight is that the optimal pair always includes the first or last house as one endpoint. 
    Any two interior houses with maximum distance could be extended further by replacing one with an endpoint. This reduces the search to two linear scans — one anchored at index 0, one at index n-1." */
 
class Solution {
    public int maxDistance(int[] colors) {
        int n = colors.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            // check distance from first house — valid only if colors differ
            if (colors[i] != colors[0])
                res = Math.max(res, i);
            // check distance from last house — valid only if colors differ
            if (colors[i] != colors[n - 1])
                res = Math.max(res, (n - 1) - i);
        }
        return res;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
