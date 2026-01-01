/**************************************** JAVA *****************************************/

Optimal Solution –  We traverse the digits from right to left, incrementing the first digit that is less than 9 and handling carry propagation, and if all digits are 9, 
                    we create a new array with an extra leading 1 and return it.


class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        // Start from the last digit (least significant digit)
        int i = n - 1;
        // Traverse digits from right to left
        while (i >= 0) {
            // If current digit is less than 9, simply increment it
            // and return the updated array (no carry needed)
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            // If digit is 9, it becomes 0 and carry is propagated
            digits[i] = 0;
            i--;
        }
        // If all digits were 9, we need an extra digit at the beginning
        // Example: [9,9,9] -> [1,0,0,0]
        int[] res = new int[n + 1];
        res[0] = 1;
        return res;
    }
}
Time Complexity :- O(N).
Space Complexity :- O(N) worst case.
