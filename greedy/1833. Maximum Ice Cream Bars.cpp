/*********************************************** JAVA **************************************************/

// Optimal Solution - Maximizes ice cream count by greedily buying cheapest options first after sorting, stopping when budget is exhausted.
/* "Greedy works here because buying cheaper items first always leaves more budget for additional items — any other order can only buy equal or fewer ice creams. 
    Formally proved by exchange argument: swapping a cheaper item later with a more expensive item earlier can never increase the count. Early break on coins < cost works because array is sorted — all remaining items are at least as expensive." */

class Solution {
    public int maxIceCream(int[] costs, int coins) {
        // sort ascending — buy cheapest first to maximize count
        Arrays.sort(costs);
        int count = 0;
        for (int cost : costs) {
            // can't afford current ice cream — stop
            if (coins < cost) break;
            coins -= cost;
            count++;
        }
        return count;
    }
}

// Time Complexity :- O(n log n).
// Space Complexity :- O(1).
