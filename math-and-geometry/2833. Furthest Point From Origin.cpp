/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds furthest position from origin by computing net L/R displacement and adding all wildcard moves in the dominant direction.
/* "The key insight is that wildcards should always extend in whichever direction already has the advantage — left or right. So optimal distance is simply |left - right| + dashes. 
    If left == right, all dashes can go either way giving just dash distance. No simulation needed — pure math in O(n)." */

class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int left = 0, right = 0, dash = 0;
        // count each move type
        for (char ch : moves.toCharArray()) {
            if (ch == 'L')
                left++;
            else if (ch == 'R')
                right++;
            else
                dash++;
        }
        // net displacement + all wildcards added in dominant direction
        return Math.abs(left - right) + dash;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
