/*********************************************** JAVA **************************************************/

// Optimal Solution - Used repeated string grouping and digit-sum transformation with StringBuilder to reduce the string until its length is at most k.
/* "I repeatedly divide the string into groups of size k, calculate the digit sum of each group, and append the result to a StringBuilder. 
    I replace the original string with the transformed string and repeat until its length is at most k. I use StringBuilder to avoid unnecessary string concatenation." */

class Solution {
    public String digitSum(String s, int k) {
        // Repeat until the string length becomes at most k.
        while (s.length() > k) {
            // Get the current string length in every iteration.
            int n = s.length();
            // Store the transformed string.
            StringBuilder sb = new StringBuilder();
            // Process the string in groups of size k.
            for (int i = 0; i < n; i += k) {
                int sum = 0;
                // Handle the last group if it contains fewer than k characters.
                int end = Math.min(i + k, n);
                // Calculate the sum of digits in the current group.
                for (int j = i; j < end; j++) {
                    sum += s.charAt(j) - '0';
                }
                // Append the digit sum to the resulting string.
                sb.append(sum);
            }
            // Update the string for the next transformation.
            s = sb.toString();
        }
        return s;
    }
}

// Time Complexity :- O(n log n).
// Space Complexity :- O(n).
