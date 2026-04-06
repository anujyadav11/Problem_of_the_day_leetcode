/*********************************************** JAVA **************************************************/

// Optimal Solution - Simulates robot navigation with obstacle avoidance using a HashSet for O(1) collision detection, tracking maximum squared Euclidean distance from origin.

/* "Two key details: update maxDist after every command not just at the end — the robot could reach its furthest point mid-journey before turning back. 
    String concatenation for obstacle keys is simple but x * 60001 + y as an integer hash is faster if performance matters. Turn-left as (d+3)%4 avoids negative modulo issues." */

class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        // store obstacles as "x,y" strings for O(1) lookup
        HashSet<String> set = new HashSet<>();
        for (int[] obs : obstacles)
            set.add(obs[0] + "," + obs[1]);
        int x = 0, y = 0;
        int maxDist = 0;
        // directions: North, East, South, West (clockwise order)
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        // d=0 means facing North initially
        int d = 0;
        for (int command : commands) {
            if (command == -1) {
                // turn right — clockwise next direction
                d = (d + 1) % 4;
            } else if (command == -2) {
                // turn left — counter-clockwise = 3 clockwise steps
                d = (d + 3) % 4;
            } else {
                // move forward step by step — stop if obstacle hit
                for (int i = 0; i < command; i++) {
                    int xx = x + dir[d][0];
                    int yy = y + dir[d][1];
                    // obstacle ahead — stop moving this command
                    if (set.contains(xx + "," + yy))
                        break;
                    x = xx;
                    y = yy;
                }
            }
            // update max Euclidean distance squared after each command
            maxDist = Math.max(maxDist, x * x + y * y);
        }
        // return maximum distance squared from origin
        return maxDist;
    }
}

// Time Complexity :- O(m + n).
// Space Complexity :- O(m).
