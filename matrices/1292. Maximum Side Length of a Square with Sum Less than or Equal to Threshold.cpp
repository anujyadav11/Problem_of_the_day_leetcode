/*********************************************** JAVA **************************************************/

Optimal Solution - Use a 2D prefix sum to efficiently check square submatrix sums and expand the largest valid square under a threshold.

class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int r = mat.length, c = mat[0].length;
        // Prefix sum matrix with extra row and column
        int[][] pre = new int[r + 1][c + 1];
        // Build 2D prefix sum
        // pre[i][j] stores sum of submatrix (0,0) to (i-1,j-1)
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                pre[i][j] =
                        pre[i - 1][j] +
                        pre[i][j - 1] -
                        pre[i - 1][j - 1] +
                        mat[i - 1][j - 1];
            }
        }
        int res = 0; // maximum side length found
        int l = 0;   // current side length being tested
        // Try all top-left corners
        for (int i = 0; i <= r; i++) {
            for (int j = 0; j <= c; j++) {
                // Expand square while it stays within bounds and threshold
                while (i + l <= r && j + l <= c &&
                       (pre[i + l][j + l]
                       - pre[i + l][j]
                       - pre[i][j + l]
                       + pre[i][j]) <= threshold) {
                    res = l; // update result
                    l++;     // try bigger square
                }
            }
        }
        return res;
    }
}

Time Complexity :- O(R × C × min(R, C)).
Space Complexity :- O(R × C).
