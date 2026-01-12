/*********************************************** JAVA **************************************************/

Optimal Solution - The minimum time between two points is the maximum of horizontal and vertical distances, since diagonal moves reduce both coordinates simultaneously.


class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int n = points.length;
        int steps = 0;
        // Traverse consecutive points
        for (int i = 0; i < n - 1; i++) {
            // Coordinates of current point
            int x1 = points[i][0];
            int y1 = points[i][1];
            // Coordinates of next point
            int x2 = points[i + 1][0];
            int y2 = points[i + 1][1];
            // Horizontal and vertical distances
            int dx = Math.abs(x2 - x1);
            int dy = Math.abs(y2 - y1);
            /*
             * Since we can move diagonally, vertically, or horizontally:
             * - Diagonal moves cover min(dx, dy)
             * - Remaining distance is covered by straight moves
             */
            steps += Math.min(dx, dy) + Math.abs(dx - dy);
        }
        return steps;
    }
}



Time Complexity :- O(N).
Space Complexity :- O(1).
