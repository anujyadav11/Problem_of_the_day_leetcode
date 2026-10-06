/*********************************************** JAVA **************************************************/

// Optimal Solution - Find the minimum parentheses insertions needed for a valid string using stack-based matching in O(n) time and O(n) space.
/* “I use a stack to track unmatched opening parentheses. When I encounter a closing parenthesis, I match it with an available opening parenthesis; 
    otherwise, I count that an opening parenthesis must be added. At the end, every remaining opening parenthesis needs one closing parenthesis.” */

class Solution {
    public int minAddToMakeValid(String s) {
        Stack<Character> st = new Stack<>();
        int needOpen = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                // Store unmatched opening parentheses.
                st.push(ch);
            } else {
                // Match ')' with a previously seen '(' if possible.
                if (!st.isEmpty()) {
                    st.pop();
                } else {
                    // No '(' is available, so we need to add one.
                    needOpen++;
                }
            }
        }
        // Any remaining '(' needs a matching ')'.
        // needOpen handles unmatched ')'.
        return needOpen + st.size();
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n).
