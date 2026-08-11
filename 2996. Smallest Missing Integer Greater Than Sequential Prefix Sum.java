/*********************************************** JAVA **************************************************/

// Optimal Solution - Find the longest consecutive prefix in one pass and use a HashSet to efficiently locate the smallest missing value from its sum.
/* “The optimal approach is linear. I calculate the sum of the longest sequential prefix while scanning the array once. I store all values in a HashSet, 
    allowing O(1) average lookup when incrementing the prefix sum until I find a missing value. This gives O(n) average time and O(n) space.” */

class Solution {
    public int missingInteger(int[] nums) {
        // Store all values for O(1) average lookup
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        // Sum of the longest sequential prefix
        int sum = nums[0];
        // Find the longest consecutive prefix
        for (int i = 1; i < nums.length; i++) {
            // Current element must be exactly previous + 1
            if (nums[i] != nums[i - 1] + 1) {
                break;
            }
            sum += nums[i];
        }
        // Find the smallest missing integer >= sum
        while (set.contains(sum)) {
            sum++;
        }
        return sum;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n).
