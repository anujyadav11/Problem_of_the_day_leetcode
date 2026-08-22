/*********************************************** JAVA **************************************************/

// Optimal Solution - Track the earliest completion times of both ride types and evaluate both possible execution orders.
/* “Since only one ride from each category must be completed, I first compute the earliest finishing ride in each category and then evaluate both possible orders: L→W and W→L.” */
 
class Solution {
    public int earliestFinishTime(int[] startL,int[] durL,int[] startW,int[] durW) {
        // Earliest finish time among all L rides
        int earliestLFinish = 3000;
        // Earliest finish time among all W rides
        int earliestWFinish = 3000;
        // Final answer
        int earliestFinish = 3000;
        int n = startL.length;
        int m = startW.length;
        // Find the earliest completion time of any L ride
        for (int i = 0; i < n; i++) {
            earliestLFinish = Math.min(earliestLFinish,startL[i] + durL[i]);
        }
        // Find the earliest completion time of any W ride
        // Also try doing L first, then W
        for (int i = 0; i < m; i++) {
            earliestWFinish = Math.min(earliestWFinish,startW[i] + durW[i]);
            earliestFinish = Math.min(earliestFinish,Math.max(earliestLFinish, startW[i]) + durW[i]);
        }
        // Try W -> L
        for (int i = 0; i < n; i++) {
            earliestFinish = Math.min(earliestFinish,Math.max(earliestWFinish, startL[i]) + durL[i]);
        }
        return earliestFinish;
    }
}

// Time Complexity :- O(n + m).
// Space Complexity :- O(1).
