/*********************************************** JAVA **************************************************/

// Optimal Solution - Counts special characters where the last lowercase occurrence precedes the first uppercase occurrence using index tracking arrays.
/* "Always use Character.isLowerCase() and Character.isUpperCase() — Java has no standalone isLowerCase() function. 
    Tracking last lowercase (updated every time) and first uppercase (recorded once) elegantly handles the ordering constraint without comparing all pairs. 
    This is stricter than just checking both exist — position matters." */

class Solution {
    public int numberOfSpecialChars(String word) {
        int n = word.length();
        int count = 0;
        // lastSmall[i] = last index of lowercase letter i
        int[] lastSmall = new int[26];
        // firstCap[i] = first index of uppercase letter i
        int[] firstCap = new int[26];
        Arrays.fill(lastSmall, -1);
        Arrays.fill(firstCap, -1);
        for (int i = 0; i < n; i++) {
            char ch = word.charAt(i);
            if (Character.isLowerCase(ch)) {
                // FIXED: Character.isLowerCase not isLowerCase
                lastSmall[ch - 'a'] = i;
            } else {
                // record first occurrence of uppercase only
                if (firstCap[ch - 'A'] == -1)
                    firstCap[ch - 'A'] = i;
            }
        }
        // special: lowercase must appear before uppercase for same letter
        for (int i = 0; i < 26; i++)
            if (lastSmall[i] != -1 && firstCap[i] != -1 && lastSmall[i] < firstCap[i])
                count++;
        return count;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
