/*********************************************** JAVA **************************************************/

// Optimal Solution - Solved using recursive BST partitioning and Pascal's triangle to count valid reorderings modulo 1e9+7.
/* “The first element determines the BST root. I partition the remaining elements into left and right subtrees, recursively count the valid reorderings for each subtree, 
    and multiply those counts by the binomial coefficient that represents the number of ways to interleave the two subsequences while preserving their internal order.” */

class Solution {
    private static final long MOD = 1_000_000_007L;
    private long[][] PT;
    private long solve(List<Integer> nums) {
        int m = nums.size();
        // An empty tree or a single-node tree has one arrangement.
        if (m < 3) {
            return 1;
        }
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int root = nums.get(0);
        // Partition values based on the root of the BST.
        for (int i = 1; i < m; i++) {
            if (nums.get(i) < root) {
                left.add(nums.get(i));
            } else {
                right.add(nums.get(i));
            }
        }
        // Count valid reorderings for the left and right subtrees.
        long leftWays = solve(left) % MOD;
        long rightWays = solve(right) % MOD;
        // Choose positions for left-subtree elements while preserving
        // the relative order of elements within each subtree.
        return (((leftWays * rightWays) % MOD)
                * PT[m - 1][left.size()]) % MOD;
    }
    public int numOfWays(int[] nums) {
        int n = nums.length;
        // Build Pascal's triangle to calculate combinations modulo MOD.
        PT = new long[n + 1][];
        for (int i = 0; i <= n; i++) {
            PT[i] = new long[i + 1];
            Arrays.fill(PT[i], 1);
            for (int j = 1; j < i; j++) {
                PT[i][j] = (PT[i - 1][j - 1]
                        + PT[i - 1][j]) % MOD;
            }
        }
        // Convert the input array to a list for recursive partitioning.
        List<Integer> list = new ArrayList<>();
        for (int x : nums) {
            list.add(x);
        }
        // Subtract the original ordering from all valid reorderings.
        return (int) ((solve(list) - 1 + MOD) % MOD);
    }
}

// Time Complexity :- O(n^2).for building Pascal’s triangle, plus the cost of recursively partitioning the subarrays. 
                            //The recursive partitioning can take O(n²) in the worst case, so the overall worst-case time complexity is O(n²).
// Space Complexity :- O(n^2). for Pascal’s triangle. The recursive lists and call stack can require additional space, up to O(n²) cumulatively in the worst case due to the lists created across recursive calls.

