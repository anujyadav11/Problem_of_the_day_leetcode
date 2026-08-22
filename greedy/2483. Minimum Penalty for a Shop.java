/********************************************************* JAVA *********************************************************/

Optimal solution for the problem of the day, 26 December 2025 

This solution uses two arrays, which are prefix and postfix arrays, which will count the minimum penalty for the customer in the shop 
class Solution {
    public int bestClosingTime(String customers) {
        // Length of the input string
        int n = customers.length();
        /*
         * pre[i]  -> number of 'N' (no customers) in range [0, i-1]
         * post[i] -> number of 'Y' (customers) in range [i, n-1]
         */
        int[] pre = new int[n + 1];
        int[] post = new int[n + 1];
        // Build prefix array
        // pre[i] stores how many 'N' appear before index i
        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i - 1]; // carry forward previous count
            
            // If previous hour had no customers, increase penalty
            if (customers.charAt(i - 1) == 'N')
                pre[i]++;
        }

        // Build postfix array
        // post[i] stores how many 'Y' appear from index i to end
        for (int i = n - 1; i >= 0; i--) {
            post[i] = post[i + 1]; // carry forward next count
            
            // If customers arrive after closing, increase penalty
            if (customers.charAt(i) == 'Y')
                post[i]++;
        }
        // Variables to track minimum penalty and best closing hour
        int min = Integer.MAX_VALUE;
        int idx = 0;
        // Try closing shop at every possible hour [0...n]
        for (int i = 0; i <= n; i++) {
            // Penalty = 
            // 'N' before closing + 'Y' after closing
            int pen = pre[i] + post[i];
            // Update minimum penalty and corresponding index
            if (pen < min) {
                min = pen;
                idx = i;
            }
        }
        // Return the hour at which penalty is minimum
        return idx;
        /*
         * Time Complexity (TC):
         * - Prefix loop:  O(n)
         * - Postfix loop: O(n)
         * - Final scan:   O(n)
         * Total TC:       O(n)
         *
         * Space Complexity (SC):
         * - Two arrays of size (n + 1)
         * Total SC:       O(n)
         */
    }
}
