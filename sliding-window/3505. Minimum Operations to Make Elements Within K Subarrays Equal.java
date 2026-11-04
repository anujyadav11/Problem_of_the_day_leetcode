/*********************************************** JAVA - I have not solved this problem i have picked this code from codestorywithMIK  **************************************************/

// Optimal Solution - Solved minimum operations for k non-overlapping subarrays using sliding-window median maintenance with two TreeMaps and memoized take-or-skip DP.
/* "I divide the problem into two stages. First, I calculate the minimum equalization cost for every window of length x. Since the median minimizes the sum of absolute differences, 
    I maintain two balanced TreeMaps to dynamically track the median and the sums on both sides.
    Then I use memoized DP to select exactly k non-overlapping windows. At each index, I either select the current window and jump x positions or skip the current index. 
    I take the minimum of both choices." */

class Solution {
    int n, x, k;
    long[] minOpsSubarray;
    long[][] memo;
    static final long INF = Long.MAX_VALUE / 4;
    // --------------------------------------------------
    // DP: Select exactly k non-overlapping subarrays.
    // --------------------------------------------------
    long solve(int count, int i) {
        // All k subarrays have been selected.
        if (count == k) {
            return 0;
        }
        // Not enough elements remaining.
        long elementsNeeded = (long) (k - count) * x;
        if (i + elementsNeeded > n) {
            return INF;
        }
        if (memo[count][i] != -1) {
            return memo[count][i];
        }
        // Option 1: Select the subarray starting at i.
        long take = minOpsSubarray[i] + solve(count + 1, i + x);
        // Option 2: Skip the current index.
        long skip = solve(count, i + 1);
        return memo[count][i] = Math.min(take, skip);
    }
    // --------------------------------------------------
    // Sliding Window Median using two TreeMaps.
    // --------------------------------------------------
    class MedianWindow {
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();
        int leftSize = 0;
        int rightSize = 0;
        long leftSum = 0;
        long rightSum = 0;
        // Add an element to the correct multiset.
        void add(int val) {
            if (left.isEmpty() || val <= left.lastKey()) {
                addLeft(val);
            } else {
                addRight(val);
            }
            rebalance();
        }
        // Remove an element from the window.
        void remove(int val) {
            // Remove one occurrence from the appropriate multiset.
            if (left.containsKey(val)) {
                removeLeft(val);
            } else {
                removeRight(val);
            }
            rebalance();
        }
        // Add to left multiset.
        void addLeft(int val) {
            left.put(val, left.getOrDefault(val, 0) + 1);
            leftSize++;
            leftSum += val;
        }
        // Add to right multiset.
        void addRight(int val) {
            right.put(val, right.getOrDefault(val, 0) + 1);
            rightSize++;
            rightSum += val;
        }
        // Remove from left multiset.
        void removeLeft(int val) {
            int freq = left.get(val);
            if (freq == 1) {
                left.remove(val);
            } else {
                left.put(val, freq - 1);
            }
            leftSize--;
            leftSum -= val;
        }
        // Remove from right multiset.
        void removeRight(int val) {
            int freq = right.get(val);
            if (freq == 1) {
                right.remove(val);
            } else {
                right.put(val, freq - 1);
            }
            rightSize--;
            rightSum -= val;
        }
        // Maintain:
        // 1. leftSize = ceil(totalSize / 2)
        // 2. Every left element <= every right element.
        void rebalance() {
            int totalSize = leftSize + rightSize;
            int targetLeftSize = (totalSize + 1) / 2;
            // Move maximum element from left to right.
            while (leftSize > targetLeftSize) {
                int val = left.lastKey();
                removeLeft(val);
                addRight(val);
            }
            // Move minimum element from right to left.
            while (leftSize < targetLeftSize) {
                int val = right.firstKey();
                removeRight(val);
                addLeft(val);
            }
        }
        // Calculate minimum operations to make all
        // elements in the current window equal.
        long getCost() {
            long median = left.lastKey();
            long leftCost = median * leftSize - leftSum;
            long rightCost = rightSum - median * rightSize;
            return leftCost + rightCost;
        }
    }
    // --------------------------------------------------
    // Main method.
    // --------------------------------------------------
    public long minOperations(int[] nums, int x, int k) {
        this.n = nums.length;
        this.x = x;
        this.k = k;
        // Check whether selecting k non-overlapping
        // subarrays of length x is possible.
        if (x <= 0 || k < 0 || (long) x * k > n) {
            return -1;
        }
        if (k == 0) {
            return 0;
        }
        // Store the minimum cost for every window of size x.
        minOpsSubarray = new long[n + 1];
        MedianWindow window = new MedianWindow();
        int left = 0;
        // Calculate costs using a sliding window.
        for (int right = 0; right < n; right++) {
            // Expand the window.
            window.add(nums[right]);
            // Process once the window reaches size x.
            if (right - left + 1 == x) {
                minOpsSubarray[left] = window.getCost();
                // Remove the outgoing element.
                window.remove(nums[left]);
                left++;
            }
        }
        // Initialize memoization table.
        memo = new long[k + 1][n + 1];
        for (long[] row : memo) {
            Arrays.fill(row, -1);
        }
        // Start DP from index 0 with zero subarrays selected.
        return solve(0, 0);
    }
}

// Time Complexity :- O(n log x + nk).
// Space Complexity :- O(nk + n).
