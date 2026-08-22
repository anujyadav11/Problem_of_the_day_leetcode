/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds the smallest number ≥ num with a digit product divisible by t, using prefix remainder tracking and greedy digit increment with optimal suffix filling.
/* "The prime factor check is the first gate — if t has any prime > 7, no digit product can satisfy it since digits are 1-9. The prefix tracking via GCD remainder elegantly handles partial satisfaction. 
    freeSlotsFiller greedily uses large digits to pack the remaining factor into minimum digits — reversing because we build from largest to smallest." */

class Solution {
    // builds smallest zero-free number of at least 'length' digits divisible by 'required'
    private String freeSlotsFiller(long required, int length) {
        StringBuilder str = new StringBuilder();
        // greedily divide by largest digits first to minimize digit count
        for (int digit = 9; digit >= 2; digit--) {
            while (required % digit == 0) {
                str.append((char) (digit + '0'));
                required /= digit;
            }
        }
        // pad with 1s to reach minimum required length
        while (str.length() < length)
            str.append('1');
        // digits were added largest-first — reverse for correct order
        str.reverse();
        return str.toString();
    }

    public String smallestNumber(String num, long t) {
        int n = num.length();
        // t must only contain prime factors 2, 3, 5, 7 (single digits)
        long temp = t;
        for (int prime : new int[]{2, 3, 5, 7}) {
            while (temp % prime == 0)
                temp /= prime;
        }
        // remaining factor > 1 means t has prime > 7 — impossible with single digits
        if (temp != 1) return "-1";
        // remainingFactor[i] = factor of t still needed after using first i digits of num
        long[] remainingFactor = new long[n + 1];
        remainingFactor[0] = t;
        for (int i = 0; i < n; i++) {
            int digit = num.charAt(i) - '0';
            // zero digit breaks the product — stop prefix here
            if (digit == 0) break;
            remainingFactor[i + 1] = remainingFactor[i] / gcd(remainingFactor[i], digit);
        }
        // num itself satisfies t — return as is
        if (remainingFactor[n] == 1) return num;
        // find first zero position — can't use prefix beyond it
        int zeroIdx = num.indexOf('0');
        if (zeroIdx == -1) zeroIdx = n - 1;
        // try incrementing digit at position i and filling rest optimally
        for (int i = zeroIdx; i >= 0; i--) {
            long required = remainingFactor[i];
            int freeSlots = n - 1 - i;
            // try each digit larger than current at position i
            for (int digit = (num.charAt(i) - '0') + 1; digit <= 9; digit++) {
                long furtherRequired = required / gcd(required, digit);
                String suffix = freeSlotsFiller(furtherRequired, freeSlots);
                // suffix fits exactly — valid answer found
                if (suffix.length() == freeSlots)
                    return num.substring(0, i) + (char) (digit + '0') + suffix;
            }
        }
        // no valid modification found — need one extra digit
        return freeSlotsFiller(t, n + 1);
    }
    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}

// Time Complexity :- O(n × 9 × log t) — n positions, 9 digit choices, freeSlotsFiller is O(log t).
// Space Complexity :- O(n) — remainingFactor array and string building.
