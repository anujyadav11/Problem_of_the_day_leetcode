/*********************************************** JAVA **************************************************/

// Optimal Solution - Rotates a gravity box 90° clockwise by first settling stones rightward row by row, then applying direct clockwise rotation formula to produce the final grid.
/* "Order matters here — gravity must be applied in the original orientation before rotation. After rotating, gravity direction changes so applying it post-rotation gives wrong results. 
    The direct rotation formula res[i][j] = src[m-1-j][i] is cleaner than transpose-then-reverse since it avoids the extra reverseRow step." */

class Solution {
    public char[][] rotateTheBox(char[][] boxGrid) {
        int m = boxGrid.length;
        int n = boxGrid[0].length;
        char[][] res = new char[n][m];
        // step 1: transpose — res[i][j] = boxGrid[j][i]
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                res[i][j] = boxGrid[j][i];
        // step 2: reverse each row — completes 90° clockwise rotation
        for (int i = 0; i < n; i++)
            reverseRow(res[i]);
        // step 3: apply gravity — stones '#' fall downward in rotated grid
        for (int j = 0; j < m; j++) {
            int spaceBottom = n - 1;
            for (int i = n - 1; i >= 0; i--) {
                if (res[i][j] == '*') {
                    // obstacle — reset available space above it
                    spaceBottom = i - 1;
                    continue;
                }
                if (res[i][j] == '#') {
                    // stone — fall to lowest available space
                    res[i][j] = '.';
                    res[spaceBottom][j] = '#';
                    spaceBottom--;
                }
            }
        }
        return res;
    }

    public void reverseRow(char[] row) {
        int left = 0, right = row.length - 1;
        // standard two-pointer row reversal
        while (left < right) {
            char temp = row[left];
            row[left] = row[right];
            row[right] = temp;
            left++;
            right--;
        }
    }
}

// Time Complexity :- O(m * n).
// Space Complexity :- O(m * n).
