/*********************************************** JAVA **************************************************/

// Optimal Solution - Builds prefix common array by counting combined element frequencies across both arrays, incrementing common count when any value reaches frequency 2.
/* "The key insight is that a value becomes 'common' the moment its combined frequency hits 2 — meaning it appeared once in each array's prefix. 
    Processing both A[i] and B[i] in the same iteration handles the case where A[i] == B[i] correctly too — frequency hits 2 immediately in the same step, counting it once." */

class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        // freq tracks how many times each value has been seen across both arrays
        int[] freq = new int[n + 1];
        int[] res = new int[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            // increment frequency for A[i] — if seen twice it appears in both prefixes
            freq[A[i]]++;
            if (freq[A[i]] == 2) count++;
            // increment frequency for B[i] — if seen twice it appears in both prefixes
            freq[B[i]]++;
            if (freq[B[i]] == 2) count++;
            // store common count for prefix of length i+1
            res[i] = count;
        }
        return res;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n).
