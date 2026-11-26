/*********************************************** JAVA **************************************************/

// Optimal Solution - Simulated alternating greedy stone removal and used move parity to determine the winner in O(1) time and O(1) space.
/* "I simulate the game using a greedy approach. Alice starts by removing 10 stones, and each subsequent player must remove one fewer stone than the previous move. 
    I track the number of successful moves. If the total number of moves is odd, Alice wins; otherwise, Bob wins." */

class Solution {
    public boolean canAliceWin(int n) {
        // Alice starts by removing 10 stones.
        int take = 10;
        // Tracks the total number of successful moves.
        int steps = 0;
        // Continue while enough stones are available
        // for the current player's required move.
        while (n >= take) {
            // Remove the required number of stones.
            n -= take;
            // Decrease the required removal for the next turn.
            // Sequence: 10, 9, 8, 7, ..., 1.
            take -= 1;
            // Count the successful move.
            steps++;
        }
        // Alice wins if the number of successful moves is odd.
        // Odd moves  -> Alice makes the last successful move.
        // Even moves -> Bob makes the last successful move.
        return steps % 2 != 0;
    }
}

// Time Complexity :- O(1).
// Space Complexity :- O(1).
