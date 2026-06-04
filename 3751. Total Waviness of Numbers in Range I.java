/*********************************************** JAVA **************************************************/

// Optimal Solution - Computes total waviness of a number range by counting digit peaks and valleys across all numbers using adjacent character comparison.
/* "ch > ch - 1 compares the char ch to its ASCII value minus 1 — not to the previous character. Always use s.charAt(i-1) for adjacent character access. 
    The loop bound i < l - 1 not i < l - 2 is critical — l-2 skips the second-to-last position which can validly be a peak or valley between index l-3 and l-1." */

class Solution {
    public int totalWaviness(int num1, int num2) {
        int score = 0;
        // accumulate waviness score for all numbers in range
        for (int num = num1; num <= num2; num++)
            score += findWaveScore(num);
        return score;
    }
    public int findWaveScore(int num) {
        String s = Integer.toString(num);
        int l = s.length();
        // need at least 3 digits for a peak or valley
        if (l < 3) return 0;
        int score = 0;
        // i < l - 1 to include second-to-last character
        for (int i = 1; i < l - 1; i++) {
            char prev = s.charAt(i - 1);
            char curr = s.charAt(i);
            char next = s.charAt(i + 1);
            // compare adjacent characters not ASCII arithmetic
            if (curr > prev && curr > next) score++; // local peak
            if (curr < prev && curr < next) score++; // local valley
        }
        return score;
    }
}

// Time Complexity :- O((num2 - num1) × d).
// Space Complexity :- O(d).
