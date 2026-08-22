/*********************************************** JAVA **************************************************/

// Optimal Solution - Checks if array is a rotated sorted array by counting descent points — valid rotation has at most one point where the next element is smaller.
/*  "A sorted array rotated at any point creates exactly one 'drop' — the junction between the end of the larger values and start of the smaller values. 
    Zero drops means already sorted, one drop means valid rotation, two or more drops means unsortable by rotation. The % n wraps the last element back to first for the circular comparison." */

class Solution {
    public boolean check(int[] nums) {
        int n = nums.length;
        int drops = 0;
        // count positions where next element is smaller (drop points)
        for (int i = 0; i < n; i++)
            if (nums[(i + 1) % n] < nums[i])
                drops++;
        // valid rotation has at most one drop point
        return drops <= 1;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
