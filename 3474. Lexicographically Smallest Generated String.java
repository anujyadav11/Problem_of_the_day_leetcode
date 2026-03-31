/*********************************************** JAVA **************************************************/

// Optimal Solution - Constructs a valid string by forcing str2 at 'T' positions, filling gaps with 'a', then greedily breaking accidental str2 matches at 'F' positions.

/* "This is a three-phase greedy — first satisfy all hard constraints from 'T', then fill freely, then fix violations at 'F'. The rightmost-change strategy in Phase 3 is optimal since it preserves the most flexibility for earlier 'F' windows. 
    Returning '' when a fixed window fully matches an 'F' is the key impossibility check." */

class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        // result length = n + m - 1 (str2 slides over str1)
        int N = n + m - 1;
        char[] word = new char[N];
        // tracks which positions were freely assigned (not forced by 'T')
        boolean[] canChange = new boolean[N];
        // initialize all positions with placeholder
        for (int i = 0; i < N; i++)
            word[i] = '$';
        // step 1: force str2 characters at every 'T' position in str1
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                int idx = i;
                for (int j = 0; j < m; j++) {
                    // conflict: two 'T' positions require different chars at same index
                    if (word[idx] != '$' && word[idx] != str2.charAt(j))
                        return "";
                    word[idx] = str2.charAt(j);
                    idx++;
                }
            }
        }
        // step 2: fill unfixed positions with 'a' and mark them as changeable
        for (int i = 0; i < N; i++) {
            if (word[i] == '$') {
                word[i] = 'a';
                canChange[i] = true;
            }
        }
        // step 3: for every 'F' position ensure str2 does NOT appear starting there
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                // if str2 currently matches at position i — must break it
                if (isSame(word, str2, i, m)) {
                    boolean changed = false;
                    // try to change rightmost changeable position in window
                    for (int k = i + m - 1; k >= i; k--) {
                        if (canChange[k]) {
                            // change to 'b' to differ from str2 (str2 chars could be 'a')
                            word[k] = 'b';
                            canChange[k] = false;
                            changed = true;
                            break;
                        }
                    }
                    // all positions in window are fixed by 'T' — impossible to break match
                    if (!changed)
                        return "";
                }
            }
        }
        // return the constructed valid string
        return new String(word);
    }

    private boolean isSame(char[] word, String str2, int i, int m) {
        // check if word[i..i+m-1] matches str2 exactly
        for (int j = 0; j < m; j++) {
            if (word[i] != str2.charAt(j))
                return false;
            i++;
        }
        return true;
    }
}

// Time Complexity :- O(n * m). - each of n positions scans up to m characters in Phase 1 and 3.
// Space Complexity :- O(n + m). - result array and canChange array of size N = n + m - 1.
