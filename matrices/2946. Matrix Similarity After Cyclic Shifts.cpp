/*********************************************** JAVA **************************************************/

// Optimal Solution - Checks if a matrix is similar to its k-shift state by verifying each element matches its rotated position using modular index arithmetic on even/odd rows.
/* "The key is never physically rotating — just check if mat[i][j] equals what would shift into that position. k %= n is critical to handle cases where k > n. 
    The +n in (j-k+n)%n is essential to prevent negative indices in Java since Java's % can return negative values." */

class Solution {
    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        // reduce k to avoid redundant full rotations
        k %= n;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i % 2 == 0) {
                    // even row shifts left by k: element at j must equal element at j+k
                    if (mat[i][j] != mat[i][(j + k) % n])
                        return false;
                } else {
                    // odd row shifts right by k: element at j must equal element at j-k
                    if (mat[i][j] != mat[i][(j - k + n) % n])
                        return false;
                }
            }
        }
        // all rows match their shifted versions — matrix is similar
        return true;
    }
}

// Time Complexity :- O(m*n).
// Space Complexity :- O(1).
