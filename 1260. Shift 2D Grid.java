/*********************************************** JAVA **************************************************/

// Optimal Solution - Shifts grid by k positions using circular flattened index mapping — reading from offset (total-k) with modulo wraparound to fill the result grid.
/* "Flattening 2D to 1D and using modulo arithmetic is cleaner than simulating actual shifts. The key formula is source = (total - k + dest) % total — 
    shifting right by k means each destination position x came from total - k + x positions earlier in the flat array. Always normalize k with k %= total to handle k > total efficiently." */

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int row = grid.length;
        int col = grid[0].length;
        int total = row * col;
        // normalize k to avoid full rotations
        k %= total;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < row; i++)
            res.add(new ArrayList<>());
        // start reading from position (total - k) in flattened grid
        int begin = total - k;
        for (int x = 0; x < total; x++) {
            // map flattened index to 2D source position with wraparound
            int flatIdx = (begin + x) % total;
            int r = flatIdx / col;
            int c = flatIdx % col;
            res.get(x / col).add(grid[r][c]);
        }
        return res;
    }
}

// Time Complexity :- O().
// Space Complexity :- O().
