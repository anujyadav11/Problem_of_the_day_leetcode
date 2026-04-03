/*********************************************** JAVA **************************************************/

// Optimal Solution -Maximizes wall destructions by choosing left/right shooting direction per robot using memoized DP with binary search wall counting and neighbor boundary clamping.
/* "The key state is (robotIndex, prevDirection) — only 2n states total. Previous direction matters because a right-shooting robot claims territory that the next robot's left range can't overlap. 
    Binary search on sorted walls makes range counting O(log w) instead of O(w), keeping the solution efficient." */

class Solution {
    int[][] t;
    // count walls strictly within range [L, R] using binary search
    int countWalls(int[] walls, int L, int R) {
        int left = lowerBound(walls, L);
        int right = upperBound(walls, R);
        return right - left;
    }
    // first index >= target
    int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }
    // first index > target
    int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] > target) r = mid;
            else l = mid + 1;
        }
        return l;
    }
    int solve(int[] walls, int[][] roboDist, int[][] range, int i, int prevDir) {
        // base case: all robots processed
        if (i == roboDist.length) return 0;
        // return cached result for state (i, prevDir)
        if (t[i][prevDir] != -1) return t[i][prevDir];
        int leftStart = range[i][0];
        // if previous robot shot RIGHT — current robot's left range starts after prev robot's right limit
        if (prevDir == 1 && i > 0)
            leftStart = Math.max(leftStart, range[i - 1][1] + 1);
        // option 1: shoot LEFT — count walls from leftStart to robot position
        int leftTake = countWalls(walls, leftStart, roboDist[i][0]) +
                       solve(walls, roboDist, range, i + 1, 0);
        // option 2: shoot RIGHT — count walls from robot position to right limit
        int rightTake = countWalls(walls, roboDist[i][0], range[i][1]) +
                        solve(walls, roboDist, range, i + 1, 1);
        // memoize and return best direction
        return t[i][prevDir] = Math.max(leftTake, rightTake);
    }
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        // pair each robot with its distance
        int[][] roboDist = new int[n][2];
        for (int i = 0; i < n; i++) {
            roboDist[i][0] = robots[i];
            roboDist[i][1] = distance[i];
        }
        // sort robots by position for left-to-right processing
        Arrays.sort(roboDist, (a, b) -> a[0] - b[0]);
        // sort walls for binary search
        Arrays.sort(walls);
        // compute clamped shooting range for each robot
        int[][] range = new int[n][2];
        for (int i = 0; i < n; i++) {
            int pos = roboDist[i][0];
            int d = roboDist[i][1];
            // left boundary: can't overlap with previous robot
            int leftLimit = (i == 0) ? 1 : roboDist[i - 1][0] + 1;
            // right boundary: can't overlap with next robot
            int rightLimit = (i == n - 1) ? (int) 1e9 : roboDist[i + 1][0] - 1;
            // clamp range to distance and neighbor boundaries
            range[i][0] = Math.max(pos - d, leftLimit);
            range[i][1] = Math.min(pos + d, rightLimit);
        }
        // initialize dp table — 2 states per robot (prevDir: 0=left, 1=right)
        t = new int[n][2];
        for (int[] row : t) Arrays.fill(row, -1);
        // start from first robot with prevDir=0 (no previous direction constraint)
        return solve(walls, roboDist, range, 0, 0);
    }
}

// Time Complexity :- O(n log n + n log w).
// Space Complexity :- O(n).
