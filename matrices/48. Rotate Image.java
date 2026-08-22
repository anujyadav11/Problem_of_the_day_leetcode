/*********************************************** JAVA **************************************************/

// Optimal Solution - Rotates matrix 90° clockwise in-place by first transposing across the main diagonal then reversing each row.
/* "Transpose + reverse is the standard in-place 90° clockwise rotation. For counter-clockwise, reverse each row first then transpose — 
    or transpose then reverse each column. For 180°, reverse the entire matrix row-by-row then reverse column-by-column. All variants are O(n²) time O(1) space." */

class Solution {
    public void rotate(int[][] matrix) {
        // Size of the square matrix
        int n = matrix.length;
        // Step 1: Transpose the matrix (swap rows with columns)
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Swap matrix[i][j] with matrix[j][i]
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // Step 2: Reverse each row to complete 90° clockwise rotation
        for (int i = 0; i < n; i++) {
            int left = 0, right = n - 1;
            // Reverse elements in the current row
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }
}


// Time Complexity :- O(n^2).
// Space Complexity :- O(1).
