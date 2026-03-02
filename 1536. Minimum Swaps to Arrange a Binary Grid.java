/*********************************************** JAVA **************************************************/

// Optimal Solution - Greedily position rows by counting trailing zeros and simulating minimal adjacent swaps.
                    //“Each row must have enough trailing zeros to satisfy its position. I greedily bring the nearest valid row upward using adjacent swaps to minimize total operations.”

class Solution {
    public int minSwaps(int[][] grid) {
        int n = grid.length;   // Grid is n x n
        int[] endZeros = new int[n];
        // endZeros[i] = number of consecutive trailing zeros in row i
        // Step 1: Count trailing zeros for each row
        for (int i = 0; i < n; i++) {
            int j = n - 1;     // Start from last column
            int count = 0;
            // Count consecutive zeros from the end
            while (j >= 0 && grid[i][j] == 0) {
                count++;
                j--;
            }
            endZeros[i] = count;
        }
        int steps = 0;  // Total swaps
        // Step 2: Try to place rows in the correct order
        for (int i = 0; i < n; i++) {   
            int need = n - i - 1;  // Required trailing zeros for row i
            int j = i;
            // Find first row below i that satisfies the requirement
            while (j < n && endZeros[j] < need) {
                j++;
            }
            // If no such row found → impossible
            if (j == n) {
                return -1;
            }
            // Add number of swaps needed to bring row j to i
            steps += (j - i);
            // Bubble row upward (simulate adjacent swaps)
            while (j > i) {
                int temp = endZeros[j];
                endZeros[j] = endZeros[j - 1];
                endZeros[j - 1] = temp;
                j--;
            }
        }
        return steps;
    }
}

// Time Complexity :- O(n ^ 2).
// Space Complexity :- O(n).
