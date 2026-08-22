/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds lexicographically smallest subsequence with distinct characters using a monotonic stack, greedily popping larger characters when they appear later in the string.
/* "Three arrays drive the solution — last tells if we can safely remove a character (appears later), seen prevents duplicates in stack, and the stack maintains order. 
    The greedy choice: always prefer smaller characters earlier, but only remove a larger character if it will reappear. This is identical to LC 316 Remove Duplicate Letters." */

class Solution {
    public String smallestSubsequence(String s) {
        // last[c] = last index where character c appears
        int[] last = new int[26];
        for (int i = 0; i < s.length(); i++)
            last[s.charAt(i) - 'a'] = i;
        // seen[c] = whether character c is currently in the stack
        int[] seen = new int[26];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            // skip if character already in stack
            if (seen[c] > 0) continue;
            // pop larger characters that appear later in string
            while (!st.isEmpty() && st.peek() > c && i < last[st.peek()]) {
                seen[st.pop()] = 0;
            }
            st.push(c);
            seen[c] = 1;
        }
        StringBuilder sb = new StringBuilder();
        for (int c : st)
            sb.append((char) ('a' + c));
        return sb.toString();
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
