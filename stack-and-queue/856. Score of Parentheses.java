/*********************************************** JAVA **************************************************/

// Optimal Solution - Calculate nested parentheses scores using a stack and running score in O(n) time and O(n) space.
/* “I use a stack to preserve the score of the outer level while calculating the score inside each pair of parentheses. 
    An empty pair contributes 1, while a non-empty pair doubles its inner score.” */

class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> st = new Stack<>();
        int score = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                // Save the score accumulated outside this
                // pair and start calculating the inner score.
                st.push(score);
                score = 0;
            } else {
                // For "()", inner score is 0, so it contributes 1.
                // For nested expressions, double the inner score.
                score = st.pop() + Math.max(score * 2, 1);
            }
        }
        return score;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n).
