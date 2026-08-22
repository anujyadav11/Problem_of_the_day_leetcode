/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds maximum product of any two digits in n by extracting all digits and checking all pairs.
/* "For digit problems always extract digits first into an array — cleaner than arithmetic tricks mid-loop. 
    Since n has at most 9-10 digits, O(d²) is effectively O(1). The optimal O(d log d) approach sorts digits descending and returns digits[0] * digits[1] — mention this as a follow-up." */

class Solution {
    public int maxProduct(int n) {
        int res = 0;
        // extract all digits
        List<Integer> digits = new ArrayList<>();
        while (n > 0) {
            digits.add(n % 10);
            n /= 10;
        }
        // find maximum product of any two digits
        for (int i = 0; i < digits.size(); i++)
            for (int j = i + 1; j < digits.size(); j++)
                res = Math.max(res, digits.get(i) * digits.get(j));
        return res;
    }
}

// Time Complexity :- O(d^2). d = digits
// Space Complexity :- O(d).
