/*********************************************** JAVA **************************************************/

Optimal Solution - Check every pair of rectangles, compute their overlapping region, and take the largest possible square side as the minimum of overlap width and height.

class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int n = bottomLeft.length;
        int maxSide = 0; // Stores the maximum possible square side length
        // Compare every pair of rectangles
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Compute overlapping width
                int overlapRightX = Math.min(topRight[i][0], topRight[j][0]);
                int overlapLeftX  = Math.max(bottomLeft[i][0], bottomLeft[j][0]);
                int width = overlapRightX - overlapLeftX;
                // Compute overlapping height
                int overlapTopY    = Math.min(topRight[i][1], topRight[j][1]);
                int overlapBottomY = Math.max(bottomLeft[i][1], bottomLeft[j][1]);
                int height = overlapTopY - overlapBottomY;
                // Side of the largest square that can fit in the overlap
                int side = Math.min(width, height);
                // Update maximum side length
                maxSide = Math.max(maxSide, side);
            }
        }
        // Return area of the largest square
        return 1L * maxSide * maxSide;
    }
}

Time Complexity :- O(N^2).
Space Complexity :- O(1).
