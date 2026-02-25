/*********************************************** JAVA **************************************************/

Optimal Solution - Encode set-bit counts into array values, sort normally, then decode to achieve custom bit-based sorting.
                   “Instead of using a custom comparator, I encode the bit-count priority directly into the number using a multiplier larger than max value, then sort and decode.”

class Solution {
    // This method sorts the input array 'arr' based on the number of set bits in each element
    public int[] sortByBits(int[] arr) {
        // Step 1: Add a weight to each element based on the number of set bits
        for (int i = 0; i < arr.length; i++) {
            // Calculate the number of set bits in the current element and multiply it by 10001
            // Then add this value to the current element
            arr[i] += Integer.bitCount(arr[i]) * 10001;
        }
        // Step 2: Sort the modified array
        Arrays.sort(arr);
        // Step 3: Remove the weight added in step 1 from each element
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] % 10001; // This operation essentially removes the weight
        }
        // Step 4: Return the sorted array
        return arr;
    }
}

Time Complexity :- O(n log n).
Space Complexity :- O(1).
