/*********************************************** JAVA **************************************************/

Optimal Solution - Find the largest possible square by comparing all pairwise horizontal and vertical fence distances using a hash set for fast lookup.

class Solution {
    // Modulo constant as required by the problem
    private static final int M = 1_000_000_007;
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        // Store horizontal and vertical fence positions
        List<Integer> hList = new ArrayList<>();
        List<Integer> vList = new ArrayList<>();
        for (int x : hFences) hList.add(x);
        for (int x : vFences) vList.add(x);
        // Add boundary fences
        hList.add(1);
        hList.add(m);
        vList.add(1);
        vList.add(n);
        // Sort fence positions
        Collections.sort(hList);
        Collections.sort(vList);
        // Set to store all possible vertical widths
        Set<Integer> widths = new HashSet<>();
        // Compute all possible vertical distances
        for (int i = 0; i < vList.size(); i++) {
            for (int j = i + 1; j < vList.size(); j++) {
                widths.add(vList.get(j) - vList.get(i));
            }
        }
        int maxSide = 0;
        // Compute horizontal distances and check if matching width exists
        for (int i = 0; i < hList.size(); i++) {
            for (int j = i + 1; j < hList.size(); j++) {
                int height = hList.get(j) - hList.get(i);
                // If same distance exists vertically, it can form a square
                if (widths.contains(height)) {
                    maxSide = Math.max(maxSide, height);
                }
            }
        }
        // If no square can be formed
        if (maxSide == 0)
            return -1;
        // Calculate area modulo M
        long area = (long) maxSide * maxSide;
        return (int) (area % M);
    }
}

Time Complexity :- O(H² + V²).
Space Complexity :- O(V²).
