/*********************************************************** JAVA *******************************************************/


Brute Force Solution - so basically we are using a counting variable as our repeated number storing variable and then sort the array for get the repeated number together and then just find 
                       the repeated number.


class Solution {
    public int repeatedNTimes(int[] nums) {
        // Variable to store the repeated element
        int count = 0;
        // Sort the array so equal elements come next to each other
        Arrays.sort(nums);
        // Traverse the sorted array
        for (int i = 0; i < nums.length - 1; i++) {
            // If two adjacent elements are equal,
            // then this element is the repeated one
            if (nums[i] == nums[i + 1]) {
                count = nums[i];
            }
        }
        // Return the repeated element
        return count;
    }
}

Time Complexity :- O(n log n).
Space Complexity :- O(1).

Optimal solution - using a set, we find repeated number but check if we seen the number already.


class Solution {
    public int repeatedNTimes(int[] nums) {
        // HashSet to store elements we have already seen
        Set<Integer> s = new HashSet<>();
        // Traverse each number in the array
        for (int i : nums) {
            // add() returns false if the element already exists in the set
            // If add() is false, it means this number is repeated
            if (!s.add(i))
                return i; // return the repeated number immediately
        }
        // Fallback return (problem guarantees one repeated element)
        return nums[nums.length - 1];
    }
}

Time Complexity :- O(N).
Space Complexity :- O(N). worst case.
