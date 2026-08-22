/*********************************************** JAVA **************************************************/

// Optimal Solution - Constructs a product matrix where each cell holds the product of all other elements mod 12345, using an in-place prefix-suffix two-pass approach.
/* "This is the 2D extension of the classic 'Product of Array Except Self' problem. The trick is reusing the result matrix as the suffix array in the first pass — saving O(n×m) extra space. 
  Always use long for intermediate products before taking mod to prevent overflow." */

class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int MOD = 12345;
        // result matrix storing product of all elements except current
        int[][] p = new int[n][m];
        long suffix = 1;
        // suffix pass: p[i][j] stores product of all elements after (i,j)
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                // cast long suffix to int before storing
                p[i][j] = (int) suffix;
                // update suffix with current element under MOD
                suffix = (suffix * grid[i][j]) % MOD;
            }
        }
        long prefix = 1;
        // prefix pass: multiply stored suffix with running prefix product
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // combine prefix and suffix products at each cell
                p[i][j] = (int) ((prefix * p[i][j]) % MOD);
                // update prefix with current element under MOD
                prefix = (prefix * grid[i][j]) % MOD;
            }
        }
        // return completed product matrix
        return p;
    }
}

// Time Complexity :- O(n * m).
// Space Complexity :- O(n * m).
