/*********************************************** JAVA **************************************************/

// Optimal Solution - Count numbers containing a comma by directly calculating the range from 1,000 to n.
/* “Since 1,000 is the first number containing a comma, every integer from 1,000 through n contributes exactly one comma under the problem’s constraints. 
    Therefore, instead of iterating, I calculate the count directly as n - 999.” */

class Solution {
    public int countCommas(int n) {
        if (n < 1000) {
            return 0;
        }
        return n - 999;
    }
}

// Time Complexity :- O(1).
// Space Complexity :- O(1).
