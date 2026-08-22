/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds missing integers between min and max by building a HashSet in one pass and scanning the range for absent values.
/* "This is optimal when the range is close to n (dense array). For sparse arrays where range >> n, sort + gap scan avoids iterating the full range. 
    Always clarify input density before choosing — HashSet approach is O(range) scan while sort approach is O(n log n) regardless of range." */

class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        // build set while finding min and max in single pass
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
            set.add(num);
        }
        List<Integer> ans = new ArrayList<>();
        // scan range (min, max) exclusive — add values not in set
        for (int i = min + 1; i < max; i++)
            if (!set.contains(i))
                ans.add(i);
        return ans;
    }
}

// Time Complexity :- O(n + range). — n for set build, range = max - min for scan
// Space Complexity :- O(n). — HashSet stores all n elements
