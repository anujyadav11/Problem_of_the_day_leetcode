/*********************************************** JAVA **************************************************/

Optimal Solution - Use binary search on the y-axis and compute area differences above and below the line to find the height that equally splits the total square area.

class Solution {
    // Binary search to find the horizontal line that equally separates square areas
    public double separateSquares(int[][] squares) {
        double low = 0, high = 0;
        // Determine the upper bound of search (maximum top edge of any square)
        for (int[] sq : squares) {
            high = Math.max(high, (double) sq[1] + sq[2]);
        }
        double eps = 1e-6; // Precision for binary search
        // Binary search on y-coordinate
        while (high - low > eps) {
            double mid = (high + low) / 2.0;
            // Calculate area difference above and below mid
            double diff = check(mid, squares);
            if (diff > 0) {
                // More area above → move line upward
                low = mid;
            } else {
                // More area below → move line downward
                high = mid;
            }
        }
        // low converges to the separating y-coordinate
        return low;
    }
    // Helper function to compute (area above - area below) the horizontal line at y = mid
    private double check(double mid, int[][] squares) {
        double above = 0, below = 0;
        for (int[] sq : squares) {
            double y = sq[1];       // bottom y-coordinate
            double l = sq[2];       // side length
            double area = l * l;
            double bottom = y;
            double top = y + l;
            if (top < mid) {
                // Square lies completely below the line
                below += area;
            } else if (bottom > mid) {
                // Square lies completely above the line
                above += area;
            } else {
                // Square intersects the line → split area proportionally
                double aboveHeight = top - mid;
                double belowHeight = mid - bottom;
                above += aboveHeight * l;
                below += belowHeight * l;
            }
        }
        return above - below;
    }
}

Time Complexity :- O().
Space Complexity :- O().
