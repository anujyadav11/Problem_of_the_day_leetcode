/*********************************************** JAVA **************************************************/

// Optimal Solution - Convert rows into histograms and sort each row to compute the largest possible submatrix area.
/* "I treat each row as a histogram of consecutive 1s. Since columns can be rearranged, I sort each row and compute the maximum area by considering different widths." */

class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int maxArea = 0;
        // Step 1: Build height histogram for each column
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                // If current cell is 1, accumulate height from above
                if (matrix[i][j] == 1)
                    matrix[i][j] = matrix[i - 1][j] + 1;
            }
        }
        // Step 2: For each row, treat it as histogram
        for (int i = 0; i < m; ++i) {
            // Sort row to rearrange columns optimally
            Arrays.sort(matrix[i]);
            // Try all possible widths
            for (int width = 1; width <= n; ++width) {
                // Height = element from right side (largest heights)
                int height = matrix[i][n - width];
                // Area = width * height
                maxArea = Math.max(maxArea, width * height);
            }
        }
        return maxArea;
    }
}

// Time Complexity :- O(m * n log n).
// Space Complexity :- O(1).
