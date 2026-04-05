/*********************************************** JAVA **************************************************/

// Optimal Solution - Simulate movement on a 2D grid and check if final position returns to origin.
/* “I simulate movements using x and y coordinates and check if we end up back at (0,0).” */

class Solution {
    public boolean judgeCircle(String moves) {
        int x = 0;  // Horizontal position (left/right)
        int y = 0;  // Vertical position (up/down)
        // Traverse each move
        for (char ch : moves.toCharArray()) {
            if (ch == 'U')
                y++;        // Move up
            
            else if (ch == 'D')
                y--;        // Move down
            
            else if (ch == 'L')
                x--;        // Move left
            
            else if (ch == 'R')
                x++;        // Move right
        }
        // Return true if back to origin (0,0)
        return x == 0 && y == 0;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
