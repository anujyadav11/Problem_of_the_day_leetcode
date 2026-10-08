/*********************************************** JAVA **************************************************/

// Optimal Solution - Remove the outermost parentheses from every primitive substring using depth tracking in O(n) time and O(n) output space.
/* “I track the current parentheses depth. An opening parenthesis at depth zero is the outermost one, so I skip it. For a closing parenthesis, 
    I decrease the depth first and skip it if the depth becomes zero. This removes the outermost pair from every primitive substring in one pass.” */

class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder res = new StringBuilder();
        int open = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                // If open > 0, this '(' is not the outermost
                // opening parenthesis, so keep it.
                if (open != 0)
                    res.append(ch);
                // Enter a deeper nesting level.
                open++;
            } else if (ch == ')') {
                // Leave the current nesting level first.
                open--;
                // If we are still inside a primitive,
                // this ')' is not the outermost closing parenthesis.
                if (open != 0)
                    res.append(ch);
            }
        }
        return res.toString();
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n) for the resulting StringBuilder.
