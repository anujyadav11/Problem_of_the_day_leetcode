/*********************************************** JAVA **************************************************/

// Optimal Solution - Uses three-index enumeration with validity checks and a boolean array to count distinct 3-digit even numbers.
/* “I enumerate every possible choice for the hundreds, tens, and units positions. I skip zero for the hundreds position, prevent reusing the same index, 
    and require the units digit to be even. Since duplicate digits can generate the same number through different index combinations, 
    I use a boolean array indexed by the generated number to count each distinct number only once.” */

class Solution {
    public int totalNumbers(int[] digits) {
        int n = digits.length;
        // Tracks whether a 3-digit number has already been counted.
        // This handles duplicate digits in the input.
        boolean[] vis = new boolean[1000];
        int ans = 0;
        // Choose the hundreds digit
        for (int i = 0; i < n; i++) {
            // A 3-digit number cannot start with 0
            if (digits[i] == 0)continue;
            // Choose the tens digit
            for (int j = 0; j < n; j++) {
                // Cannot reuse the same digit index
                if (i == j) continue;
                // Choose the units digit
                for (int k = 0; k < n; k++) {
                    // Cannot reuse an index
                    // Last digit must be even
                    if (k == i || k == j || digits[k] % 2 != 0) continue;
                    // Construct the 3-digit number
                    int x = digits[i] * 100
                          + digits[j] * 10
                          + digits[k];
                    // Count only distinct numbers
                    if (!vis[x]) {
                        vis[x] = true;
                        ans++;
                    }
                }
            }
        }
        return ans;
    }
}

// Time Complexity :- O(n^3).
// Space Complexity :- O(1).
