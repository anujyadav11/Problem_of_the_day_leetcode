/**************************************************** JAVA ************************************************/

optimal Solution - We maximise the matrix sum by converting all values to their absolute form and, if the count of negative numbers is odd, 
                    subtracting twice the smallest absolute value to account for one unavoidable negative. 


class Solution {
    public long maxMatrixSum(int[][] matrix) {
        // Stores the sum of absolute values of all elements
        long totalSum = 0;
        // Stores the smallest absolute value in the matrix
        int minAbsVal = Integer.MAX_VALUE;
        // Counts the number of negative elements
        int negCnt = 0;
        // Traverse the entire matrix
        for (int[] row : matrix) {
            for (int val : row) {
                // Add absolute value to total sum
                totalSum += Math.abs(val);
                // Count negative numbers
                if (val < 0)  negCnt++;
                // Track the minimum absolute value
                minAbsVal = Math.min(minAbsVal, Math.abs(val));
            }
        }
        // If number of negatives is odd,
        // one element must remain negative
        if (negCnt % 2 != 0)  totalSum -= 2 * minAbsVal;
        // Return the maximum possible matrix sum
        return totalSum;
    }
}
Time Complexity :- O(n * m)
Space Complexity :- O(1)
