/*********************************************** JAVA **************************************************/

// Optimal Solution - Weighted Interval Scheduling + Binary Search + Bounded DP + Lexicographical Tie-Breaking
/* “I treat this as weighted interval scheduling with a maximum of four selected intervals. I first sort the intervals by start time and use binary search to find the first interval whose start is strictly greater than the current interval’s end. 
    Then I use DP where t[i][k] represents the best result from interval i onward when I can still choose at most k intervals. 
    For each state, I either skip the current interval or take it and jump to its next compatible interval. If both choices have the same weight, I select the lexicographically smaller list of original indices.” */

class Solution {
    int n;
    int[][] intervals;
    int[] nextIdx;
    // Stores:
    // score -> maximum total weight
    // idxs  -> original indices producing that score
    static class Node {
        long score = -1;
        List<Integer> idxs = new ArrayList<>();
    }
    // t[i][k] = best result considering intervals from i onward
    // when we can still select at most k intervals
    Node[][] t;
    // Find the first interval whose start time is > r.
    // This interval is compatible with the current interval.
    int findNext(int r) {
        int lo = 0;
        int hi = n - 1;
        int result = n;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (intervals[mid][0] > r) {
                result = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return result;
    }
    // Compare two lists lexicographically.
    // Returns true if a is lexicographically smaller than b.
    boolean isLexSmaller(List<Integer> a, List<Integer> b) {
        int len = Math.min(a.size(), b.size());
        for (int i = 0; i < len; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }
        // If one is a prefix of the other,
        // the shorter list is lexicographically smaller.
        return a.size() < b.size();
    }
    public int[] maximumWeight(List<List<Integer>> intervalsList) {
        n = intervalsList.size();
        // Store:
        // [start, end, weight, originalIndex]
        intervals = new int[n][4];
        for (int i = 0; i < n; i++) {
            intervals[i][0] = intervalsList.get(i).get(0);
            intervals[i][1] = intervalsList.get(i).get(1);
            intervals[i][2] = intervalsList.get(i).get(2);
            // Preserve original index
            intervals[i][3] = i;
        }
        // Sort by:
        // 1. start
        // 2. end
        // 3. weight
        // 4. original index
        Arrays.sort(intervals, (a, b) -> {

            if (a[0] != b[0])
                return a[0] - b[0];

            if (a[1] != b[1])
                return a[1] - b[1];

            if (a[2] != b[2])
                return a[2] - b[2];

            return a[3] - b[3];
        });
        // nextIdx[i] = first interval after i
        // whose start > intervals[i]'s end
        nextIdx = new int[n];
        for (int i = 0; i < n; i++) {
            int r = intervals[i][1];
            nextIdx[i] = findNext(r);
        }
        // We can select at most 4 intervals.
        final int K = 4;
        // t[i][k]:
        // best result from index i onward
        // with at most k intervals remaining
        t = new Node[n + 1][K + 1];
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= K; k++) {
                t[i][k] = new Node();
            }
        }
        // Process intervals from right to left.
        for (int i = n - 1; i >= 0; i--) {
            int weight = intervals[i][2];
            int idx = intervals[i][3];
            // First compatible interval
            int j = nextIdx[i];
            // Try taking exactly one more interval
            for (int k = 1; k <= K; k++) {
                // Option 1: Skip current interval
                Node skip = t[i + 1][k];
                // Option 2: Take current interval
                Node temp = t[j][k - 1];
                Node take = new Node();
                // Add current interval's weight
                take.score = temp.score + weight;
                // Copy selected indices
                take.idxs = new ArrayList<>(temp.idxs);
                // Add current interval's original index
                take.idxs.add(idx);
                // Keep indices sorted because the final
                // answer must be lexicographically compared
                Collections.sort(take.idxs);
                Node result;
                // Prefer the solution with greater weight
                if (skip.score > take.score) {
                    result = skip;
                } else if (skip.score < take.score) {
                    result = take;
                } else {
                    // Same weight -> choose lexicographically
                    // smaller list of original indices
                    result = isLexSmaller(skip.idxs, take.idxs)
                            ? skip
                            : take;
                }
                t[i][k] = result;
            }
        }
        // Best answer using at most 4 intervals
        Node res = t[0][K];
        int[] ans = new int[res.idxs.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = res.idxs.get(i);
        }
        return ans;
    }
}

// Time Complexity :- O(n log n).
// Space Complexity :- O(n * n).
