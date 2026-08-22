/*********************************************** JAVA **************************************************/

// Optimal Solution - Count row and column frequencies of 1s and identify cells that are the only 1 in both their row and column.
                    // “I first count the number of 1s in every row and column, then a position is special if it contains 1 and both counts equal 1.”

class Solution {
    public int numSpecial(int[][] mat) {
        int n = mat.length;       // Number of rows
        int m = mat[0].length;    // Number of columns
        // Arrays to count number of 1s in each row and column
        int[] rowCnt = new int[n];
        int[] colCnt = new int[m];
        // First pass: count number of 1s in each row and column
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {       
                if (mat[i][j] == 1) {
                    rowCnt[i]++;   // Increment row count
                    colCnt[j]++;   // Increment column count
                }
            }
        }
        int res = 0;  // Result: number of special positions
        // Second pass: check if the 1 is the only one in its row and column
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // Skip if cell is 0
                if (mat[i][j] == 0)
                    continue;
                // Special position condition
                if (rowCnt[i] == 1 && colCnt[j] == 1) {
                    res++;
                }
            }
        }
        return res;
    }
}

// Time Complexity :- O(n * m).
// Space Complexity :- O(n + m).
