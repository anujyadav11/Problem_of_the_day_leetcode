/*********************************************** JAVA **************************************************/

// Optimal Solution - Minimizes total key presses by greedily assigning letters to the lowest available press level, processing 8 letters per level across the phone keypad.
/* "8 keys × multiple presses per key — letters assigned to press level k cost k presses each. Greedy assignment (fill level 1 first, then 2, etc.) is optimal since lower press counts are always cheaper. 
    The loop runs at most 4 times for 26 letters, making this effectively O(1)." */

class Solution {
    public int minimumPushes(String word) {
        int remaining = word.length();
        int totalPushes = 0;
        int pressCount = 1; // current number of presses needed per key
        // assign 8 letters per press level (8 keys on phone keypad)
        while (remaining >= 8) {
            // all 8 slots at current press level cost pressCount each
            totalPushes += 8 * pressCount;
            pressCount++;
            remaining -= 8;
        }
        // handle leftover letters at current press level
        totalPushes += remaining * pressCount;
        return totalPushes;
    }
}

// Time Complexity :- O(1). ~ O(n / 8).
// Space Complexity :- O(1).
