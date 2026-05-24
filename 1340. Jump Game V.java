/*********************************************** JAVA **************************************************/

// Optimal Solution - Maximizes reachable indices from any start using value-sorted DP where smaller elements are processed first to ensure valid transitions within distance d.
/* "Sorting by value ensures that when computing t[i], all indices reachable from i (which must have smaller values) are already optimally computed — a classic topological ordering trick. 
    The blocking condition arr[j] >= arr[i] is a hard stop, not a skip — once blocked by a taller element, no further elements in that direction are reachable regardless of their height." */

class Solution {
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        // t[i] = maximum indices visitable starting from index i
        int[] t = new int[n];
        Arrays.fill(t, 1);
        // sort indices by value — process smaller values first
        int[][] vec = new int[n][2];
        for (int i = 0; i < n; i++) {
            vec[i][0] = arr[i];
            vec[i][1] = i;
        }
        Arrays.sort(vec, (a, b) -> a[0] - b[0]);
        for (int[] it : vec) {
            int i = it[1];
            // explore left within distance d — stop if blocked by equal or taller element
            for (int j = i - 1; j >= Math.max(0, i - d); j--) {
                if (arr[j] >= arr[i]) break;
                t[i] = Math.max(t[i], 1 + t[j]);
            }
            // explore right within distance d — stop if blocked by equal or taller element
            for (int j = i + 1; j <= Math.min(n - 1, i + d); j++) {
                if (arr[j] >= arr[i]) break;
                t[i] = Math.max(t[i], 1 + t[j]);
            }
        }
        // return maximum jumps achievable from any starting index
        return Arrays.stream(t).max().getAsInt();
    }
}

// Time Complexity :- O(n × d + n log n).
// Space Complexity :- O(n).
