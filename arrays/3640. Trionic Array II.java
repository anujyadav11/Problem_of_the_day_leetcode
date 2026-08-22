/*********************************************** JAVA **************************************************/

Optimal Solution - Constant-space DP solution to compute the maximum-sum trionic subsequence in linear time.
                   Model the trionic pattern as three DP states and update them greedily in one pass, similar to Kadane-style transitions.

class Solution {
    public long maxSumTrionic(int[] nums) {
        long inc1 = Long.MIN_VALUE / 2; // Represents sum of nums[l...p] ending at current index
        long dec = Long.MIN_VALUE / 2;  // Represents sum of nums[l...p...q] ending at current index
        long inc2 = Long.MIN_VALUE / 2; // Represents sum of nums[l...p...q...r] ending at current index
        long maxTotal = Long.MIN_VALUE;
        int n = nums.length;
        // We iterate starting from the second element because all parts require length >= 2
        for (int i = 1; i < n; i++) {
            long currNum = nums[i];
            long prevNum = nums[i-1];
            long nextInc1 = Long.MIN_VALUE / 2;
            long nextDec = Long.MIN_VALUE / 2;
            long nextInc2 = Long.MIN_VALUE / 2;
            // Transition for Part 1: Increasing (l...p)
            if (currNum > prevNum) {
                // Either extend existing increasing sequence or start a new one of length 2
                // Starting new: prevNum + currNum
                // Extending: inc1 + currNum
                nextInc1 = currNum + Math.max(prevNum, inc1);
            }
            
            // Transition for Part 2: Decreasing (p...q)
            if (currNum < prevNum) {
                // Either extend existing decreasing sequence or transition from Part 1
                // Transition from Part 1: inc1 (which ended at i-1) + currNum
                // Extending: dec + currNum
                nextDec = currNum + Math.max(inc1, dec);
            }
            
            // Transition for Part 3: Increasing (q...r)
            if (currNum > prevNum) {
                // Either extend existing second increasing sequence or transition from Part 2
                // Transition from Part 2: dec (which ended at i-1) + currNum
                // Extending: inc2 + currNum
                nextInc2 = currNum + Math.max(dec, inc2);
            }
            inc1 = nextInc1;
            dec = nextDec;
            inc2 = nextInc2;
            if (inc2 > maxTotal) {
                maxTotal = inc2;
            }
        }
        return maxTotal;
    }
}
Time Complexity :- O(n).
Space Complexity :- O(1).
