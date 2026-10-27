/*********************************************** JAVA **************************************************/

// Optimal Solution - Generated a patterned string grid using String.repeat() and reusable row construction in O(m × n) time.
/* “I construct the first row using n dots, then build a reusable row containing n - 1 hashes followed by a dot. 
    I assign that row to every remaining position, avoiding repeated construction of identical strings.” */

class Solution {
    public String[] createGrid(int m, int n) {
        // Create an array to store m rows.
        String[] grid = new String[m];
        // The first row contains only empty cells.
        grid[0] = ".".repeat(n);
        // Remaining rows contain '#' in the first n - 1 columns
        // and '.' in the last column.
        String row = "#".repeat(n - 1) + ".";
        // Fill the remaining rows with the same pattern.
        for (int i = 1; i < m; i++) {
            grid[i] = row;
        }
        return grid;
    }
}

// Time Complexity :- O(m * n).
// Space Complexity :- O(m * n).
