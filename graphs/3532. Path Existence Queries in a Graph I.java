/*********************************************** JAVA **************************************************/

// Optimal Solution - Answers path existence queries in O(1) each by pre-assigning component IDs via linear scan, incrementing on gaps exceeding maxDiff.
/* Sorted array + threshold gap = linear connected components — no Union-Find needed. Each component is a contiguous range of indices where consecutive differences stay ≤ maxDiff. 
    This reduces path existence to component equality, answering each query in O(1) after O(n) preprocessing — optimal for large query counts." */

class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // assign component id to each index — new component when gap exceeds maxDiff
        int[] component = new int[n];
        int compId = 0;
        component[0] = compId;
        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] > maxDiff)
                compId++;
            component[i] = compId;
        }
        // two nodes are connected iff they share the same component id
        boolean[] result = new boolean[queries.length];
        for (int j = 0; j < queries.length; j++)
            result[j] = component[queries[j][0]] == component[queries[j][1]];
        return result;
    }
}

// Time Complexity :- O(n + q).
// Space Complexity :- O(n + q).
