/*********************************************** JAVA **************************************************/

// Optimal Solution - Distribute elements between two arrays based on their current last elements and concatenate the arrays.
/* “I initialise two arrays with the first two elements. For every remaining element, I compare the last elements of both arrays and append the current value to the array with the larger last element.
    After processing all elements, I concatenate the two arrays.” */

class Solution {
    public int[] resultArray(int[] nums) {
        // Two arrays to store the elements
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        // First two elements are fixed
        arr1.add(nums[0]);
        arr2.add(nums[1]);
        // Distribute remaining elements
        for (int i = 2; i < nums.length; i++) {
            // Compare the last elements of both arrays
            if (arr1.get(arr1.size() - 1) > arr2.get(arr2.size() - 1)) {
                arr1.add(nums[i]);
            } else {
                arr2.add(nums[i]);
            }
        }
        // Merge arr1 followed by arr2
        int res[] = new int[nums.length];
        int idx = 0;
        for (int num : arr1)
            res[idx++] = num;
        for (int num : arr2)
            res[idx++] = num;
        return res;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n).
