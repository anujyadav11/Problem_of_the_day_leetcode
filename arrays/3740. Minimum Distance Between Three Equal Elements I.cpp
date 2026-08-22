/*********************************************** JAVA **************************************************/

// Optimal Solution - Group indices by value and compute minimum distance using consecutive triplets.
/* “Instead of checking all triplets, I group indices and only check consecutive occurrences to minimize distance.” */

class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        // Map to store indices of each number
        Map<Integer, List<Integer>> map = new HashMap<>();
        // Store all indices
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], x -> new ArrayList<>()).add(i);
        }
        int ans = Integer.MAX_VALUE;
        // Process each number
        for (List<Integer> list : map.values()) {
            // Need at least 3 occurrences
            if (list.size() < 3) continue;
            // Check consecutive triplets
            for (int i = 0; i + 2 < list.size(); i++) {
                int left = list.get(i);
                int right = list.get(i + 2);       
                ans = Math.min(ans, 2 * (right - left));
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n).
