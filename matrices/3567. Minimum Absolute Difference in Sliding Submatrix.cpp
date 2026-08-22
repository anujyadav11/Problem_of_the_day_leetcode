/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds the minimum absolute difference in every k×k subgrid by leveraging a TreeSet for automatic sorting and deduplication.
/* "The key insight is that minimum absolute difference always occurs between adjacent elements in sorted order — so I don't need to check all pairs. 
    TreeSet gives sorted-distinct values in O(k² log k) per window, reducing an O(k⁴) brute force down significantly." */

class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length; // total rows
        int n = grid[0].length; // total cols
        // output size shrinks by k-1 each dim
        int[][] result = new int[m - k + 1][n - k + 1]; 
        for (int i = 0; i <= m - k; i++) { // top-left row of each k×k window
            for (int j = 0; j <= n - k; j++) { // top-left col of each k×k window
                // sorted + auto-deduped values
                TreeSet<Integer> vals = new TreeSet<>(); 
                // traverse k rows of window
                for (int r = i; r <= i + k - 1; r++) 
                    // traverse k cols of window
                    for (int c = j; c <= j + k - 1; c++) 
                        // insert element (duplicates ignored)
                        vals.add(grid[r][c]); 
                if (vals.size() == 1)
                    continue; // all same → min diff is 0, default int value
                // track minimum adjacent difference
                int minDiff = Integer.MAX_VALUE; 
                Integer prev = null; // previous element in sorted order
                for (int val : vals) {
                    if (prev != null)
                        // diff always +ve since TreeSet is sorted
                        minDiff = Math.min(minDiff, val - prev); 
                    prev = val; // slide prev forward
                }
                result[i][j] = minDiff; // store result for this window
            }
        }
        return result; // return completed result matrix
    }
}

// Time Complexity :- O(m × n × k^2 × log k).
// Space Complexity :- O(k^2).
