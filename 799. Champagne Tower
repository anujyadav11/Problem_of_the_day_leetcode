/*********************************************** JAVA **************************************************/

Optimal Solution - Bottom-up DP simulation of champagne overflow in a triangular tower.
                   Simulate overflow row-by-row since each glass only affects the two directly below it.

class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        // Create a 2D array to simulate the champagne tower
        // We only need rows up to query_row
        double[][] tower = new double[query_row + 1][query_row + 1];
        // Pour all champagne into the top glass
        tower[0][0] = (double) poured;
        // Simulate flow row by row
        for (int row = 0; row < query_row; row++) {
            // Each row has 'row + 1' glasses
            for (int glass = 0; glass <= row; glass++) {
                // Calculate overflow (excess above 1 cup)
                double excess = (tower[row][glass] - 1.0) / 2.0;
                // Only overflow if current glass exceeds capacity
                if (excess > 0) {
                    // Distribute equally to two glasses below
                    tower[row + 1][glass] += excess;
                    tower[row + 1][glass + 1] += excess;
                }
            }
        }
        // A glass can hold at most 1 cup
        return Math.min(1.0, tower[query_row][query_glass]);
    }
}

Time Complexity :- O(r²).
Space Complexity :- O(r²).
