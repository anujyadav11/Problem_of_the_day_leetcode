/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds the k-th character after string operations by forward-computing final length then backward-simulating each operation to trace the original character.
/* "Forward pass builds the final length, backward pass undoes operations to find which original character maps to position k. The % reverse operation is elegantly undone by k = L - k - 1 — same formula forwards and backwards. 
    The key bug was returning on k == L for all operations — must only return for regular characters since those are the only ones that actually place characters." */

class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        long L = 0;
        // forward pass — compute final length of processed string
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '*') {
                // delete last character if string not empty
                if (L > 0) L--;
            } else if (ch == '#') {
                // double the string length
                L *= 2;
            } else if (ch == '%') {
                // reverse — no change in length
            } else {
                // regular character — increment length
                L++;
            }
        }
        // k is out of bounds — character doesn't exist
        if (k >= L) return '.';
        // backward pass — trace back which original character is at position k
        for (int i = n - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch == '*') {
                // undo delete — length increases by 1
                L++;
            } else if (ch == '%') {
                // undo reverse — mirror position k within current length
                k = L - k - 1;
            } else if (ch == '#') {
                // undo double — halve length, remap k to first half
                L /= 2;
                if (k >= L) k -= L;
            } else {
                // undo character addition — length decreases
                L--;
                // found the exact character at position k
                if (k == L) return ch;
            }
        }
        return '.';
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
