/*********************************************** JAVA **************************************************/

// Optimal Solution - Checks reachability from index 0 to n-1 using BFS with a farthest pointer to skip already-processed ranges, jumping only to '0' positions within [minJump, maxJump].
/* "The farthest pointer is the key optimization — without it, multiple queue entries could redundantly process overlapping ranges, degrading to O(n²). Starting each range at max(i+minJump, 
    farthest+1) ensures each index is processed at most once across all BFS iterations, giving O(n) total." */

class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        // BFS from index 0 — only '0' positions are valid landing spots
        Queue<Integer> que = new LinkedList<>();
        que.offer(0);
        // farthest index already processed — avoids redundant enqueuing
        int farthest = 0;
        while (!que.isEmpty()) {
            int i = que.poll();
            // start from max of minJump reach or just past farthest processed
            int start = Math.max(i + minJump, farthest + 1);
            for (int j = start; j <= Math.min(i + maxJump, n - 1); j++) {
                if (s.charAt(j) == '0') {
                    // reached last index — valid path exists
                    if (j == n - 1)
                        return true;
                    que.offer(j);
                }
            }
            // update farthest to avoid reprocessing indices
            farthest = Math.max(farthest, i + maxJump);
        }
        return false;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n).
