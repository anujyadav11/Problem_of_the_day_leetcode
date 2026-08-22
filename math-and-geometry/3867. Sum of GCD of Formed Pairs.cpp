/*********************************************** JAVA **************************************************/

// Optimal Solution - Computes sum of paired GCDs by building prefix GCD array with running max, sorting, then pairing smallest with largest via two pointers.
/* The prefix GCD with running max is the problem-specific formula — understand what it computes before optimizing. Sorting then two-pointer pairing is the standard pattern for 'pair all elements and compute something' problems.
    long accumulator is essential — sum of n/2 GCD values can overflow int for large inputs." */

class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int max = -1;
        int[] prefixGCD = new int[n];
        // prefixGCD[i] = gcd(nums[i], max of nums[0..i])
        for (int i = 0; i < n; i++) {
            max = Math.max(nums[i], max);
            prefixGCD[i] = gcd(nums[i], max);
        }
        // sort then pair smallest with largest via two pointers
        Arrays.sort(prefixGCD);
        long ans = 0;
        for (int i = 0, j = n - 1; i < j; i++, j--)
            ans += gcd(prefixGCD[i], prefixGCD[j]);
        return ans;
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}

// Time Complexity :- O(n log n + log(maxVal).
// Space Complexity :- O(n).
