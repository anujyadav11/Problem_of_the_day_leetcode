/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds minimum initial energy for all tasks using binary search on energy with greedy feasibility check, sorting tasks by surplus requirement descending.
/* "The sort order is the greedy insight — minimum - actual represents how much extra energy a task demands beyond what it costs. Processing highest surplus tasks first ensures we meet their minimum requirements while energy is still high. 
    Binary search on the answer converts the optimization to a feasibility check." */

class Solution {
    public boolean isPossible(int[][] tasks, int energy) {
        for (int[] task : tasks) {
            int actual = task[0];
            int minimum = task[1];
            // can't start task — current energy below minimum requirement
            if (minimum > energy)
                return false;
            // complete task — spend actual energy
            energy -= actual;
        }
        return true;
    }
    public int minimumEffort(int[][] tasks) {
        // sort by (minimum - actual) descending — tasks needing most extra energy first
        Arrays.sort(tasks, (t1, t2) -> (t2[1] - t2[0]) - (t1[1] - t1[0]));
        int l = 0;
        int r = (int) 1e9;
        int res = r;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (isPossible(tasks, mid)) {
                // mid energy is sufficient — try less
                res = mid;
                r = mid - 1;
            } else {
                // not enough — try more
                l = mid + 1;
            }
        }
        return res;
    }
}

// Time Complexity :- O(n log n + n log(maxEnergy)).
// Space Complexity :- O(1).
