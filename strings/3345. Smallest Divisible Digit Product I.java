/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds smallest number ≥ n whose digit product is divisible by t via linear search with digit product computation.
/* "The brute force linear scan works efficiently here since the gap to the next valid number is bounded — at most 9 steps since appending any factor of t as a digit creates a valid number. 
    For large t values with prime factors > 9 (like t = 11), no single digit works — the answer might require multiple steps but still converges quickly in practice." */

class Solution {
    public int smallestNumber(int n, int t) {
        // Keep checking numbers starting from n
        while (true) {
            // Calculate the product of the current number's digits
            int product = digitProduct(n);
            // Return the first number whose digit product is divisible by t
            if (product % t == 0) {
                return n;
            }
            // Check the next number
            n++;
        }
    }
    // Returns the product of all digits in the given number
    private int digitProduct(int num) {
        int product = 1;
        while (num > 0) {
            product *= num % 10;
            num /= 10;
        }
        return product;
    }
}

// Time Complexity :- O(d × gap) — d digits per number, gap = distance to next valid number (at most 9 steps in practice)
// Space Complexity :- O(1).
