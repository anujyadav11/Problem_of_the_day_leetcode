/*********************************************** JAVA **************************************************/

// Sorting Solution - Solved Find Target Indices After Sorting Array using sorting and linear search in O(n log n) time.

class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        List<Integer> res = new ArrayList<>();
        // Sort the array in ascending order.
        Arrays.sort(nums);
        // Find every index where the element equals target.
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                res.add(i);
            }
        }
        return res;
    }
}

// Time Complexity :- O(n log n).
// Space Complexity :- O(n).

/*********************************************** JAVA **************************************************/

// Optimal Solution - Solved Find Target Indices After Sorting Array without sorting using counting, achieving O(n) time and O(1) auxiliary space.
/* “Sorting is unnecessary because the target’s starting index in the sorted array equals the number of elements smaller than it. 
    I count elements smaller than and equal to the target, then generate the consecutive indices occupied by the target.” */

class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        List<Integer> res = new ArrayList<>();
        int smaller = 0;
        int equal = 0;
        // Count elements smaller than target and equal to target.
        for (int num : nums) {
            if (num < target) {
                smaller++;
            } else if (num == target) {
                equal++;
            }
        }
        // Target elements begin after all smaller elements.
        // Add consecutive indices for every occurrence of target.
        for (int i = smaller; i < smaller + equal; i++) {
            res.add(i);
        }
        return res;
    }
}

// Time Complexity :- O(n + k).
// Space Complexity :- O(k).
