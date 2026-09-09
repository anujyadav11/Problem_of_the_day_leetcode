/*********************************************** JAVA **************************************************/

// Optimal Solution - Count total commas from 1 to n by processing each three-digit range and multiplying its size by the number of commas per number.
/* “Instead of checking every number, I group numbers by the number of commas they contain. Numbers from 1,000 to 999,999 contain one comma, 
    numbers from 1,000,000 to 999,999,999 contain two, and so on. For each range, I calculate how many numbers it contains and multiply that by the number of commas in each number.” */

class Solution {
    public long countCommas(long n) {
        long res = 0;
        // First number that contains a comma
        long lower = 1000;
        // Number of commas for the current digit range
        long comma = 1;
        while (lower <= n) {
            // Last number in the current comma group
            long upper = lower * 1000 - 1;
            // Don't go beyond n
            if (upper > n)
                upper = n;
            // Number of values in this range
            long countNum = upper - lower + 1;
            // Each number in this range contributes 'comma' commas
            res += countNum * comma;
            // Move to the next comma group
            lower *= 1000;
            comma++;
        }
        return res;
    }
}

// Optimal Solution - Counts total comma occurrences from 1 to n in O(log n) time using powers of 1000.

class Solution {
    public long countCommas(long n) {
        long res = 0;
        // Start from the first number that contains a comma.
        // 1,000 has 1 comma.
        long start = 1000;
        while (start <= n) {
            // Every number from 'start' to 'n' contributes
            // at least one comma.
            res += (n - start + 1);
            // Move to the next comma threshold:
            // 1,000 -> 1,000,000 -> 1,000,000,000 -> ...
            start *= 1000;
        }
        return res;
    }
}

// Time Complexity :- O(log10 n).
// Space Complexity :- O(1).
