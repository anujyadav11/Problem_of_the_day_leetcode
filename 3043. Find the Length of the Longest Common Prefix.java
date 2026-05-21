/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds longest common numeric prefix by hashing all prefixes of arr1 and stripping arr2 elements digit-by-digit until a match is found.
/* "Treating numbers as numeric prefixes via integer division is cleaner than string conversion — num / 10 strips the last digit in O(1). 
    The log10(num) + 1 formula gives digit count without string conversion. Early termination !set.contains(val) prevents redundant insertions when multiple arr1 numbers share prefixes." */

class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        // store all numeric prefixes of arr1 elements
        HashSet<Integer> set = new HashSet<>();
        for (int val : arr1) {
            // add val and all its prefixes by stripping digits from right
            while (!set.contains(val) && val > 0) {
                set.add(val);
                val /= 10;
            }
        }
        int res = 0;
        for (int num : arr2) {
            // strip digits from right until a matching prefix is found
            while (!set.contains(num) && num > 0)
                num /= 10;
            if (num > 0)
                // digit count of matched prefix = length of common prefix
                res = Math.max(res, (int) Math.log10(num) + 1);
        }
        return res;
    }
}

// Time Complexity :- O((n + m) * d). — n, m array lengths, d max digits per number (≤ 10 for int) = effectively O(n + m)
// Space Complexity :- O(n * d).— HashSet stores at most d prefixes per arr1 element
