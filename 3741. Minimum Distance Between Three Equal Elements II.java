/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds minimum triplet distance for same-valued elements by grouping indices and checking consecutive triplets using the 2*(rightIndex - leftIndex) cost formula.
/* "The key insight is that optimal triplets always use consecutive indices in the sorted index list — skipping any index can only increase the span. 
    So a simple sliding window of size 3 over each value's index list finds the minimum cost in O(n) total. 
    The cost formula 2*(right-left) comes from the problem's specific distance definition." */

class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        // map each number to its sorted list of indices
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++)
            map.computeIfAbsent(nums[i], x -> new ArrayList<>()).add(i);
        int ans = Integer.MAX_VALUE;
        for (List<Integer> list : map.values()) {
            // need at least 3 occurrences to pick valid i < j < k
            if (list.size() < 3)
                continue;
            // check all consecutive triplets — optimal due to sorted indices
            for (int i = 0; i + 2 < list.size(); i++) {
                int left = list.get(i);
                int right = list.get(i + 2);
                // distance formula: 2 * (right - left) for triplet cost
                ans = Math.min(ans, 2 * (right - left));
            }
        }
        // return -1 if no valid triplet exists
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n).
