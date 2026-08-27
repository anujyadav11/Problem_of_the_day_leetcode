/*********************************************** JAVA **************************************************/

// Optimal Solution - Construct the lexicographically smallest permutation greater than the target using frequency-based backtracking.
/* “I store the frequency of each character and construct the permutation from left to right. While the current prefix is equal to the target, 
    I cannot choose a smaller character. Choosing the same character keeps the prefix equal, while choosing a larger character makes the entire permutation greater. 
    Once the prefix becomes greater, I always choose the smallest available characters. Because I try characters from a to z, the first valid complete permutation is the lexicographically smallest one.” */

class Solution {
    String res = "";
    public String lexGreaterPermutation(String s, String target) {
        // Count frequency of every character
        int[] count = new int[26];
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }
        StringBuilder curr = new StringBuilder();
        // Build the smallest permutation greater than target
        solve(curr, count, target, 0, false);
        return res;
    }
    public boolean solve(StringBuilder curr, int[] count,String target, int i, boolean greater) {
        // Complete permutation found
        if (i == target.length()) {
            if (greater) {
                res = curr.toString();
                return true;
            }
            return false;
        }
        // Try characters in increasing order
        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (count[ch - 'a'] == 0)
                continue;
            // If still equal to target, cannot choose a smaller character
            if (!greater && ch < target.charAt(i))
                continue;
            curr.append(ch);
            count[ch - 'a']--;
            // Once a larger character is chosen, the whole
            // permutation is guaranteed to be greater
            boolean isGreater = greater || ch > target.charAt(i);
            // Continue building the permutation
            if (solve(curr, count, target, i + 1, isGreater)) {
                return true;
            }
            // Backtrack
            curr.deleteCharAt(curr.length() - 1);
            count[ch - 'a']++;
        }
        return false;
    }
}

// Time Complexity :- O(n!).
// Space Complexity :- O(n).
