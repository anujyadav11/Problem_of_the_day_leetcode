/*********************************************** JAVA **************************************************/

Optimal Solution - Uses sliding window and HashSet to verify presence of all 2^k binary substrings.
                   There are exactly 2^k binary strings of length k — count them using sliding window and stop early when all found.

class Solution {
    public boolean hasAllCodes(String s, int k) {
        // Set to store all unique substrings of length k
        Set<String> set = new HashSet<>();
        int n = s.length();
        // If string length is smaller than k, impossible
        if (n < k)
            return false;
        // Total possible binary codes of length k = 2^k
        int codes = 1 << k;
        // Slide window of size k
        for (int i = k; i <= n; i++) {
            // Extract substring of length k
            String sub = s.substring(i - k, i);
            // If not seen before, add to set
            if (!set.contains(sub)) {
                set.add(sub);
                codes--; // One required code found
            }
            // If all codes found
            if (codes == 0)
                return true;
        }
        return false;
    }
}

Time Complexity :- O(N * K).
Space Complexity :- O(2^k × k).
