/*********************************************** JAVA **************************************************/

// Optimal Solution - Generate all valid parentheses combinations using backtracking with opening/closing bracket constraints.
/* “I generate parentheses using backtracking. I can add an opening bracket as long as I have not used all n opening brackets.  
    I can add a closing bracket only when there are unmatched opening brackets, which prevents generating invalid prefixes.” */

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        // Generate all possible strings of length 2 * n.
        generate("", n, res);
        return res;
    }
    public void generate(String curr, int n, List<String> res) {
        // A valid parentheses string contains exactly 2 * n characters.
        if (2 * n == curr.length()) {
            if (valid(curr)) {
                res.add(curr);
            }
            return;
        }
        // Try adding an opening parenthesis.
        generate(curr + "(", n, res);
        // Try adding a closing parenthesis.
        generate(curr + ")", n, res);
    }
    boolean valid(String curr) {
        int open = 0;
        for (char ch : curr.toCharArray()) {
            if (ch == '(') {
                open++;
            } else if (ch == ')') {
                open--;
                // More closing brackets than opening brackets
                // means the string can never be valid.
                if (open < 0) {
                    return false;
                }
            }
        }
        // All opening brackets must be closed.
        return open == 0;
    }
}

// Time Complexity :- O(2^(2n) × n).
// Space Complexity :- O(2n).
