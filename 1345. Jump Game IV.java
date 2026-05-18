/*********************************************** JAVA **************************************************/

// Optimal Solution - Use BFS with value-to-indices mapping to find the minimum jumps while avoiding repeated same-value traversals.
/* “I model the array as a graph where indices with equal values are connected and use BFS to find the shortest path.” */

class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        // Already at destination
        if (n == 1)
            return 0;
        boolean[] visited = new boolean[n];
        // Map value -> list of indices
        HashMap<Integer, List<Integer>> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            mp.putIfAbsent(arr[i], new ArrayList<>());
            mp.get(arr[i]).add(i);
        }
        Queue<Integer> que = new LinkedList<>();
        que.offer(0);
        visited[0] = true;
        int steps = 0;
        // BFS traversal
        while (!que.isEmpty()) {
            int size = que.size();
            // Process current level
            while (size-- > 0) {
                int curr = que.poll();
                // Reached destination
                if (curr == n - 1) {
                    return steps;
                }
                int left = curr - 1;
                int right = curr + 1;
                // Move left
                if (left >= 0 && !visited[left]) {
                    que.offer(left);
                    visited[left] = true;
                }
                // Move right
                if (right < n && !visited[right]) {
                    que.offer(right);
                    visited[right] = true;
                }
                // Same value jumps
                if (mp.containsKey(arr[curr])) {
                    
                    for (int idx : mp.get(arr[curr])) {
                        
                        if (!visited[idx]) {
                            que.offer(idx);
                            visited[idx] = true;
                        }
                    }
                    // Remove to avoid repeated processing (important optimization)
                    mp.remove(arr[curr]);
                }
            }
            // Next BFS level
            steps++;
        }
        return -1;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n).
