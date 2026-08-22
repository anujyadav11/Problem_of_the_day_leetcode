/*********************************************** JAVA **************************************************/

// Optimal Solution - Compute hour and minute hand positions mathematically and return the smaller angle between them.
/* “I calculate the exact positions of the hour and minute hands, including the hour hand’s continuous movement with minutes, then return the minimum of the two possible angles.” */

class Solution {
    public double angleClock(int hour, int minutes) {
        // Hour hand moves 30° per hour and 0.5° per minute
        double hourAngle = (hour % 12) * 30 + 0.5 * minutes;
        // Minute hand moves 6° per minute
        double minuteAngle = minutes * 6;
        // Absolute angle between the two hands
        double angleDifference = Math.abs(hourAngle - minuteAngle);
        // Return the smaller angle
        return Math.min(angleDifference, 360 - angleDifference);
    }
}

// Time Complexity :- O(1).
// Space Complexity :- O(1).
