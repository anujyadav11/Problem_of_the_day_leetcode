/*********************************************** JAVA **************************************************/

Optimal Solution - Maximize square hole area by finding the longest consecutive removed horizontal and vertical bars after sorting.

class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        // Sort the horizontal and vertical removed bars
        Arrays.sort(hBars);
        Arrays.sort(vBars);
        // Find the maximum consecutive streak of removed bars
        int hMax = findMax(hBars);
        int vMax = findMax(vBars);
        // The side of the largest square is limited by the smaller streak
        int side = Math.min(hMax, vMax) + 1;
        // Return area of the square
        return side * side;
    }
    /*
     * Finds the longest consecutive sequence in a sorted array
     * Example: [2,3,4,7] -> longest streak = 3
     */
    public int findMax(int[] bars) {
        int streak = 1;      // current consecutive count
        int maxStreak = 1;   // maximum consecutive count found so far
        for (int i = 1; i < bars.length; i++) {
            if (bars[i] == bars[i - 1] + 1) {
                streak++; // extend consecutive sequence
            } else {
                maxStreak = Math.max(maxStreak, streak);
                streak = 1; // reset streak
            }
        }
        return Math.max(maxStreak, streak);
    }
}


Time Complexity :- O(h log h + v log v).
Space Complexity :- O(1).
