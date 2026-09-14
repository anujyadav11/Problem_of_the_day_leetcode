/*********************************************** JAVA **************************************************/

// Optimal Solution - Checks rectangle overlap by verifying intersection on both the horizontal and vertical axes in O(1) time.
/* “Two axis-aligned rectangles overlap with positive area only if their projections overlap on both the x-axis and y-axis. 
    I check the four boundary conditions for horizontal and vertical overlap. If all four are true, the rectangles overlap.” */

class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // rec1[0] = left
        // rec1[1] = bottom
        // rec1[2] = right
        // rec1[3] = top
        // rec2 follows the same format.
        return rec1[0] < rec2[2]   // rec1 left < rec2 right
            && rec1[1] < rec2[3]   // rec1 bottom < rec2 top
            && rec2[0] < rec1[2]   // rec2 left < rec1 right
            && rec2[1] < rec1[3];  // rec2 bottom < rec1 top
    }
}

// Time Complexity :- O(1).
// Space Complexity :- O(1).
