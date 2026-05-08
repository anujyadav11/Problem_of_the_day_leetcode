/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds minimum jumps using BFS with ±1 moves and prime-factor value jumps, using a sieve and seen-set to efficiently process each prime value's multiples exactly once.
/* "The seen HashSet is the critical optimization — without it, the same prime value's multiples get reprocessed every time that value is dequeued, causing TLE. 
    Once a prime's multiples are all enqueued, mark it seen so future occurrences skip the expensive multiple-traversal loop. This reduces total work from O(n × maxEl) to O(maxEl log log maxEl)." */

class Solution {
    private boolean[] isPrime;
    private void buildSieve(int maxEl) {
        isPrime = new boolean[maxEl + 1];
        Arrays.fill(isPrime, true);
        // 0 and 1 are not prime
        if (maxEl >= 0) isPrime[0] = false;
        if (maxEl >= 1) isPrime[1] = false;
        // sieve of eratosthenes
        for (int num = 2; num * num <= maxEl; num++) {
            if (isPrime[num]) {
                for (int multiple = num * num; multiple <= maxEl; multiple += num)
                    isPrime[multiple] = false;
            }
        }
    }
    public int minJumps(int[] nums) {
        int n = nums.length;
        // map each value to all indices containing it
        HashMap<Integer, List<Integer>> mp = new HashMap<>();
        int maxEl = 0;
        for (int i = 0; i < n; i++) {
            mp.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
            maxEl = Math.max(maxEl, nums[i]);
        }
        buildSieve(maxEl);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        // seen prevents reprocessing same prime value's multiples
        HashSet<Integer> seen = new HashSet<>();
        queue.offer(0);
        visited[0] = true;
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int i = queue.poll();
                // reached last index — return step count
                if (i == n - 1) return steps;
                // move left by 1
                if (i - 1 >= 0 && !visited[i - 1]) {
                    queue.offer(i - 1);
                    visited[i - 1] = true;
                }
                // move right by 1
                if (i + 1 < n && !visited[i + 1]) {
                    queue.offer(i + 1);
                    visited[i + 1] = true;
                }
                // skip prime jump if value not prime or already processed
                if (!isPrime[nums[i]] || seen.contains(nums[i])) continue;
                // jump to all indices whose value is a multiple of nums[i]
                for (int multiple = nums[i]; multiple <= maxEl; multiple += nums[i]) {
                    if (!mp.containsKey(multiple)) continue;
                    for (int j : mp.get(multiple)) {
                        if (!visited[j]) {
                            queue.offer(j);
                            visited[j] = true;
                        }
                    }
                }
                // mark this prime value as fully processed
                seen.add(nums[i]);
            }
            steps++;
        }
        return -1;
    }
}

// Time Complexity :- O(maxEl × log(log(maxEl)) + n).
// Space Complexity :- O(maxEl + n).
