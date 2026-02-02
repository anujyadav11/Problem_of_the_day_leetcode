/*********************************************** JAVA **************************************************/

Optimal Solution - Sliding window + greedy solution using balanced sets to track the (k−1) smallest elements efficiently.  
                   Use two ordered sets to maintain the smallest (k−1) elements in a sliding window and rebalance on insert/remove to keep the minimum sum optimal.

class Solution {
    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;
        // TreeSet to maintain the (k-1) smallest elements in the current window
        // Sorted by value first, then index to avoid collisions
        TreeSet<int[]> kMinimum = new TreeSet<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });
        // TreeSet to store the remaining elements outside the (k-1) minimum set
        TreeSet<int[]> remaining = new TreeSet<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });
        // Sum of the (k-1) smallest elements in the current window
        long sum = 0;
        int i = 1;
        // Build the initial window [1 ... dist]
        while (i < n && i - dist < 1) {
            int[] cur = new int[]{nums[i], i};
            kMinimum.add(cur);
            sum += nums[i];
            // Ensure kMinimum holds only (k-1) smallest values
            if (kMinimum.size() > k - 1) {
                int[] largest = kMinimum.pollLast();
                sum -= largest[0];
                remaining.add(largest);
            }
            i++;
        }
        long result = Long.MAX_VALUE;
        // Slide the window forward
        while (i < n) {
            // Add the new element to the window
            int[] cur = new int[]{nums[i], i};
            kMinimum.add(cur);
            sum += nums[i];
            // Rebalance to keep only (k-1) smallest
            if (kMinimum.size() > k - 1) {
                int[] largest = kMinimum.pollLast();
                sum -= largest[0];
                remaining.add(largest);
            }
            // Update minimum sum found so far
            result = Math.min(result, sum);
            // Remove the element that goes out of the window (i - dist)
            int remIdx = i - dist;
            int[] toRemove = new int[]{nums[remIdx], remIdx};
            // If removed from kMinimum, rebalance from remaining
            if (kMinimum.remove(toRemove)) {
                sum -= nums[remIdx];
                if (!remaining.isEmpty()) {
                    int[] promote = remaining.pollFirst();
                    kMinimum.add(promote);
                    sum += promote[0];
                }
            } else {
                // Otherwise, remove from remaining
                remaining.remove(toRemove);
            }
            i++;
        }
        // nums[0] is always included in the cost
        return nums[0] + result;
    }
}

Time Complexity :- O(n x log(k)).
Space Complexity :- O(k).
