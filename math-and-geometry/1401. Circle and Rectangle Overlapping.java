/*********************************************** JAVA **************************************************/

// Optimal Solution - Detects circle-rectangle overlap by clamping the circle center to the rectangle and comparing the closest-point distance with the radius.
/* “Instead of checking the rectangle’s corners and edges separately, I find the point on the rectangle that is closest to the circle’s center. 
    I clamp the x-coordinate between the rectangle’s left and right boundaries and the y-coordinate between its bottom and top boundaries. 
    Then I calculate the squared distance between this closest point and the circle center. If that distance is less than or equal to the squared radius, the circle and rectangle overlap.” */

class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter,
                                int x1, int y1, int x2, int y2) {

        // Find the closest point on the rectangle to the circle center
        int closestX = Math.max(x1, Math.min(xCenter, x2));
        int closestY = Math.max(y1, Math.min(yCenter, y2));
        // Check whether that point lies inside the circle
        return sqDist(xCenter, yCenter, closestX, closestY)
                <= radius * radius;
    }
    int sqDist(int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        return dx * dx + dy * dy;
    }
}

// Time Complexity :- O(1).
// Space Complexity :- O(1).
