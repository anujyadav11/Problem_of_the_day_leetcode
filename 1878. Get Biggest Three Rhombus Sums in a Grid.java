/*********************************************** JAVA **************************************************/

// Optimal Solution - Enumerate all rhombus borders in the grid and track the three largest distinct border sums.

class Solution {
    public int[] getBiggestThree(int[][] grid) {
        // Maximum possible rhombus side length
        int maxLength = Math.min(grid.length, grid[0].length);
        // Array to store top 3 distinct values
        int[] maxThree = {0, 0, 0};
        // Try every possible rhombus size
        for (int length = 0; length < maxLength; length++) {
            searchBigThree(grid, maxThree, length);
        }
        // Sort to arrange values
        Arrays.sort(maxThree);
        // If less than 3 distinct values exist
        if (maxThree[0] == 0) {
            if (maxThree[1] == 0) {
                return new int[]{maxThree[2]};
            }
            return new int[]{maxThree[2], maxThree[1]};
        }
        // Reverse array to descending order using XOR swap
        maxThree[0] = maxThree[0] ^ maxThree[2];
        maxThree[2] = maxThree[0] ^ maxThree[2];
        maxThree[0] = maxThree[0] ^ maxThree[2];
        return maxThree;
    }
    void searchBigThree(int[][] grid, int[] maxThree, int length) {
        // Boundary limits for rhombus top vertex
        int rowLimit = grid.length - (length == 0 ? 0 : 2 * length);
        int colLimit = grid[0].length - length;
        for (int row = 0; row < rowLimit; row++) {
            for (int col = length; col < colLimit; col++) {
                if (row + col >= length) {
                    // Compute rhombus border sum
                    addToMaxThree(maxThree, getSum(grid, row, col, length));
                }
            }
        }
    }
    int getSum(int[][] grid, int i, int j, int length) {
        // Single cell rhombus
        if (length == 0) {
            return grid[i][j];
        }
        int sum = 0;
        // Edge AB (down-right)
        for (int step = 0; step <= length; step++) {
            sum += grid[i + step][j + step];
        }
        // Edge AD (down-left)
        for (int step = 1; step <= length; step++) {
            sum += grid[i + step][j - step];
        }
        // Edge DC (down-right)
        for (int step = 1; step <= length; step++) {
            sum += grid[i + length + step][j - length + step];
        }
        // Edge BC (down-left)
        for (int step = 1; step < length; step++) {
            sum += grid[i + length + step][j + length - step];
        }
        return sum;
    }
    void addToMaxThree(int[] maxThree, int num) {
        // Skip duplicates
        if (maxThree[0] == num || maxThree[1] == num || maxThree[2] == num) {
            return;
        }
        // Keep the three largest values
        Arrays.sort(maxThree);
        if (maxThree[0] < num) {
            maxThree[0] = num;
        }
    }
}

// Time Complexity :- O(R×C×L2).
// Space Complexity :- O(1).
