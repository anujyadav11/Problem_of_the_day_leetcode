/*********************************************** JAVA **************************************************/

// Optimal Solution - Reverses nested parenthesised substrings in O(n) time using matching-parenthesis jumps and direction reversal.
/* “Instead of actually reversing every substring, I first use a stack to find the matching index for every parenthesis. Then I traverse the string using a direction variable. 
    Whenever I encounter a parenthesis, I jump directly to its matching parenthesis and reverse the traversal direction. 
    This makes nested reversals happen naturally and allows the entire string to be processed in linear time.” */

class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        // Stack used to find matching parentheses.
        Stack<Integer> open = new Stack<>();
        // door[i] stores the index of the matching
        // parenthesis for index i.
        int[] door = new int[n];
        // Find and store matching parenthesis pairs.
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                // Store the index of the opening parenthesis.
                open.push(i);
            } else if (s.charAt(i) == ')') {
                // Match this closing parenthesis with
                // the most recent opening parenthesis.
                int j = open.pop();
                door[i] = j;
                door[j] = i;
            }
        }
        StringBuilder res = new StringBuilder();
        // Direction of traversal.
        // 1  -> move forward
        // -1 -> move backward
        int dir = 1;
        // Traverse the string.
        for (int i = 0; i < n; i += dir) {
            // When we encounter a parenthesis,
            // jump to its matching parenthesis
            // and reverse the traversal direction.
            if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                i = door[i];
                dir = -dir;
            } else {
                // Normal character: add it to the result.
                res.append(s.charAt(i));
            }
        }
        return res.toString();
    }
}

// Time Complexity :- O(N).
// Space Complexity :- O(N).
