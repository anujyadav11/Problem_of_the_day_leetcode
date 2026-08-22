/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds earliest collaborative finish time by trying both orderings — each person waiting for the other's earliest completion before starting their own optimal task.
/*  "Always initialize independent variables to their own sentinel values — minW = minL is a subtle initialization bug that copies a computed value instead of starting fresh. 
      The greedy insight is that the second person should start their fastest task immediately after the first finishes — max(firstFinish, taskStart) + duration captures both waiting and immediate start cases." */

class Solution {
    public int earliestFinishTime(int[] startL, int[] durL, int[] startW, int[] durW) {
        int INF = 300000;
        int n = startL.length;
        int m = startW.length;
        // minL = earliest time aman can finish any single task
        int minL = INF;
        for (int i = 0; i < n; i++)
            minL = Math.min(minL, startL[i] + durL[i]);
        // minW = earliest time anuj can finish any single task
        int minW = INF;
        int res = INF;
        for (int i = 0; i < m; i++) {
            minW = Math.min(minW, startW[i] + durW[i]);
            // anuj finishes task i, aman finishes after — res = max(minL, startW[i]) + durW[i]
            res = Math.min(res, Math.max(minL, startW[i]) + durW[i]);
        }
        // aman finishes task i, finishes after
        for (int i = 0; i < n; i++)
            res = Math.min(res, Math.max(minW, startL[i]) + durL[i]);
        return res;
    }
}

// Time Complexity :- O(m + n).
// Space Complexity :- O(1).
