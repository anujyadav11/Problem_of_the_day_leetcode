/*********************************************** JAVA **************************************************/

// Optimal Solution - Count how many fixed-size windows contain each value and return the largest value appearing in exactly one window.
/* “I iterate over every subarray of length k. For each window, I use a boolean array to record which values are present, ensuring duplicates within the same window are counted only once. 
    I then increment the global count for those values and finally scan from the largest value downward to find the value present in exactly one window.” */

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int[] count = new int[51];
        // Count how many windows contain each number
        for (int i = 0; i <= n - k; i++) {
            boolean[] seen = new boolean[51];
            for (int j = i; j < i + k; j++) {
                seen[nums[j]] = true;
            }
            // Count each distinct number once per window
            for (int x = 0; x <= 50; x++) {
                if (seen[x]) {
                    count[x]++;
                }
            }
        }
        // Find the largest number appearing in exactly one window
        for (int x = 50; x >= 0; x--) {
            if (count[x] == 1) {
                return x;
            }
        }
        return -1;
    }
}

// Time Complexity :- O(n * k).
// Space Complexity :- O(1).
