/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds the longest odd-length special subsequence by building squaring chains from each unique value, requiring frequency ≥ 2 to extend each step.
/* "The squaring chain x → x² → x⁴ grows exponentially so its length is at most O(log log maxVal) — very short in practice. 
    The odd-length constraint comes from the problem definition — subtract 1 from even counts. Handle 1 separately since 1² = 1 creates an infinite loop in the chain logic." */

class Solution {
    public int maximumLength(int[] nums) {
        // frequency map for all elements
        Map<Long, Integer> map = new HashMap<>();
        int ones = 0;
        for (int num : nums) {
            map.merge((long) num, 1, Integer::sum);
            if (num == 1) ones++;
        }
        // handle 1s separately — longest odd subsequence of 1s
        int ans = (ones % 2 == 0) ? ones - 1 : ones;
        map.remove(1L);
        for (long num : map.keySet()) {
            int count = 0;
            long curr = num;
            while (map.containsKey(curr)) {
                if (map.get(curr) >= 2) {
                    // can use this value twice — advance to curr*curr
                    count += 2;
                } else {
                    // can use only once — end chain here
                    count++;
                    break;
                }
                curr = curr * curr;
            }
            // subsequence length must be odd
            if (count % 2 == 0) count--;
            ans = Math.max(ans, count);
        }
        return ans;
    }
}

// Time Complexity :- O(n * loglog m).
// Space Complexity :- O(n).
