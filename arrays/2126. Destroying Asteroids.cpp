/*********************************************** JAVA **************************************************/

// Optimal Solution - Greedily destroys asteroids in ascending order, accumulating mass after each collision and returning false if any asteroid exceeds current mass.
/* "long totalMass is critical — mass starts as int but accumulated sum of all asteroids can overflow int range easily. 
    Greedy works here because absorbing smaller asteroids first maximizes mass gain rate — if you can't destroy the smallest remaining asteroid with current mass, 
    you can never destroy it regardless of order. Classic greedy exchange argument." */

class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        // sort ascending — must destroy smallest asteroids first
        Arrays.sort(asteroids);
        long totalMass = mass;
        for (int asteroid : asteroids) {
            // can't destroy current asteroid — too heavy
            if (totalMass < asteroid) return false;
            // absorb asteroid and gain its mass
            totalMass += asteroid;
        }
        return true;
    }
}

// Time Complexity :- O(n log n). Because sorting takes log n and traversing the array takes n.
// Space Complexity :- O(1).
