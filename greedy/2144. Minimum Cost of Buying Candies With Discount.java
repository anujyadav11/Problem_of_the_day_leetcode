/*********************************************** JAVA **************************************************/

// Optimal Solution - Sort candies in descending order and greedily make every third candy free to minimize total cost.
/* “To maximize the discount, I sort the candies in descending order and group them in threes. In each group, 
    I pay for the two most expensive candies and get the third one for free.” */

class Solution {
    public int minimumCost(int[] cost) {
        int n = cost.length;
        // Sort candies in ascending order
        Arrays.sort(cost);
        // Reverse array to get descending order
        for (int left = 0, right = n - 1; left < right; left++, right--) {
            int temp = cost[left];
            cost[left] = cost[right];
            cost[right] = temp;
        }
        int totalCost = 0;
        // For every group of 3 candies:
        // Pay for the first two, get the third (cheapest) free
        for (int i = 0; i < n; i++) {
            // Skip every third candy
            if (i % 3 != 2) {
                totalCost += cost[i];
            }
        }
        return totalCost;
    }
}

// Time Complexity :- O(n log n).
// Space Complexity :- O(1).
