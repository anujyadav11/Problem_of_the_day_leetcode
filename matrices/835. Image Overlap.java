/*********************************************** JAVA **************************************************/

// Optimal Solution - Solves Image Overlap using brute-force translation, checking every possible shift and counting overlapping 1s in O(n⁴) time.
/* “I consider every possible translation of the first image relative to the second. For each row and column shift, I map every cell (i, j) 
    in the first image to (i+r, j+c) in the second image. If both cells contain 1, I increment the overlap count. Finally, I return the maximum overlap across all translations.” */

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        // Maximum overlap found so far
        int max = 0;
        // Try every possible row shift.
        // Shift ranges from -(n - 1) to +(n - 1).
        for (int r = -n + 1; r < n; r++) {
            // Try every possible column shift.
            for (int c = -n + 1; c < n; c++) {
                int overlap = 0;
                // Check every cell in img1
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        // Find the corresponding position
                        // in img2 after applying the shift.
                        int x = i + r;
                        int y = j + c;
                        // Make sure the shifted position
                        // is still inside img2.
                        if (x >= 0 && x < n &&
                            y >= 0 && y < n &&
                            // Both positions must contain 1
                            img1[i][j] == 1 &&
                            img2[x][y] == 1) {
                            overlap++;
                        }
                    }
                }
                // Keep the maximum overlap
                max = Math.max(max, overlap);
            }
        }
        return max;
    }
}

// Time Complexity :- O(n^4).
// Space Complexity :- O(1).
