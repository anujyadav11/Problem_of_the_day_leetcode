/*********************************************** JAVA **************************************************/

// Optimal Solution - Counts special characters by checking which letters have both lowercase and uppercase forms present using two fixed-size frequency arrays.
/* "Two separate frequency arrays are cleaner than one combined array with bit manipulation here — straightforward and readable. 
    The O(1) space claim holds because the arrays are fixed size 26 regardless of input length. For a one-liner approach: use two Sets, 
    compute their intersection size — same complexity but less cache-friendly." */

class Solution {
    public int numberOfSpecialChars(String word) {
        int[] lowCnt = new int[26];
        int[] upCnt = new int[26];
        // count lowercase and uppercase frequencies separately
        for (char ch : word.toCharArray()) {
            if (ch >= 'a' && ch <= 'z')
                lowCnt[ch - 'a']++;
            else if (ch >= 'A' && ch <= 'Z')
                upCnt[ch - 'A']++;
        }
        int speCnt = 0;
        // special char: both lowercase and uppercase versions present
        for (int i = 0; i < 26; i++)
            if (lowCnt[i] > 0 && upCnt[i] > 0)
                speCnt++;
        return speCnt;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
