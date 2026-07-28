/*********************************************** JAVA **************************************************/

// Optimal Solution - Builds smallest palindrome by sorting the first half lexicographically and mirroring it onto the second half.
/* "Sorting only the first half is the key insight — the second half is fully determined by mirroring, so only the first half's arrangement matters for lexicographic ordering. 
    Odd-length strings have a fixed middle character that doesn't affect either half's palindrome constraint — leave it untouched. 
    This is O(n log n) but can be done O(n) with counting sort since inputs are typically lowercase letters." */

class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();
        int mid = n / 2;
        char[] chArr = s.toCharArray();
        // sort only the first half — gives lexicographically smallest arrangement
        Arrays.sort(chArr, 0, mid);
        // mirror first half onto second half to complete palindrome
        for (int i = 0; i < mid; i++)
            chArr[n - 1 - i] = chArr[i];
        return new String(chArr);
    }
}

// Time Complexity :- O(n log n).
// Space Complexity :- O(n).
