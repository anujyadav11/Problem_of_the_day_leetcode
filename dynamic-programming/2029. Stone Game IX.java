/*********************************************** JAVA **************************************************/

// Optimal Solution - Determines Stone Game IX winner by classifying stones mod 3 and applying parity-based win conditions for even and odd zero-remainder counts.
/* "The key insight is that stones only matter by their mod-3 remainder — actual values are irrelevant. Type-0 stones are wildcards that let Bob reset the cumulative sum, 
    changing the parity of the game. With even zeros, they cancel out; with odd zeros, Bob gains one reset opportunity. The threshold of 2 comes from the game tree analysis of optimal play." */

class Solution {
    public boolean stoneGameIX(int[] stones) {
        int cnt0 = 0, cnt1 = 0, cnt2 = 0;
        // classify stones by remainder mod 3
        for (int val : stones) {
            int type = val % 3;
            if (type == 0)
                cnt0++;
            else if (type == 1)
                cnt1++;
            else
                cnt2++;
        }
        // even zeros: Alice wins iff both type-1 and type-2 exist
        if (cnt0 % 2 == 0)
            return cnt1 >= 1 && cnt2 >= 1;
        // odd zeros: Alice wins iff counts differ by more than 2
        return Math.abs(cnt1 - cnt2) > 2;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
