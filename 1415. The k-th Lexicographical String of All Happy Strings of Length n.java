/*********************************************** JAVA **************************************************/

// Optimal Solution - Generate lexicographical happy strings using backtracking and return the k-th valid string.
/* "I generate happy strings using backtracking while ensuring no adjacent characters are the same. Since I try characters in lexicographical order, 
    the strings are generated in sorted order, and I stop when I reach the k-th string." */

class Solution {
    String result; // Stores the k-th happy string
    int count; // Tracks how many happy strings we have generated
    public String getHappyString(int n, int k) {
        count = 0;
        result = "";
        // Start backtracking with an empty string
        backtrack(n, k, new StringBuilder());
        return result;
    }
    public boolean backtrack(int n, int k, StringBuilder current) {
        // If current string reaches length n → one happy string found
        if (current.length() == n) {
            count++;
            // If this is the k-th happy string, store it
            if (count == k) {
                result = current.toString();
                return true; // Stop further search
            }
            return false;
        }
        // Try adding characters 'a', 'b', 'c'
        for (char ch = 'a'; ch <= 'c'; ch++) {
            int len = current.length();
            // Skip if same as previous character (not a happy string)
            if (len > 0 && current.charAt(len - 1) == ch)
                continue;
            // Choose the character
            current.append(ch);
            // Explore further
            if (backtrack(n, k, current)) {
                return true;
            }
            // Undo the choice (backtrack)
            current.deleteCharAt(current.length() - 1);
        }
        return false;
    }
}

// Time Complexity :- O(2^n).
// Space Complexity :- O(n).
