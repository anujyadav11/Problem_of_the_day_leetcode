/*********************************************** JAVA **************************************************/

Optimal Solution - Greedy scan to find the smallest character strictly greater than a target with wrap-around handling.
                   Track the minimum character greater than target; if none is found, return the first element for circular ordering.

class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        // Initialize result with first character (wrap-around case)
        char res = letters[0];
        // Flag to indicate whether a valid greater character is found
        boolean flag = false;
        // Iterate through all characters in the array
        for (char ch : letters) {
            // If no greater character found yet
            if (!flag) {
                // First character greater than target
                if (ch > target) {
                    res = ch;
                    flag = !flag;
                }
            } 
            // If a greater character is already found
            else {
                // Try to find a smaller valid character greater than target
                if (ch > target && ch < res)
                    res = ch;
            }
        }
        // Return the smallest character greater than target
        // or the first character if none exists
        return res; 
    }
}

Time Complexity :- O(N).
Space Complexity :- O(1).
