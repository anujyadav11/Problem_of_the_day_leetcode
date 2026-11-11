/*********************************************** JAVA **************************************************/

// Optimal Solution - Minimum Cost Optimisation using Greedy: Minimise purchasing cost by comparing individual and combined purchase prices, greedily satisfying overlapping requirements, and using long arithmetic to prevent integer overflow.
/* "I use a greedy strategy to minimise the total purchasing cost. First, I compare the cost of buying both items separately against the combined purchase cost. 
    If separate purchases are cheaper, I buy them independently. Otherwise, I satisfy the overlapping requirements using combined purchases and handle any remaining requirements using whichever option is cheaper: 
    an individual purchase or another combined purchase. I also use long arithmetic to prevent integer overflow during multiplication and addition." */

class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        // If buying the two items separately is cheaper,
        // purchase exactly the required quantities separately.
        if ((long) cost1 + cost2 <= costBoth) {
            return (long) need1 * cost1 + (long) need2 * cost2;
        }
        // Buy as many combined items as possible.
        int both = Math.min(need1, need2);
        // Cast before multiplication to prevent integer overflow.
        long total = (long) both * costBoth;
        // Update the remaining requirements.
        need1 -= both;
        need2 -= both;
        // Fulfill the remaining requirement for item 1.
        if (need1 > 0) {
            total += (long) need1 * Math.min(cost1, costBoth);
        }
        // Fulfill the remaining requirement for item 2.
        if (need2 > 0) {
            total += (long) need2 * Math.min(cost2, costBoth);
        }
        return total;
    }
}

// Time Complexity :- O(1).
// Space Complexity :- O(1).
