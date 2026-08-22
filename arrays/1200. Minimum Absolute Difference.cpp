/*********************************************** JAVA **************************************************/

Optimal Solution - Sort the array and find all adjacent pairs with the minimum absolute difference.
                    After sorting, the minimum absolute difference can only occur between adjacent elements.

class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        int n = arr.length;
        // Sort the array
        Arrays.sort(arr);
        // Find the minimum absolute difference
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            minDiff = Math.min(minDiff, arr[i] - arr[i - 1]);
        }
        // Collect all pairs with the minimum difference
        for (int i = 1; i < n; i++) {
            int diff = arr[i] - arr[i - 1];
            if (diff == minDiff) {
                List<Integer> pair = new ArrayList<>();
                pair.add(arr[i - 1]);
                pair.add(arr[i]);
                res.add(pair);
            }
        }
        return res;
    }
}


Time Complexity :- O(n log n).
Space Complexity :- O(1).
