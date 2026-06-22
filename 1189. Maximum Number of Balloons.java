/*********************************************** JAVA **************************************************/

// Optimal Solution - Counts maximum "balloon" instances by finding the bottleneck character frequency, dividing by 2 for letters appearing twice in the word.
/* "Always use 'char' - 'a' instead of hardcoded indices — count[11] vs count['l'-'a'] are equivalent but the latter is self-documenting and immune to off-by-one mistakes. 
    The divide-by-2 for l and o is the key insight — they appear twice in 'balloon' so each instance needs two occurrences." */

class Solution {
    public int maxNumberOfBalloons(String text) {
        int[] count = new int[26];
        for (char ch : text.toCharArray())
            count[ch - 'a']++;
        // "balloon" needs: b(1), a(1), l(2), o(2), n(1)
        int min = count['b' - 'a'];
        min = Math.min(min, count['a' - 'a']);
        min = Math.min(min, count['l' - 'a'] / 2);
        min = Math.min(min, count['o' - 'a'] / 2);
        min = Math.min(min, count['n' - 'a']);
        return min;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(26).
