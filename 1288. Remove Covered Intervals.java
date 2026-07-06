/*********************************************** JAVA **************************************************/

// Optimal Solution - Counts non-covered intervals by sorting with start ascending and end descending, tracking maximum end to identify intervals not subsumed by previous ones.
/* "The descending end sort for equal starts is critical — it ensures [1,4] comes before [1,2], so when we see [1,2] its end 2 <= cur=4 correctly identifies it as covered. 
    Without this, [1,2] would be processed first, setting cur=2, then [1,4] would update cur=4 — counting both as uncovered incorrectly." */

class Solution {
    public int removeCoveredIntervals(int[
    ][] intervals) {
        // sort by start ascending, break ties by end descending
        // descending end ensures larger intervals come first for same start
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int cur = 0;
        int count = 0;
        for (int[] interval : intervals) {
            // interval not covered by current maximum end — count it
            if (cur < interval[1]) {
                cur = interval[1];
                count++;
            }
            // else interval[1] <= cur — fully covered, skip
        }
        return count;
    }
}

// Time Complexity :- O(n log n).
// Space Complexity :- O(1).
