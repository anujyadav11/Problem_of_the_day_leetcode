/*********************************************** JAVA **************************************************/

// Optimal Solution - Minimizes two-finger typing distance using bottom-up 3D DP tracking finger positions, with Manhattan distance costs on a 6-column keyboard grid.
/* "Position 26 as sentinel for unplaced finger is elegant — getDistance returns 0 for it, making first placement always free. Bottom-up avoids recursion stack overhead. 
    Space can be optimized to O(27²) by noting dp[i] only depends on dp[i+1] — use two alternating 2D arrays." */

class Solution {
    // convert letter index to keyboard grid coordinate
    public int[] getCoord(int pos) {
        return new int[]{pos / 6, pos % 6};
    }
    public int getDistance(int pos1, int pos2) {
        // position 26 means finger not yet placed — no movement cost
        if (pos1 == 26 || pos2 == 26) return 0;
        int[] c1 = getCoord(pos1);
        int[] c2 = getCoord(pos2);
        // Manhattan distance between two keyboard positions
        return Math.abs(c1[0] - c2[0]) + Math.abs(c1[1] - c2[1]);
    }
    public int minimumDistance(String word) {
        int n = word.length();
        // dp[i][f1][f2] = min cost to type word[i..n-1] with fingers at f1 and f2
        int[][][] dp = new int[n + 1][27][27];
        // fill bottom-up from last character to first
        for (int i = n - 1; i >= 0; i--) {
            int curr = word.charAt(i) - 'A';
            for (int f1 = 0; f1 < 27; f1++) {
                for (int f2 = 0; f2 < 27; f2++) {
                    if (f1 == 26 && f2 == 26) {
                        // neither finger placed yet — place first finger for free
                        dp[i][f1][f2] = dp[i + 1][curr][f2];
                        continue;
                    }
                    if (f2 == 26) {
                        // second finger not placed — either move f1 or place f2 for free
                        int moveF1 = getDistance(f1, curr) + dp[i + 1][curr][f2];
                        int moveF2 = dp[i + 1][f1][curr];
                        dp[i][f1][f2] = Math.min(moveF1, moveF2);
                        continue;
                    }
                    // both fingers placed — choose which finger to move to curr
                    int moveF1 = getDistance(f1, curr) + dp[i + 1][curr][f2];
                    int moveF2 = getDistance(f2, curr) + dp[i + 1][f1][curr];
                    dp[i][f1][f2] = Math.min(moveF1, moveF2);
                }
            }
        }
        // start with both fingers unplaced (position 26)
        return dp[0][26][26];
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n).
