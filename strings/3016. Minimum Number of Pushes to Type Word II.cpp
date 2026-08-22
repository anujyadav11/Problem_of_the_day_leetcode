/*********************************************** JAVA **************************************************/

// Optimal Solution - Optimized key assignments by sorting character frequencies in descending order and greedily assigning the most frequent characters to positions with the fewest required key presses.
/* "The goal is to minimize total key presses. Since every character assignment is independent, a greedy strategy works: assign the highest-frequency characters to the cheapest positions (1 press), 
    the next highest to the second cheapest (2 presses), and so on. Sorting the frequency array enables this optimal assignment while keeping the solution efficient." */

class Solution {
    public int minimumPushes(String word) {
        // Store the frequency of each character
        int[] frequency = new int[26];
        for (char ch : word.toCharArray()) {
            frequency[ch - 'a']++;
        }
        // Sort frequencies in ascending order
        Arrays.sort(frequency);
        int assignedCharacters = 0;
        int minimumPushes = 0;
        // Process characters from highest frequency to lowest
        for (int i = 25; i >= 0; i--) {
            // No more characters present
            if (frequency[i] == 0) {
                break;
            }
            // Every group of 8 characters requires one additional key press
            int pressesRequired = assignedCharacters / 8 + 1;
            // Add the total pushes contributed by this character
            minimumPushes += frequency[i] * pressesRequired;
            assignedCharacters++;
        }
        return minimumPushes;
    }
}

// Time Complexity :- O(n log n) but similar to O(n) because sorting the array only for 26 letters.
// Space Complexity :- O(1).
