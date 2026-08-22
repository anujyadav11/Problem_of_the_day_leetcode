/*********************************************** JAVA **************************************************/

// Optimal Solution - Rotates each concentric grid layer independently by extracting clockwise perimeter elements, rotating the list by k positions, and writing back in order.
/* "Collections.rotate(list, -k) rotates left by k — negative means left in Java's convention. Always normalize k with % len before rotating to avoid redundant full rotations. 
    The corner-skipping in side traversals prevents double-counting — top-right, bottom-right, and bottom-left corners are included in row traversals not column traversals." */

class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        // number of concentric layers to rotate
        int layers = Math.min(m, n) / 2;
        for (int layer = 0; layer < layers; layer++) {
            List<Integer> nums = new ArrayList<>();
            int top = layer;
            int bottom = m - layer - 1;
            int left = layer;
            int right = n - layer - 1;
            // extract layer elements clockwise: top row left to right
            for (int j = left; j <= right; j++)
                nums.add(grid[top][j]);
            // right column top to bottom (skip top-right corner)
            for (int i = top + 1; i <= bottom - 1; i++)
                nums.add(grid[i][right]);
            // bottom row right to left (skip bottom-right corner)
            for (int j = right; j >= left; j--)
                nums.add(grid[bottom][j]);
            // left column bottom to top (skip corners)
            for (int i = bottom - 1; i >= top + 1; i--)
                nums.add(grid[i][left]);
            // normalize k and rotate left by k positions
            int len = nums.size();
            int normalizedK = k % len;
            Collections.rotate(nums, -normalizedK);
            // write rotated elements back in same clockwise order
            int idx = 0;
            for (int j = left; j <= right; j++)
                grid[top][j] = nums.get(idx++);
            for (int i = top + 1; i <= bottom - 1; i++)
                grid[i][right] = nums.get(idx++);
            for (int j = right; j >= left; j--)
                grid[bottom][j] = nums.get(idx++);
            for (int i = bottom - 1; i >= top + 1; i--)
                grid[i][left] = nums.get(idx++);
        }
        return grid;
    }
}

// Time Complexity :- O(m * n).
// Space Complexity :- O(m + n).
