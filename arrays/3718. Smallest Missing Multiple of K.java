/*********************************************** JAVA **************************************************/

// Optimal Solution - Find the smallest missing multiple of k using HashSet for constant-time membership checks.
/* “I put all array values into a HashSet for fast lookup. Then I start from k and repeatedly add k, checking whether each multiple exists. The first multiple not present in the set is the smallest missing multiple.” */

class Solution {
    public int missingMultiple(int[] nums, int k) {
        // Store all numbers for O(1) average lookup
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        // Start with the first positive multiple of k
        int ans = k;
        // Keep checking multiples until a missing one is found
        while (set.contains(ans)) {
            ans += k;
        }
        return ans;
    }
}

// Time Complexity :- O(n + m).
// Space Complexity :- O(n).
