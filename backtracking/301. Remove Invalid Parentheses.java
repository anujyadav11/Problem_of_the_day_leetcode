/*********************************************** JAVA **************************************************/

// Optimal Solution - Remove the minimum number of invalid parentheses using DFS backtracking with balance-based pruning and duplicate elimination.
/* “I use backtracking where each parenthesis has two choices: keep it or remove it. I maintain a balance count to prune any path where closing parentheses exceed opening parentheses. 
    At the end, I keep only balanced strings, and maxLen ensures that only strings requiring the minimum number of removals are returned.” */

class Solution {
    // Stores all valid results without duplicates.
    private Set<String> st = new HashSet<>();
    private int n;
    // Length of the longest valid string found so far.
    private int maxLen;
    private void solve(String s, int i, StringBuilder curr, int count) {
        // More ')' than '(' means the current string
        // can never become valid, so stop exploring this path.
        if (count < 0)
            return;
        // All characters have been processed.
        if (i == n) {
            // A valid parentheses string must have balance 0.
            if (count == 0) {
                // Found a longer valid string.
                // Clear previous shorter results.
                if (curr.length() > maxLen) {
                    maxLen = curr.length();
                    st.clear();
                }
                // Store all valid strings having maximum length.
                if (curr.length() == maxLen) {
                    st.add(curr.toString());
                }
            }
            return;
        }
        char c = s.charAt(i);
        // Letters are always valid, so they must be kept.
        if (c != '(' && c != ')') {
            curr.append(c);
            solve(s, i + 1, curr, count);
            // Backtrack.
            curr.deleteCharAt(curr.length() - 1);
            return;
        }
        // Choice 1: Keep the current parenthesis.
        curr.append(c);
        solve(s,i + 1,curr,count + (c == '(' ? 1 : -1));
        // Backtrack before trying the second choice.
        curr.deleteCharAt(curr.length() - 1);
        // Choice 2: Remove the current parenthesis.
        solve(s, i + 1, curr, count);
    }
    public List<String> removeInvalidParentheses(String s) {
        n = s.length();
        maxLen = 0;
        st.clear();
        // Start DFS with an empty result and balance 0.
        solve(s, 0, new StringBuilder(), 0);
        return new ArrayList<>(st);
    }
}

// Time Complexity :- O(2^n * n).
// Space Complexity :- O(2^n * n).
