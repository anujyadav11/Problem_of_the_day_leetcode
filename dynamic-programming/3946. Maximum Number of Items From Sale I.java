/*********************************************** JAVA **************************************************/

// Optimal Solution - Implemented a 0/1 Knapsack solution with divisibility-based frequency counting and greedy utilization of the remaining budget to maximize the number of sale items.
/* "I first calculate the number of qualifying items for each factor using divisibility checks. I then treat each item as a 0/1 Knapsack choice, where its price is the weight and its qualifying count is the value. 
    Finally, I iterate over possible budget allocations and use the remaining budget to purchase as many minimum-priced items as possible." */

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        // DP array where:
        // f[j] = maximum number of qualifying items obtainable
        // using a budget of j through the knapsack selections.
        int[] f = new int[budget + 1];
        // Stores the minimum price among all available items.
        // This will be used to utilize the remaining budget.
        int mn = Integer.MAX_VALUE;
        // Process every item as a potential factor-based selection.
        for (int[] item : items) {
            // Extract the item's factor and price.
            int factor = item[0];
            int price = item[1];
            // Update the minimum item price.
            mn = Math.min(mn, price);
            // Count how many items have a factor divisible
            // by the current item's factor.
            int cnt = 0;
            for (int[] other : items) {
                // Check whether the other item's factor
                // is divisible by the current factor.
                if (other[0] % factor == 0) {
                    cnt++;
                }
            }
            // 0/1 Knapsack transition.
            // Iterate backwards to ensure each item is selected
            // at most once during this iteration.
            for (int j = budget; j >= price; j--) {
                // Either:
                // 1. Keep the previous best result for budget j.
                // 2. Select the current item and add its qualifying count.
                f[j] = Math.max(f[j],f[j - price] + cnt);
            }
        }
        // Stores the maximum number of items obtainable.
        int ans = 0;
        // Try every possible amount of budget used by the DP.
        for (int j = 0; j <= budget; j++) {
            // Use the remaining budget to purchase
            // as many minimum-priced items as possible.
            ans = Math.max(ans,f[j] + (budget - j) / mn);
        }
        // Return the maximum achievable item count.
        return ans;
    }
}

// Time Complexity :- O(n^2 + n * B).
// Space Complexity :- O(B).
