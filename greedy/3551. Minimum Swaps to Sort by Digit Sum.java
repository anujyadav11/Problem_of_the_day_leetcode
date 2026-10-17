/*********************************************** JAVA **************************************************/

// Optimal Solution - Solved minimum swaps using custom digit-sum sorting, original-index tracking, and permutation cycle detection.
/* “I sort elements using the digit-sum comparator and preserve each element’s original index. I then interpret the mapping from sorted positions to original positions as a permutation. 
    Each cycle of length k requires k - 1 swaps, giving the minimum number of swaps.” */

class Solution {
    // Calculate the sum of digits of a number.
    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
    public int minSwaps(int[] nums) {
        int n = nums.length;
        // Store each number alongside its original index.
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }
        // Sort by digit sum, then by numerical value.
        Arrays.sort(arr, (a, b) -> {
            int sumA = getDigitSum(a[0]);
            int sumB = getDigitSum(b[0]);
            if (sumA != sumB) {
                return Integer.compare(sumA, sumB);
            }
            return Integer.compare(a[0], b[0]);
        });
        boolean[] visited = new boolean[n];
        int swaps = 0;
        // Count cycles in the permutation.
        for (int i = 0; i < n; i++) {
            if (visited[i] || arr[i][1] == i) {
                continue;
            }
            int cycleSize = 0;
            int j = i;
            while (!visited[j]) {
                visited[j] = true;
                j = arr[j][1];
                cycleSize++;
            }
            // A cycle of size k requires k - 1 swaps.
            swaps += cycleSize - 1;
        }
        return swaps;
    }
}

// Time Complexity :- O(n log n).
// Space Complexity :- O(n).
